# Dottydoc

Dottydoc 是为您的项目生成嵌入式文档以及 API 参考的工具。

在以前的 Scaladoc 工具中，文档与 API 参考间有很大的区别。Dottydoc 允许您在文档中对 API 进行引用并且渲染，使得两者自然的结合。

为了做到这一点，Dottydoc 与 [Jekyll](https://jekyllrb.com/) 提供的静态网页生成形式非常相似。您可能已经猜到了，整个 Dotty 的站点就是用 Dottydoc 生成的。

通过 Dottydoc 创建一个站点与用 Jekyll 一样简单。站点的根目录存放站点的布局，此处放置的所有文件都视为静态的，也可以进行模板拓展处理。

需要进行模板拓展的文件扩展名必须为 `.html` 或者 `.md`。下文中这类文件被称为“模板文件”或者“模板”。

一个简单的“Hello world”站点看起来可能是这样：

```shell
├── docs
│   └── getting-started.md
└── index.html
```

这将为您提供一个具有以下端点的站点：

```shell
_site/index.html
_site/docs/getting-started.html
```

和 Jekyll 一样，该站点存放在 `_site` 目录下。

## 使用现有的模板与布局

Dottydoc 使用 [Liquid](https://shopify.github.io/liquid/) 模板引擎，并提供了很多特定于 Scala 文档的自定义过滤器以及标签。

在 Dottydoc 中，所有模板都可以包含一个 YAML 头信息。头信息会被解析，然后通过 Liquid 引擎放入可用的 `page` 变量中。

为了执行模板拓展，Dottydoc 会在 头信息里的 `layout` 中进行寻找。下面是使用模板系统的简单例子，`index.html`：

```html
---
layout: main
---

<h1>Hello world!</h1>
```

有一个简单的 `main` 模板：

```html
<html>
    <head>
        <title>Hello, world!</title>
    </head>
    <body>
        {{ content }}
    </body>
</html>
```

在这个例子中，`{{ context }}` 会被替换为 `<h1>Hello world!</h1>。`

布局必须放在站点根目录下的 `_layouts` 文件夹下：

```shell
├── _layouts
│   └── main.html
├── docs
│   └── getting-started.md
└── index.html
```

您也可用使用 Dottydoc 附带的默认布局。

## 博客

Dottydoc 还允许和 Jekyll 一样简单的搭建博客。博客的帖子放在 `./blog/_posts` 目录中，文件名必须是 `year-month-day-title.{md, html}` 的形式。

一个简单的例子：

```
├── blog
│   └── _posts
│       └── 2016-12-05-implicit-function-types.md
└── index.html
```

每篇博文都应该包含头信息以及 `layout` 声明来采用模板。

## 导入

在 Liquid 中，`include` 标签用来在模板中包含其他模板。

```html
<div class="container">
    {% include "sidebar.html" %}
</div>
```

如果导入的是 `html` 文件，则可以省略扩展名。

被导入文件需要保存在站点根目录下的 `_includes` 文件夹下。Dottydoc 提供了一些默认导入文件，用户指定的导入文件可以覆盖掉它们。

导入文件“sidebar.html”的示例结构：

```
├── _includes
│   └── sidebar.html
├── blog
│   ├── _posts
│   │   └── 2016-12-05-implicit-function-types.md
│   └── index.md
└── index.html
```

## 侧边栏

Dottydoc 也允许您创建自定义目录，您可以通过覆盖 `toc.html` 导入文件来实现，也可以通过 `sidebar.yml` 文件指定一个站点根目录下的文件：

```yaml
sidebar:
    - title: Blog
      url: blog/index.html
    - title: Docs
      url: docs/index.html
    - title: Usage
      subsection:
        - title: Dottydoc
          url: docs/usage/dottydoc.html
        - title: sbt-projects
          url: docs/usage/sbt-projects.html
```

`sidebar` 键是必要的，同样，对于每个元素， `title` 也是必要的。默认列表允许有子项——目前列表的深度不能超过 2 ，我们也希望能够移除这种限制，欢迎贡献代码！

具有 `subsection` 键的项目可能在当前格式中没有 `url` 键。这个网站的样例的目录结构可以是：

```
├── blog
│   └── _posts
│       └── 2016-12-05-implicit-function-types.md
├── index.html
└── sidebar.yml
```



## Dottydoc 的具体标签以及行为

### 链接到 API

如果您想在 markdown 文件中链接到 `scala.collection.immutable.Seq`，您可以简单的链接到它的全限定名：

```markdown
[Seq](scala.collection.immutable.Seq)
```

也可以用相同的方式链接到类的成员：

```markdown
[Seq.isEmpty](scala.collection.immutable.Seq.isEmpty)
```

Dottydoc 使用以“$”结尾的名称来代表 `object` 。要链接到 `List.range`，你可以这样写：

```markdown
[Object.range](scala.collection.immutable.List$.range)
```

###  呈现文档字符串

有时候您需要在文档中复制 API 字符串，Dottydoc 能够轻松的做到这一点：

```
{% docstring "scala.collection.immutable.Seq" %}
```

### 其他拓展

我们很乐意聆听您的想法，以便您生成所需的文档，您的意见是什么？也许您想要渲染方法的定义或者成员？[提交 issue](https://github.com/lampepfl/dotty/issues/new) 让我们知道吧！

## 默认布局

### main.html

所有其他布局的包装器，包含了使用了 JavaScript 以及 CSS 的默认 `<head>` 标签。

#### 变量

* `context`：放置在 `<body>` 标签中
* `extraCSS`：网站的拓展 CSS 样式表的相对路径列表
* `extraJS`：网站的拓展 JavaScript 脚本的相对路径列表
* `title`：页面的 `<title>` 标签

### sidebar.html

侧边栏使用 `main.html` 作为它的父布局。它会添加从 YAML 文件（如果存在）生成的侧边栏，以及项目 API 的目录。

#### 变量

* `content`：放置在 class 设定为 `content-body` 的 `<div>` 标签内
* `docs`：从提供的源文件生成 API 文档，默认情况下已经被指定，不需要显式设定

### doc-page.html

文档页面用于需要使用侧边栏的页面，并提供导入 `{{ content }}` 的最小包装。

### api-page.html

最后两种布局是 Dottydoc 特殊处理的。API 页面的输入是一个文档[实体](http://dotty.epfl.ch/api/dotty/tools/dottydoc/model/Entity.html)。可以通过改变此页面来更改 Dottydoc 改变显示 API 文档的方式。

### blog-page.html

一个博客页面通过使用 `./blog/_posts/` 下的文件作为输入来构建博客。

## 默认导入

* `scala-logo.svg`：svg 格式的 Dotty 的版本信息
* `toc.html`：默认列表的模板