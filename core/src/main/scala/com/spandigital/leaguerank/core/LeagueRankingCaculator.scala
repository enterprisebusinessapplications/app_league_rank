package com.spandigital.leaguerank.core

import com.spandigital.leaguerank.model._

object LeagueRankingCaculator {
  private val winPoints = 3
  private val drawPoints = 1
  private val losePoints = 0

  private def rank(teamA: TeamResult, teamB: TeamResult) =
    if (teamA.score > teamB.score) {
      LeagueRankings.updatePoints(teamA.name, winPoints)
      LeagueRankings.updatePoints(teamB.name, losePoints)
    } else if (teamA.score < teamB.score) {
      LeagueRankings.updatePoints(teamA.name, losePoints)
      LeagueRankings.updatePoints(teamB.name, winPoints)
    } else {
      LeagueRankings.updatePoints(teamA.name, drawPoints)
      LeagueRankings.updatePoints(teamB.name, drawPoints)
    }

  def calculate(matchResults: List[MatchResult]): Map[String, Int] =
    matchResults match {
      case Nil => LeagueRankings.retrieveRankings()
      case matches =>
        matches.map(m => rank(m.teamA, m.teamB))
        LeagueRankings.retrieveRankings()
    }
}
