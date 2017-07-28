# 在 cbt 中使用 Dotty

_注意：现在 Dotty 对 cbt 的支持是实验性并且不完善的（例如不支持增量编译），我们建议现在_[_使用 sbt_](sbt-projects.md)。

cbt 内置了 Dotty 支持。按照 [cbt 教程](https://github.com/cvogt/cbt/)，简单的用 `Build` 继承 `Dotty`。

```scala
// build/build.scala
import cbt._
class Build(val context: Context) extends Dotty {
  ...
}
```

另见[示例项目](https://github.com/cvogt/cbt/tree/master/examples/dotty-example)。

