package com.spandigital.leaguerank.core

import scala.collection.mutable.{Map => MutableMap, LinkedHashMap}

case class League(pointsTable: MutableMap[String, Int] = MutableMap.empty) {
  //private val pointsTable: MutableMap[String, Int] = MutableMap()

  private def sortByPointsThenName(teamA: (String, Int), teamB: (String, Int)) = {
    val (teamAName, teamAPoints) = teamA
    val (teamBName, teamBPoints) = teamB

    if (teamAPoints == teamBPoints) teamAName < teamBName
    else teamAPoints > teamBPoints
  }

  def newLeague(): Unit = pointsTable.clear()

  def allocatePoints(teamName: String, points: Int): Unit =
    if (pointsTable.contains(teamName)) pointsTable(teamName) = pointsTable(teamName) + points
    else pointsTable(teamName) = points

  def retrieveRankings(): LinkedHashMap[String, Int] =
    LinkedHashMap(pointsTable.toSeq.sortWith(sortByPointsThenName): _*)

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
