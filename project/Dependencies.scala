import sbt._

object Dependencies {
    val scalaVersion = "2.13.7"
    
    lazy val scalaTestFunSuite = "org.scalatest" %% "scalatest-funsuite" % "3.2.10"
    lazy val mockito = "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % "test"
}