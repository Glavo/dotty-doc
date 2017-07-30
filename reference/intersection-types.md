# 交集类型

`&` 操作符用于类型中创建交集类型。

```scala
trait Resettable {
  def reset(): this.type
}
trait Growable[T] {
  def add(x: T): this.type
}
def f(x: Resettable & Growable[String]) = {
  x.reset()
  x.add("first")
}
```

 `x`值的类型既要是 `Resettable` 又要是 `Growable[String]`。交集类型 `A & B` 用来代替 Scala 2 中形如 `A with B` 的符合类型（目前 `A with B` 的形式仍允许，但是将会被弃用删除）。

与 `with` 声明的复合类型不同，`&` 满足交换律：`A & B` 与 `B & A` 是等价的。

交集类型 `A & B` 的成员是 `A` 与 `B` 包含的所有成员。例如 `Resettable & Growable[String]` 具有成员方法 `reset` 和 `add`。

如果一个成员同时存在于 `A` 与 `B` 中，那么 `A & B` 中它的类型是 `A` 中与 `B` 中它的类型的交集类型。例如，对于下面的代码：

```scala
trait A {
  def children: List[A]
}
trait B {
  def children: List[B]
}
val x: A & B = new C
val ys: List[A & B] = x.children
```

`A & B` 中 `children `的类型是 `A` 中与 `B` 中它的类型的交集类型 `List[A] & Lst[B]`，因为 `List` 是协变的，所以能进一步简化为 `List[A & B]`。

人们可能想知道编译器是如何从 `List[A]` 和 `List[B]` 中提出 `List[A & B]` 这个类型的定义。这个问题的答案是不需要。`A & B` 至少表示对类型的一组约束，在_构建_值的时候，必须保证所有被继承的成员都被正确的定义了。因此，如果定义了这个继承自 `A` 以及 `B` 的类 `C`，那么在 `C` 中要定义满足这组约束的方法 `children`。

```scala
class C extends A with B {
  def children: List[A & B] = ???
}
```

