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

