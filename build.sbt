name := """play_in_scala"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.2" % Test
libraryDependencies += jdbc
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"
libraryDependencies += "org.scalikejdbc" %% "scalikejdbc" % "3.3.5"
libraryDependencies += "org.skinny-framework" %% "skinny-orm" % "3.0.0",
