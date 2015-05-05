import play.PlayScala
import sbt._
import Keys._
import play.Play.autoImport._


object projectBuild extends Build {

  val basicLibraries = Seq(
    jdbc,
    anorm,
    cache,
    ws
  )

  val testLibraries = Seq(
    "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    "org.scalacheck" %% "scalacheck" % "1.12.2" % "test"
  )

  val htmlLibraries = Seq(
    "org.webjars" %% "webjars-play" % "2.3.0-2",
    "org.webjars.bower" % "bootstrap" % "3.3.4",
    "org.webjars.bower" % "jquery" % "2.1.3"
  )

  val dbLibraries = Seq(
    "com.h2database" % "h2" % "1.4.187",
    "com.typesafe.slick" % "slick_2.11" % "2.1.0",
    "com.typesafe.play" %% "play-slick" % "0.8.1"
  )

  val securityLibraries = Seq(
    "org.mindrot" % "jbcrypt" % "0.3m"
  )

  lazy val root = Project("project", file(".")).enablePlugins(PlayScala) settings(
    name := """project""",
    version := "1.0",
    scalaVersion := "2.11.6",
    resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
    libraryDependencies ++= basicLibraries ++ testLibraries ++ htmlLibraries ++ dbLibraries ++ securityLibraries
  )
}
