package com.beachape.sbt.opencv

import scala.language.postfixOps
import sbt._
import sbt.Keys._
import com.beachape.sbt.javacpp.{ Plugin => JavaCppPlugin }

/**
 * Created by Lloyd on 2/22/16.
 */
object Plugin extends AutoPlugin {

  override def projectSettings: Seq[Setting[_]] = {
    import autoImport._
    Seq(
      javaCVVersion := Versions.javaCVVersion,
      libraryDependencies <+= javaCVVersion { resolvedJavaCVVersion =>
        "org.bytedeco" % "javacv" % resolvedJavaCVVersion excludeAll (
          ExclusionRule(organization = "org.bytedeco.javacpp-presets"),
          ExclusionRule(organization = "org.bytedeco.javacpp")
        )
      },
      JavaCppPlugin.javaCppPresetDependency("opencv")
    )
  }

  object Versions {
    val javaCVVersion = "1.1"
  }

  object autoImport {
    val javaCVVersion = SettingKey[String]("javaCVVersion", s"Version of Java CV that you want to use, defaults to ${Versions.javaCVVersion}")
  }

  override def requires: Plugins = plugins.JvmPlugin && JavaCppPlugin

  override def trigger: PluginTrigger = allRequirements

}