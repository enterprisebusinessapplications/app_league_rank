package com.spandigital.app_league_rank.model

import org.scalatest.funsuite.AnyFunSuite
import com.spandigital.leaguerank.model.LeagueRankings
class LeagueTableUnitTests extends AnyFunSuite {

  test("correctly prints the league table") {
    //setup table data using updatePoints
    /*val expected = """
        team1, 2 pts
        team2, 0 pt
        team3, 1 pt 
        """
    val result = LeagueRankings.toString()
    assert(result == expected)*/
  }

  test("Correctly orders team when there are not points ties") {
    /*//setup table data using updatePoints
    val expected = Map("team1" -> 1, "team2" -> 0)
    val result = LeagueRankings.retrieveRankings()
    assert(result == expected)*/
  }

  test("Correctly orders team when there are points ties") {
    /*val expected = Map("team1" -> 9, "team2" -> 0, "team3" -> 0)
    val result = LeagueRankings.retrieveRankings()
    assert(result == expected)*/

  }
}
