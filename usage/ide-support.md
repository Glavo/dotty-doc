# IDE 对 Dotty 的支持

Dotty 内置了一个[语言服务器协议](https://github.com/Microsoft/language-server-protocol) 的实现：Dotty 语言服务器。这意味着任何实现了 LSP 的编辑器都能作为 Dotty IDE 使用。目前，我们唯一的官方支持 IDE 是 [Visual Studio Code](https://code.visualstudio.com/)。

## 必要准备

想在您的 Scala 中使用 Dotty，需要先编译 Dotty，请按照 [https://github.com/lampepfl/dotty-example-project](https://github.com/lampepfl/dotty-example-project) 上的说明操作。

## 用法

1. 安装 [Visual Studio Code](https://code.visualstudio.com/)

2. 确保 Visual Studio Code 的二进制文件 `code` 在您的 `$PATH` 中，这使您能够在终端中使用 `code` 命令打开 IDE。



