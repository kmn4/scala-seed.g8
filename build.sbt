scalaVersion := "2.13.6"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test"

scalacOptions ++= Seq(
  "-encoding", "utf8",
  "-Xfatal-warnings",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xlint",
  "-Wdead-code",
  "-language:implicitConversions"
)

Compile / console / scalacOptions --= Seq(
  "-Xlint",
  "-Xfatal-warnings"
)

run / fork := true
Test / fork := true
Global / cancelable := true

assembly / test := (Test / test).value

Compile / scalaSource := baseDirectory.value / "src"
Test / scalaSource := baseDirectory.value / "test"
