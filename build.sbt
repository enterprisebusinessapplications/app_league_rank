import sbtassembly.AssemblyPlugin.defaultShellScript

lazy val Model: Project = Project("model", file("model"))
  .settings(Settings.BaseProject: _*)
  .disablePlugins(sbtassembly.AssemblyPlugin)
  .settings(
    name := "model",
    libraryDependencies ++= Seq(
      Dependencies.scalaTestFunSuite % Test
    )
  )

lazy val Gateway: Project = Project("gateway", file("gateway"))
  .dependsOn(Model % "compile->compile;test->test")
  .settings(Settings.BaseProject: _*)
  .disablePlugins(sbtassembly.AssemblyPlugin)
  .settings(
    name := "gateway",
    libraryDependencies ++= Seq(
      Dependencies.scalaTestFunSuite % Test
    )
  )

lazy val Core: Project = Project("core", file("core"))
  .dependsOn(Model % "compile->compile;test->test")
  .settings(Settings.BaseProject: _*)
  .disablePlugins(sbtassembly.AssemblyPlugin)
  .settings(
    name := "core",
    libraryDependencies ++= Seq(
      Dependencies.scalaTestFunSuite % Test
    )
  )

lazy val Main: Project = Project("main", file("main"))
  .dependsOn(Model, Gateway, Core) 
  .settings(Settings.BaseProject: _*)
  .settings(Settings.Assembly: _*)
  .enablePlugins(sbtassembly.AssemblyPlugin)
  .settings(
    name := "main",
    assembly / assemblyJarName := "league_rank.jar",
    assembly / assemblyOutputPath := file("release/league_rank.jar"),
    assembly / assemblyOption := (assemblyOption in assembly).value
      .copy(prependShellScript = Some(defaultShellScript)),
    libraryDependencies ++= Seq(
      Dependencies.scalaTestFunSuite % Test
    )
  )

lazy val leaguerank: Project = project
  .in(file("."))
  .aggregate(Model, Gateway, Core, Main)
  .disablePlugins(sbtassembly.AssemblyPlugin)
  .settings(
    scalaVersion := Dependencies.scalaVersion
  )
