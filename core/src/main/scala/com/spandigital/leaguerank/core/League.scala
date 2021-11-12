package com.spandigital.leaguerank.core

import scala.collection.mutable.LinkedHashMap
import com.spandigital.leaguerank.model._
class League() {
  private val pointsTable: LinkedHashMap[String, Int] = LinkedHashMap.empty

  private def allocatePoints(teamA: TeamScore, teamB: TeamScore) = {
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

  def retrieveRankings(): Seq[LeagueRank] = {
    def sortByNameAlphabetically(teamA: (String, Int), teamB: (String, Int)) = {
      val (teamAName, _) = teamA
      val (teamBName, _) = teamB
      teamAName < teamBName
    }

    def sortByPointsDescendingOrder(
        pointsGroup_1: (Int, LinkedHashMap[String, Int]),
        pointsGroup_2: (Int, LinkedHashMap[String, Int])
    ) = {
      val (groupApoints, groupAteams) = pointsGroup_1
      val (groupBpoints, groupBteams) = pointsGroup_2
      groupApoints > groupBpoints
    }

    val pointsGroupsDescendingOrderByPoints = pointsTable
      .groupBy { case (_, points) => points }
      .toSeq
      .sortWith(sortByPointsDescendingOrder)

    val pointsSubGroupsAlphabeticalOrder =
      pointsGroupsDescendingOrderByPoints.map { case (points, pointsGroups) =>
        val p = pointsGroups
        p.toSeq.sortWith(sortByNameAlphabetically)
      }

    pointsSubGroupsAlphabeticalOrder.zipWithIndex.map {
      case (pointsGroups, index) =>
        pointsGroups.map { case (teamName, points) =>
          LeagueRank(index + 1, teamName, points)
        }
    }.flatten
  }

  def allocateRankings(matches: List[MatchResult]): Seq[LeagueRank] = {
    matches.map(m => allocatePoints(m.teamA, m.teamB))
    retrieveRankings()
  }

  override def toString(): String = {
    def pointsSuffix(points: Int): String =
      if (points > 1 || points == 0) "pts"
      else "pt"

    def convert(number: Int, teamName: String, points: Int) =
      s"$number. $teamName, $points ${pointsSuffix(points)}"

    retrieveRankings()
      .map(lr => convert(lr.rank, lr.teamName, lr.points))
      .mkString("\n")
  }
}
