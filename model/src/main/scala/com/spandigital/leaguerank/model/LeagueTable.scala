package com.spandigital.leaguerank.model

import scala.collection.mutable.{Map => MutableMap, LinkedHashMap}

object LeagueRankings {
  private val table: MutableMap[String, Int] = MutableMap()

  private def sortByPointsThenName(
      teamA: (String, Int),
      teamB: (String, Int)
  ) = {
    val (teamAName, teamAPoints) = teamA
    val (teamBName, teamBPoints) = teamB

    if (teamAPoints == teamBPoints) teamAName < teamBName
    else teamAPoints > teamBPoints
  }

  def newLeague(): Unit = table.clear()

  def allocatePoints(name: String, points: Int): Unit =
    if (table.contains(name)) table(name) = table(name) + points
    else table(name) = points

  def retrieveRankings(): LinkedHashMap[String, Int] =
    LinkedHashMap(table.toSeq.sortWith(sortByPointsThenName): _*)

  def printRankings(): String = {
    def pointsSuffix(points: Int): String =
      if (points > 1 || points == 0) "pts"
      else "pt"

    def convert(number: Int, teampoints: (String, Int)) = {
      val (name, points) = teampoints
      s"$number. $name, $points ${pointsSuffix(points)}"
    }

    val c = "\n" +
      retrieveRankings().zipWithIndex
      .map { case (teampoints, index) => convert(index + 1, teampoints) }
      .mkString("\n")
    
    c
  }
  //print as per requirements
  /*
    if points > 1 or points == 0 then Name, points pts
    else then Name, points pt
   */
}
