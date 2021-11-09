package com.spandigital.leaguerank.core

import com.spandigital.leaguerank.model._
import scala.collection.mutable.LinkedHashMap

case class LeagueService(league: League) {

  private def allocatePoints(teamA: TeamResult, teamB: TeamResult) =
    if (teamA.score > teamB.score) {
      league.allocatePoints(teamA.name, Points.Win)
      league.allocatePoints(teamB.name, Points.Lose)
    } else if (teamA.score < teamB.score) {
      league.allocatePoints(teamA.name, Points.Lose)
      league.allocatePoints(teamB.name, Points.Win)
    } else {
      league.allocatePoints(teamA.name, Points.Draw)
      league.allocatePoints(teamB.name, Points.Draw)
    }

  def calculateRankings(matches: List[MatchResult]): LinkedHashMap[String, Int] = {
      matches match {
      case Nil => league.retrieveRankings()
      case matches =>
        matches.map(m => allocatePoints(m.teamA, m.teamB))
        league.retrieveRankings()
    }
  }

  def printLeagueRankings(): Unit = print(league)

}
