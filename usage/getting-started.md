# 开始

## 基础

请确保您在使用 Java8 或者更高的版本。`java -version` 的输出中应该包含 `1.8` 。

## 选择1：安装 Dotty

如果您是 Mac 用户，则可以使用 [brew](https://brew.sh/) 安装 dotty

```bash
brew install lampepfl/brew/dotty
```

如果您是 Linux 或 Windows 用户，请下载 Dotty [最新的版本](https://github.com/lampepfl/dotty/releases)。您可以将 dotty 文件夹下的 `/bin` 目录添加到到 `PATH` 环境变量中。

## 选择2：直接通过源代码使用 Dotty

```bash
$ git clone --recursive https://github.com/lampepfl/dotty.git
$ cd dotty
$ sbt managedSources # Needed for IDE import to succeed
```

Dotty 提供了一个标准的 sbt 构造：编译，运行以及启动 repl 可以在 sbt 内完成：

```sh
$ sbt
> dotc tests/pos/HelloWorld.scala
> dotr HelloWorld
hello world
```

## 试用 Dotty

可以在浏览器中使用 [Scastie](https://scastie.scala-lang.org/?target=dotty) 进行尝试。

## 创建一个 Dotty 项目

创建一个 dotty 项目最快的方式是使用 [sbt\(0.13.5+\)](http://www.scala-sbt.org/)。

创建一个 dotty 项目：

```sh
$ sbt new lampepfl/dotty.g8
```

或者创建一个与 Scala 2 交叉编译的 dotty 项目：

```bash
$ sbt new lampepfl/dotty-cross.g8
```

示例项目请参见 [Dotty 示例项目](https://github.com/lampepfl/dotty-example-project)。

## Bash 脚本

如果您已经在本例克隆了 [Dotty 仓库](https://github.com/lampepfl/dotty)，请在 `.bash_profile` 文件中添加以下内容：

```bash
export PATH=$HOME/dotty/bin:$PATH
```

然后您就能直接在终端执行相应的命令：

```bash
# Compile code using Dotty
$ dotc tests/pos/HelloWorld.scala

# Run it with the proper classpath
$ dotr HelloWorld
```

## 使用 REPL

```bash
$ sbt
> repl
Welcome to Scala.next (pre-alpha)  (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_101).
Type in expressions to have them evaluated.
Type :help for more information.
scala>
```

或者使用 bash 命令：

```bash
$ dotr
```



