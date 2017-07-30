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

如果一个成员同时存在于 `A` 与 `B` 中，那么