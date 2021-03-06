# 隐式函数类型

隐式函数类型表示的是带隐式参数的函数的类型。例如：

```scala
type Contextual[T] = implicit Context => T
```

隐式函数类型的值与接受隐式参数的方法一样，接受隐式参数。例如：

```scala
implicit ctx: Context = ...

def f(x: Int): Contextual[Int] = ...

f(2)    // is expanded to f(2)(ctx)
```

反过来说，如果一个表达式预期的类型 `E` 是一个函数函数类型 `implicit (T_1, ..., T_n) => U`，并且 `E` 尚未是隐式函数值，那么 `E` 会被重写转换为一个隐式函数值

```scala
implicit (x_1: T1, ..., x_n: Tn) => E
```

名称 `x_1,  ..., x_n` 是任意的。例如，接着刚才的定义，

```scala
def g(arg: Contextual[Int]) = ...

g(22)      // is expanded to g { implicit ctx => 22 }

g(f(2))    // is expanded to g { implicit ctx => f(2)(ctx) }

g(implicit ctx => f(22)(ctx)) // is left as it is
```

隐式函数类型有着相当强的表现力。例如这样的下面是用“构建器模式”构造一个表的例子：

```scala
table {
  row {
    cell("top left")
    cell("top right")
  }
  row {
    cell("botttom left")
    cell("bottom right")
  }
}
```

这个设计是通过定义允许通过 `add` 方法添加元素的类 `Table` 和 `Row` 实现的：

```scala
class Table {
  val rows = new ArrayBuffer[Row]
  def add(r: Row): Unit = rows += r
  override def toString = rows.mkString("Table(", ", ", ")")
}

class Row {
  val cells = new ArrayBuffer[Cell]
  def add(c: Cell): Unit = cells += c
  override def toString = cells.mkString("Row(", ", ", ")")
}

case class Cell(elem: String)
```

`table`，`row` 和 `cell` 构建器方法可以利用隐函数类型定义，以避免样板管道代码。

```scala
def table(init: implicit Table => Unit) = {
  implicit val t = new Table
  init
  t
}

def row(init: implicit Row => Unit)(implicit t: Table) = {
  implicit val r = new Row
  init
  t.add(r)
}

def cell(str: String)(implicit r: Row) =
  r.add(new Cell(str))
```

这样的代码会被自动拓展为下面的代码：

```scala
table { implicit $t: Table =>
  row { implicit $r: Row =>
    cell("top left")($r)
    cell("top right")($r)
  }($t)
  row { implicit $r: Row =>
    cell("botttom left")($r)
    cell("bottom right")($r)
  }($t)
}
```

## 参考

想要了解更多信息，请参见[博客文章](https://www.scala-lang.org/blog/2016/12/07/implicit-function-types.html)。