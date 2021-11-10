package com.spandigital.leaguerank.core

import scala.collection.mutable.{Map => MutableMap, LinkedHashMap}
import com.spandigital.leaguerank.model._
class League() {
  private val pointsTable: MutableMap[String, Int] = MutableMap.empty

  private def allocatePoints(teamA: TeamResult, teamB: TeamResult) = {
    def setPoints(teamName: String, points: Int): Unit =
      if (pointsTable.contains(teamName))
        pointsTable(teamName) = pointsTable(teamName) + points
      else pointsTable(teamName) = points

    if (teamA.score > teamB.score) {
      setPoints(teamA.name, Points.Win)
      setPoints(teamB.name, Points.Lose)
    } else if (teamA.score < teamB.score) {
      setPoints(teamA.name, Points.Lose)
      setPoints(teamB.name, Points.Win)
    } else {
      setPoints(teamA.name, Points.Draw)
      setPoints(teamB.name, Points.Draw)
    }
  }

  def retrieveRankings(): LinkedHashMap[String, Int] = {
    def sortByPointsThenName(teamA: (String, Int), teamB: (String, Int)) = {
      val (teamAName, teamAPoints) = teamA
      val (teamBName, teamBPoints) = teamB

      if (teamAPoints == teamBPoints) teamAName < teamBName
      else teamAPoints > teamBPoints
    }
    LinkedHashMap(pointsTable.toSeq.sortWith(sortByPointsThenName): _*)
  }

  def allocateRankings(
      matches: List[MatchResult]
  ): LinkedHashMap[String, Int] = {
    matches.map(m => allocatePoints(m.teamA, m.teamB))
    retrieveRankings()
  }

  override def toString(): String = {
    def pointsSuffix(points: Int): String =
      if (points > 1 || points == 0) "pts"
      else "pt"

    def convert(number: Int, teampoints: (String, Int)) = {
      val (teamName, points) = teampoints
      s"$number. $teamName, $points ${pointsSuffix(points)}"
    }
    retrieveRankings().zipWithIndex
      .map { case (teampoints, index) => convert(index + 1, teampoints) }
      .mkString("\n")
  }
}
