package com.spandigital.leaguerank.core

import com.spandigital.leaguerank.model._
import scala.collection.mutable.LinkedHashMap
object LeagueRankingCaculator {

  private def rank(teamA: TeamResult, teamB: TeamResult) =
    if (teamA.score > teamB.score) {
      LeagueRankings.allocatePoints(teamA.name, Points.win)
      LeagueRankings.allocatePoints(teamB.name, Points.lose)
    } else if (teamA.score < teamB.score) {
      LeagueRankings.allocatePoints(teamA.name, Points.lose)
      LeagueRankings.allocatePoints(teamB.name, Points.win)
    } else {
      LeagueRankings.allocatePoints(teamA.name, Points.draw)
      LeagueRankings.allocatePoints(teamB.name, Points.draw)
    }

  def calculate(matchResults: List[MatchResult]): LinkedHashMap[String, Int] =
    matchResults match {
      case Nil => LeagueRankings.retrieveRankings()
      case matches =>
        matches.map(m => rank(m.teamA, m.teamB))
        LeagueRankings.retrieveRankings()
    }
}
