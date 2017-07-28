# Dottydoc

Dottydoc 是为您的项目生成嵌入式文档以及 API 参考的工具。

在以前的 Scaladoc 工具中，文档与 API 参考间有很大的区别。Dottydoc 允许您在文档中对 API 进行引用并且渲染，使得两者自然的结合。

为了做到这一点，Dottydoc 与 [Jekyll](https://jekyllrb.com/) 提供的静态网页生成形式非常相似。您可能已经猜到了，整个 Dotty 的网站就是用 Dottydoc 生成的。

通过 Dottydoc 创建一个网站与用 Jekyll 一样简单。站点的根目录存放站点的布局，此处放置的所有文件都视为静态的，也可以进行模板拓展处理。

需要进行模板拓展的文件扩展名必须为 `.html` 或者 `.md`。下文中这类文件被称为“模板文件”或者“模板”。

一个简单的“Hello world”网站看起来可能是这样：

```shell
├── docs
│   └── getting-started.md
└── index.html
```

这将为您提供一个具有以下端点的网站：

```shell
_site/index.html
_site/docs/getting-started.html
```

和 Jekyll 一样，该网站存放在 `_site` 目录下。

## 使用现有的模板与布局
