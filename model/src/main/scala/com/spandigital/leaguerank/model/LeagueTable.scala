package com.spandigital.leaguerank.model

import scala.collection.mutable.{Map => MutableMap}

object LeagueRankings {
  private val table: MutableMap[String, Int] = MutableMap()

  /*
        Logic
          if team A points == team B points
               teamA.name < teamB.name
         else
                teamA.points > teamB.points
   */
  private def sortByPointsThenName(teamA: (String, Int),teamB: (String, Int)) = {
    val (teamAName, teamAPoints) = teamA
    val (teamBName, teamBPoints) = teamB

    if (teamAPoints == teamBPoints) teamAName < teamBName
    else teamAPoints < teamBPoints
  }

  def updatePoints(name: String, points: Int): Unit =
    if (table.contains(name)) table(name) = table(name) + points
    else table(name) = points

  def retrieveRankings(): Map[String, Int] =
    table.toSeq.sortWith(sortByPointsThenName).toMap

  override def toString: String = ??? 
  //print as per requirements
  /*
    if points > 1 or points == 0 then Name, points pts
    else then Name, points pt
  */
}
