scalaVersion := "3.0.0"

initialCommands in console := """
  println("Hello Scala 3!")
"""

scalacOptions ++= Seq(
  "-encoding",
  "utf8",
  "-source",
  "future", // remove this if you want to allow old Scala 2 syntax
  "-Xfatal-warnings",
  "-deprecation",
  "-unchecked"
)

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % "1.0.11",
  "dev.zio" %% "zio-streams" % "1.0.11",
  "dev.zio" %% "zio-interop-cats" % "2.5.1.0",
  "com.github.ghostdogpr" %% "caliban" % "1.1.0",
  "com.github.ghostdogpr" %% "caliban-zio-http" % "1.1.0",
  "org.tpolecat" %% "doobie-core" % "0.13.4",
  "org.tpolecat" %% "doobie-postgres" % "0.13.4",
  "com.github.pureconfig" %% "pureconfig-core" % "0.16.0"
)

fork := true
connectInput := true
outputStrategy := Some(StdoutOutput)
