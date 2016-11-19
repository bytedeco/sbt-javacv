name := """sbt-javacv"""

version := "1.12-SNAPSHOT"

organization := "org.bytedeco"

sbtPlugin := true

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xlint", "-Xlog-free-terms")

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <url>https://github.com/bytedeco/sbt-javacv</url>
    <licenses>
      <license>
        <name>MIT</name>
        <url>http://opensource.org/licenses/MIT</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:bytedeco/sbt-javacv.git</url>
      <connection>scm:git:git@github.com:bytedeco/sbt-javacv.git</connection>
    </scm>
    <developers>
      <developer>
        <id>lloydmeta</id>
        <name>Lloyd Chan</name>
        <url>https://beachape.com</url>
      </developer>
    </developers>
  )

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

addSbtPlugin("org.bytedeco" % "sbt-javacpp" % "1.9")