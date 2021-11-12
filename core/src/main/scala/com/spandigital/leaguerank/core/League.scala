package com.leaguerank.core

import scala.collection.mutable.LinkedHashMap
import com.leaguerank.model._

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
    val pointsGroupsDescendingOrderByPoints = pointsTable
      .groupBy { case (_, points) => points }
      .toSeq
      .sortWith(SortByPointsDescendingOrder.apply)

    val pointsSubGroupsAlphabeticalOrder =
      pointsGroupsDescendingOrderByPoints.map { case (points, pointsGroups) =>
        val p = pointsGroups
        p.toSeq.sortWith(SortByNameAlphabetically.apply)
      }

    val indexZipped = pointsSubGroupsAlphabeticalOrder.zipWithIndex

    def calculateRank(zipIndex: Int) =
      if (zipIndex != 0) {
        indexZipped(zipIndex - 1) match {
          case (pointsGroupTeamList, _) => pointsGroupTeamList.length
        }
      } else 1

    indexZipped.map { case (pointsGroups, index) =>
      pointsGroups.map { case (teamName, points) =>
        LeagueRank(
          index + calculateRank(index),
          teamName,
          points
        )
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
      .mkString(System.lineSeparator())
  }
}

private object SortByPointsDescendingOrder {
  def apply(
      pointsGroupA: (Int, LinkedHashMap[String, Int]),
      pointsGroupB: (Int, LinkedHashMap[String, Int])
  ) = {
    val (groupApoints, groupAteams) = pointsGroupA
    val (groupBpoints, groupBteams) = pointsGroupB
    groupApoints > groupBpoints
  }
}

private object SortByNameAlphabetically {
  def apply(teamA: (String, Int), teamB: (String, Int)) = {
    val (teamAName, _) = teamA
    val (teamBName, _) = teamB
    teamAName < teamBName
  }
}
