# SBT-JavaCV

[![Join the chat at https://gitter.im/bytedeco/sbt-javacv](https://badges.gitter.im/bytedeco/sbt-javacv.svg)](https://gitter.im/bytedeco/sbt-javacv?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.bytedeco/sbt-javacv/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.bytedeco/sbt-javacv) [![Build Status](https://travis-ci.org/bytedeco/sbt-javacv.svg?branch=master)](https://travis-ci.org/bytedeco/sbt-javacv)


Makes it easy to start playing around with OpenCV (via JavaCV) in Scala without having to compile
or install OpenCV on your own.

Configures the following dependencies in your project:

- JavaCV
- JavaCPP
- JavaCPP Presets

## Usage

In your `project/plugins.sbt`:

```scala
addSbtPlugin("org.bytedeco" % "sbt-javacv" % version)
```

See the Maven badge for latest version.

## Customisation

By default, this plugin will download the appropriate binaries for the platform of the computer currently
running SBT, you can modify this by setting it to another platform (for example, if you want to compile JARs to be run
on other platforms)

```scala
javaCppPlatform := "android-arm"
```

For more details, see the [SBT-JavaCPP plugin](https://github.com/bytedeco/sbt-javacpp#customisation)

Also, this plugin only pulls in `opencv` JavaCPP preset by default. If you wish to add more, use the `javaCppPresetLibs`
settings key and use `++=` to append more (if you do not use append, you will end up wiping out the ones this plugin appends by default):

```scala
javaCppPresetLibs ++= Seq(
  "ffmpeg" -> "3.4.1"
)
```

### Note when upgrading from versions < 1.16:

As of JavaCPP 1.4 `videoinput` is only available for windows, so it's not included by default since sbt-javacv 1.16.
If you need it, you can add it just like any other JavaCPP preset:
      
```scala
javaCppPresetLibs ++= Seq(
  "videoinput" -> "0.200"
)
```