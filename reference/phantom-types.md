# 幻象类型

## 什么是幻象类型？

幻象类型是一种对运行时没有影响的抽象类型的表现形式。它们对于利用类型证据证明代码的静态性质十分有效。因其没有运行时作用使得一旦达到了对代码的约束，它们在最后的代码中就会被编译器擦除。

当我们说它们不具有运行时作用时，我们不仅仅在说诸如IO，``field mutation`` ，异常等等。我们同样暗示着一个接收一个幻象的函数，它的结果不会被这个参数所影响。

由于幻象在运行时不存在，它们不能定义了诸如 ``hashCode`` 、 ``equals`` 、``getClass`` 、``asinstanceOf`` 的 ``scala.any`` 的子类型。所有这些操作不能在幻象类型上存在因为运行时是没有对应的对象实例存在的。第一眼看上去这有点像一个限制，但是实际上没有 ``asInstanceOf`` 会使约束变得更可靠，因为将幻象类型的值转换去伪造一个证据将是不可能的。

如果它们不存在于由 ``scala.Any`` 和 ``scala.Nothing`` 所划定边界的论域（universe）中，它们又存在于哪里？这个问题的答案是由它们的幻象 ``Any`` 和幻象 ``Nothing`` 划定边界的它们自己的类型论域中。实际上我们还允许多重幻象论域存在。

```none
          +-----+                     +---------------+           +--------------------+
          | Any |                     | MyPhantom.Any |           | MyOtherPhantom.Any |
          +-----+                     +---------------+           +--------------------+
             |                               |                              |
        +----+------+                  +-----+------+                      ...
        |           |                  |            |
   +--------+   +--------+          +------+   +--------+
   | AnyRef |   | AnyVal |          | Inky |   | Blinky |
   +--------+   +--------+          +------+   +--------+
      ...          ...                  |           |
    +------+        |               +-------+       |
    | Null |        |               | Pinky |       |
    +------+        |               +-------+       |
       |            |                   |           |
       +------+-----+                   +----+------+                      ...
              |                              |                              |
         +---------+                +-------------------+       +------------------------+
         | Nothing |                | MyPhantom.Nothing |       | MyOtherPhantom.Nothing |
         +---------+                +-------------------+       +------------------------+
```

在一个幻象论域内，其类型支持完整的Dotty类型系统。但是我们不能用 `&` 或 `|` 或类型边界（type bound）去混合不同论域中的类型。每一个类型都必须是完全地在一个论域内定义的。

## 实现你自己的幻象类型

幻象类型由一个继承 ``scala.phantom`` 的 ``object`` 定义。这个对象会代表一个与在 ``scala.Any`` 或其他幻象论域的类型完全隔离的一个论域。我们将自己的幻象论域定义为 ``MyPhantoms`` 。

```scala
object MyPhantoms extends Phantom
```
```scala
package scala
trait Phantom { // only an `object` can extend this trait
  protected final type Any // not a subtype of scala.Any
  protected final type Nothing // subtype of every subtype of this.Any
  protected final def assume: this.Nothing
}
```
