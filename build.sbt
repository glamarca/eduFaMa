name := """enssecfra"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

val basicLibraries = Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "com.typesafe.akka" %% "akka-actor" % "2.3.9"
)

val testLibraries = Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.2" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.9" % "test"
)

val htmlLibraries = Seq(
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "org.webjars.bower" % "bootstrap" % "3.3.4",
  "org.webjars.bower" % "jquery" % "2.1.3",
  "org.webjars.bower" % "bootswatch" % "3.3.4"
)

val dbLibraries = Seq (
  "mysql" % "mysql-connector-java" % "5.1.30",
  "com.typesafe.slick" % "slick_2.11" % "3.0.0-M1",
  "com.typesafe.play" %% "play-slick" % "0.9-M4"
)

libraryDependencies ++= basicLibraries ++ testLibraries ++ htmlLibraries ++ dbLibraries
