lazy val Model: Project = Project("model", file("model"))
    .settings(Settings.BaseProject: _*)
    .disablePlugins(sbtassembly.AssemblyPlugin)
    .settings(
        name := "model",
        libraryDependencies ++= Seq(Dependencies.scalaTest)
    )

lazy val Gateway: Project = Project("gateway", file("gateway"))
    .dependsOn(Model)
    .settings(Settings.BaseProject: _*)
    .disablePlugins(sbtassembly.AssemblyPlugin)
    .settings(
        name := "gateway",
        libraryDependencies ++= Seq(Dependencies.scalaTest)
    )

lazy val Core: Project = Project("core", file("core"))
    .dependsOn(Model)
    .settings(Settings.BaseProject: _*)
    .disablePlugins(sbtassembly.AssemblyPlugin)
    .settings(
        name := "core",
        libraryDependencies ++= Seq(Dependencies.scalaTest)
    )

lazy val Main: Project = Project("main", file("main"))
    .dependsOn(Model)
    .settings(Settings.BaseProject: _*)
    .settings(Settings.Assembly: _*)
    .enablePlugins(sbtassembly.AssemblyPlugin)
    .settings(
        name := "main",
        assemblyJarName in assembly := "service.jar",
        assemblyOutputPath in assembly := file("dist/service.jar"),
        //Do not include Scala library classes as they are are included in the Scala runtime
        assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
    )

lazy val app_league_rank: Project = project.in(file("."))
    .aggregate(
        Model,
        Gateway,
        Core,
        Main)
    .disablePlugins(sbtassembly.AssemblyPlugin)
    .settings(
        scalaVersion := Dependencies.scalaVersion,
        aggregate in update := false)