enablePlugins(ScalaJSPlugin)
//enablePlugins(ScalaJSBundlerPlugin)

name := "animaxe-examples"
version := "0.1"
scalaVersion := "2.12.8"


//testFrameworks += new TestFramework("utest.runner.Framework")

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.6",
  //"com.lihaoyi" %%% "utest" % "0.4.5" % "test",
  //"com.github.japgolly.scalajs-react" %%% "core" % "1.4.1",
)

/*
npmDependencies in Compile ++= Seq(
  "react" -> "16.7.0",
  "react-dom" -> "16.7.0",
)*/

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

// On plane
// libraryDependencies += "org.typelevel" %% "cats-core" % "1.6.0"
// libraryDependencies += "org.typelevel" %% "cats-free" % "1.6.0"

scalacOptions += "-Ypartial-unification"
