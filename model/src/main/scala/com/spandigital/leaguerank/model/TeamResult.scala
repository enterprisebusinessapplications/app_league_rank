package com.spandigital.leaguerank.model

case class TeamResult(name: String, score: Int)

object TeamResult {
  val alphaNumbericPattern = "([a-zA-Z- ]*) ([0-9]*)".r
  def apply(teamScore: String): TeamResult = teamScore match {
    case alphaNumbericPattern(team, score) => TeamResult(team, score.toInt)
    //case _ => throw new IllegalArgumentException("invalid team result format") # assumption = as per spec teamResult entry is always valid
  }
}
