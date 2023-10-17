package com.leaguerank.model

case class TeamScore(name: String, score: Int)

object TeamScore {
  val alphaNumbericPattern = "([a-zA-Z ]*) ([0-9]*)".r
  def apply(teamMatchScore: String): TeamScore = teamMatchScore match {
    case alphaNumbericPattern(name, score) => TeamScore(name, score.toInt)
    case _ =>
      throw new IllegalArgumentException("invalid team match score format")
  }
}
