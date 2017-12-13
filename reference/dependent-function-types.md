# 依赖函数类型

依赖函数类型表示结果类型依赖于参数的函数类型。例如：

```scala
class Entry { type Key; key: Key }

def extractKey(e: Entry): e.Key = e.key          // 一个依赖方法
val extractor: (e: Entry) => e.Key = extractKey  // 一个依赖函数值
```

Scala 已经有了_依赖方法_，即方法的结果类型依赖于方法的一些参数，方法 `extractKey` 就是一个例子。