package com.beachape.sbt.opencv

import scala.language.postfixOps
import sbt._
import sbt.Keys._

/**
 * Created by Lloyd on 2/22/16.
 */
object Plugin extends AutoPlugin {

  override def projectSettings: Seq[Setting[_]] = {
    import autoImport._
    Seq(
      autoCompilerPlugins := true,
      classpathTypes += "maven-plugin", // Some dependencies like `javacpp` are packaged with maven-plugin packaging
      javaCVPlatform := Platform.current,
      javaCppVersion := Versions.javaCppVersion,
      javaCVVersion := Versions.javaCVVersion,
      javaCppPresetsVersion := Versions.javaCPPPresentsForOpenCVVersion,
      libraryDependencies <++= (javaCVPlatform, javaCVVersion, javaCppVersion, javaCppPresetsVersion) {
        (resolvedOpenCVPlatform, resolvedJavaCVVersion, resolvedJavaCppVersion, resolvedJavaCppPresetsVersion) =>
          Seq(
            "org.bytedeco" % "javacv" % resolvedJavaCVVersion excludeAll (
              ExclusionRule(organization = "org.bytedeco.javacpp-presets"),
              ExclusionRule(organization = "org.bytedeco.javacpp")
            ),
            "org.bytedeco.javacpp-presets" % "opencv" % s"$resolvedJavaCppPresetsVersion-$resolvedJavaCppVersion" classifier "",
            "org.bytedeco.javacpp-presets" % "opencv" % s"$resolvedJavaCppPresetsVersion-$resolvedJavaCppVersion" classifier resolvedOpenCVPlatform,
            "org.bytedeco" % "javacpp" % resolvedJavaCppVersion
          )
      }
    )
  }

  object Versions {
    val javaCppVersion = "1.1"
    val javaCVVersion = "1.1"
    val javaCPPPresentsForOpenCVVersion = "3.0.0"
  }

  object autoImport {
    val javaCVPlatform = SettingKey[String]("javaCVPlatform", """The platform that you want to compile for (defaults to the platform of the current computer). You can also set this via the "sbt.javacv.platform" System Property """)
    val javaCppVersion = SettingKey[String]("javaCppVersion", s"Version of Java CPP that you want to use, defaults to ${Versions.javaCppVersion}")
    val javaCppPresetsVersion = SettingKey[String]("javaCppPresetsVersion", s"Version of Java CPP Presets that you want to use, defaults to ${Versions.javaCppVersion}")
    val javaCVVersion = SettingKey[String]("javaCVVersion", s"Version of Java CV that you want to use, defaults to ${Versions.javaCVVersion}")
  }

  override def requires: Plugins = plugins.JvmPlugin

  override def trigger: PluginTrigger = allRequirements

}