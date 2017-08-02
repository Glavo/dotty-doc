# Dotty文档

Dotty 是一个为 Scala 尝试新语言概念以及编译器技术的平台。Dotty 的重点在于简化。我们删除无关紧要的语法（譬如移除了 XML 字面量语法），并尝试将 Scala 的类型归结为更小的一些更基本的构建者。 DOT（依赖对象类型演算）研究了这些构建者的理论。

您通过本文档能够了解如何在机器上使用 Dotty 编译器，浏览代码，在您常用的 IDE 上配置 Dotty 开发环境。

## 目录

* 博客
* 用法
    * [开始](usage/getting-started.md)
    * [SBT 项目](usage/sbt-projects.md)
    * [IDE 支持](usage/ide-support.md)
    * [CBT 项目](usage/cbt-projects.md)
    * [Dottydoc](usage/dottydoc.md)
* 参考
    * 新类型
        * [交集类型](reference/intersection-types.md)
        * [并集类型](reference/union-types.md)
        * [类型 lambda](reference/type-lambdas.md)
        * [隐式函数类型](reference/implicit-function-types.md)
        * [幽灵类型](reference/phantom-types.md)
        * 字面量单例类型
    * 枚举
        * 枚举
        * 代数数据类型
        * 枚举以及代数数据类型的翻译
    * 其他新功能
        * 多元相等
        * 内联
        * 特质参数
        * 隐式传名参数
        * 元组参数自动转换
        * 命名类型参数
        * 本地优化
    * 更改的功能
        * 惰性变量与@volatile
        * 结构类型
        * 类型检查
        * 类型推断
        * 隐式推断
        * 变长参数模式
        * 模式匹配
        * 自动 η 扩张
    * 废弃的功能
        * Delayedinit
        * 宏
        * 存在类型
        * 过程语法
        * 早期初始化器
        * 类隐藏
        * 22 限制
        * XML 字面量
        * 自动应用
        * 弱一致
* 贡献
    * 开始
    * 工作流程
    * Eclipse
    * IntelliJ IDEA
    * 测试
    * 使用后端
* 内幕
    * 后端
    * 类路径
    * 核心数据结构
    * Contexts
    * Dotc vs Scalac
    * Dotty 中的高阶类型
    * Dotty 总结构
    * Dotc 的时间概念
    * Scala 语法摘要
    * 类型系统
* 资源
    * Talks
