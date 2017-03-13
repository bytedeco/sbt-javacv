package org.bytedeco.sbt.javacv

import scala.language.postfixOps
import sbt._
import sbt.Keys._
import org.bytedeco.sbt.javacpp.{ Plugin => JavaCppPlugin }

/**
 * Created by Lloyd on 2/22/16.
 */
object Plugin extends AutoPlugin {

  override def projectSettings: Seq[Setting[_]] = {
    import autoImport._
    Seq(
      javaCVVersion := Versions.javaCVVersion,
      libraryDependencies += {
        "org.bytedeco" % "javacv" % javaCVVersion.value excludeAll (
          ExclusionRule(organization = "org.bytedeco.javacpp-presets"),
          ExclusionRule(organization = "org.bytedeco.javacpp")
        )
      },
      JavaCppPlugin.autoImport.javaCppPresetLibs ++= JavaCVCppPresets.libs
    )
  }

  object Versions {
    val javaCVVersion = "1.3.1"
  }

  object autoImport {
    val javaCVVersion = SettingKey[String]("javaCVVersion", s"Version of Java CV that you want to use, defaults to ${Versions.javaCVVersion}")
  }

  object JavaCVCppPresets {
    /**
     * List of default JavaCPP preset names and versions that will be added by this plugin
     */
    val libs = Seq(
      "opencv" -> "3.2.0",
      "videoinput" -> "0.200"
    )
  }

  override def requires: Plugins = plugins.JvmPlugin && JavaCppPlugin

  override def trigger: PluginTrigger = allRequirements

}