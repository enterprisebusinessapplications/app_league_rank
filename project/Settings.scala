
import sbtassembly.AssemblyPlugin.autoImport._
import sbt.Keys._
import sbt._
import scoverage.ScoverageKeys._

//TODO move to / (slash) syntax
object Settings {
    val DefaultOrganization = "com.spandigital"

    lazy val BaseProject = Seq(
        organization := DefaultOrganization,
        version := "1.0.0",
        scalaVersion := Dependencies.scalaVersion,
        scalacOptions ++= Seq("-deprecation"),
        coverageMinimum := 80,
        coverageFailOnMinimum := true
    )

    lazy val Assembly = Seq(
        isSnapshot := false,

        logLevel in assembly := Level.Error,
        //test in assembly := {},
        //What are these for again?
        mainClass in assembly := None,
        mainClass in packageBin := None,
        mainClass in run := None
        //done

    )
}
