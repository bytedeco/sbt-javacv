package com.beachape.sbt.opencv

/**
 * Created by Lloyd on 2/22/16.
 */

object Platform {

  private val platformOverridePropertyKey: String = "sbt.javacv.platform"

  /**
   * The platform of the machine running SBT thanks to https://github.com/chimpler/blog-scala-javacv/blob/master/build.sbt#L19
   *
   * To override, set the "sbt.javacv.platform" System Property
   *
   * Should be in lockstep with:
   *
   * https://github.com/bytedeco/javacpp/blob/master/src/main/java/org/bytedeco/javacpp/Loader.java#L65-L95
   */
  val current: String = sys.props.getOrElse(key = platformOverridePropertyKey, default = parsePlatformFromJVM)

  private def parsePlatformFromJVM = {
    val jvmName = System.getProperty("java.vm.name", "").toLowerCase
    val jvmOsName = System.getProperty("os.name", "").toLowerCase
    val jvmOsArch = System.getProperty("os.arch", "").toLowerCase
    val jvmAbiType = System.getProperty("sun.arch.abi", "").toLowerCase()
    val osName = jvmOsName match {
      case os if jvmName.startsWith("dalvik") && os.startsWith("linux") => "android"
      case os if jvmName.startsWith("robovm") && os.startsWith("darwin") => "ios"
      case os if os.startsWith("mac os x") => "macosx"
      case other => {
        val spaceIndex = other.indexOf(' ')
        if (spaceIndex > 0)
          other.substring(0, spaceIndex)
        else
          other
      }
    }
    val osArch = jvmOsArch match {
      case "i386" | "i486" | "i586" | "i686" => "x86"
      case "amd64" | "x86-64" | "x64" => "x86_64"
      case "aarch64" | "armv8" | "arm64" => "arm64"
      case arch if arch.startsWith("arm") && jvmAbiType == "gnueabihf" => "armhf"
      case arch if arch.startsWith("arm") || osName == "ios" => "arm"
      case other => other
    }
    s"$osName-$osArch"
  }

}