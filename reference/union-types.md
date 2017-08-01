# 并集类型

`|` 操作符用于类型时，会创建并集类型。

```scala
case class UserName(name: String) {
  def lookup(admin: Admin): UserData
}
case class Password(hash: Hash) {
  def lookup(admin: Admin): UserData
}

def help(id: UserName | Password) = {
  val user = id match {
    case UserName(name) => lookupName(name)
    case Password(hash) => lookupPassword(hash)
  }
  // ...
}
```

并集类型与交集类型相反。所有 `A` 类型的值和 `B` 类型的值都是 `A | B` 类型的值。`|` 符合_交换律_：`A | B` 与 `B | A` 是等价的。

在编译期，只有显式的标准为并集类型时，表达式的类型才会被视作是并集类型。这可以在 REPL 中体现：

```scala
scala> val password = Password(123)
val password: Password = Password(123)
scala> val name = UserName("Eve")
val name: UserName = UserName(Eve)
scala> if (true) name else password
val res2: Object & Product = UserName(Eve)
scala> val either: Password | UserName = if (true) name else password
val either: Password | UserName = UserName(Eve)
```

`res2` 的类型是 `UserName` 和 `Product` 的子类型 `Object & Product` 而不是它的最小父类型 `Password | UserName`。如果你想要得到最小符类型，我们需要和 `either` 一样显式的声明它。更准确的说，在推导 `val`  、`var` 定义， `def` 定义的方法的返回值的类型，以及传递类型参数时，类型检查器会拓宽并集类型到一个非并集类型。`A | B` 拓宽后的类型通常是 `A` 与 `B` 共有的全部超类类型以及实现的特质类型的交集类型；其中也不包括细化类型。在这一点上，并集类型类似于单例类型 `x.type`，它们在没有被显式给出时都会被拓宽到它们的底层类型。