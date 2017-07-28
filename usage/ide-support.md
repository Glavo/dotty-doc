# IDE 对 Dotty 的支持

Dotty 内置了一个[语言服务器协议](https://github.com/Microsoft/language-server-protocol) 的实现：Dotty 语言服务器。这意味着任何实现了 LSP 的编辑器都能作为 Dotty IDE 使用。目前，我们唯一的官方支持 IDE 是 [Visual Studio Code](https://code.visualstudio.com/)。

## 必要准备

想在您的 Scala 中使用 Dotty，需要先编译 Dotty，请按照 [https://github.com/lampepfl/dotty-example-project](https://github.com/lampepfl/dotty-example-project) 上的说明操作。

## 用法

1. 安装 [Visual Studio Code](https://code.visualstudio.com/)

2. 确保 Visual Studio Code 的二进制文件 `code` 在您的 `$PATH` 中，这使您能够在终端中使用 `code` 命令打开 IDE。这在 Mac 以外的系统中都是默认选项。如果您在使用 Mac 系统，请遵照以下说明：[https : //code.visualstudio.com/docs/setup/mac\#\_command-line](/https : //code.visualstudio.com/docs/setup/mac#_command-line)。

## 状态

### 完全支持的功能

* 类型检查，显示编译时错误/警告
* 悬停时类型提示
* 跳转到定义处（在项目中）
* 查找全部用例

### 部分完成的功能

* 自动补全
* 重命名
* 跳转到项目外的定义处

### 未实现的功能

* 悬停显示文档
* 格式化代码（需要与 scalafmt 集成）
* 快速 debug （可以与 scalafix 集成）

### 目前的限制（将要修复）

* 在启动 IDE 前需要使用 sbt 编译项目（使用 `sbt launchIDE` 命令启动 IDE 时将会自动完成）。
* IDE 启动后，IDE 中未打开的源文件不应该在其他编辑器中进行修改，否则 IDE 将无法识别这些修改。
* 只会提示类型错误/警告，而不是全部编译时错误/警告

## 反馈

您可以在 [https://github.com/lampepfl/dotty/issues](https://github.com/lampepfl/dotty/issues) 上报告问题，也可以在 [Dotty gitter 频道](https://gitter.im/lampepfl/dotty)上使用 chat 功能。


