# SBT-JavaCV

[![Join the chat at https://gitter.im/bytedeco/sbt-javacv](https://badges.gitter.im/bytedeco/sbt-javacv.svg)](https://gitter.im/bytedeco/sbt-javacv?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Makes it easy to start playing around with OpenCV (via JavaCV) in Scala without having to compile
or install OpenCV on your own.

Configures the following dependencies in your project:

- JavaCV
- JavaCPP
- JavaCPP Presets

## Usage

In your `project/plugins.sbt`:

```scala
addSbtPlugin("org.bytedeco" % "sbt-javacv" % "1.8")
```

## Customisation

By default, this plugin will download the appropriate binaries for the platform of the computer currently
running SBT, you can modify this by setting it to another platform (for example, if you want to compile JARs to be run
on other platforms)

```scala
javaCppPlatform := "android-arm"
```

For more details, see the [SBT-JavaCPP plugin](https://github.com/bytedeco/sbt-javacpp#customisation)

Also, this plugin only pulls in `opencv` and `videoinput` JavaCPP presets by default. If you wish to add more, use the `javaCppPresetLibs`
settings key and use `++=` to append more (if you do not use append, you will end up wiping out the ones this plugin appends by default):

```scala
javaCppPresetLibs ++= Seq(
  "ffmpeg" -> "2.8.1"
)
```


## Licence

The MIT License (MIT)

Copyright (c) 2016 by Lloyd Chan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
