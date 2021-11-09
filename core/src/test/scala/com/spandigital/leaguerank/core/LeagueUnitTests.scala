package com.spandigital.app_league_rank.model

import org.scalatest.funsuite.AnyFunSuite
import com.spandigital.leaguerank.model._
import com.spandigital.leaguerank.core.League
import scala.collection.mutable.LinkedHashMap

class LeagueUnitTests extends AnyFunSuite {

  test("Correctly creates new league with no point allocations") {
    val league = League()
    assert(Map[String, Int]() === league.retrieveRankings())
  }

  test("Correctly allocates points to new team") {
    val league = League()
    val rabbits = "Rabbits"
    league.allocatePoints(rabbits, Points.Draw)

    val expectedLeagueRankings = Map(rabbits -> Points.Draw)

    assert(expectedLeagueRankings === league.retrieveRankings())
  }

  test("Correctly allocates points to an existing team") {
    val league = League()
    val rabbits = "Rabbits"
    league.allocatePoints(rabbits, Points.Draw)
    league.allocatePoints(rabbits, Points.Win)

    val expectedLeagueRankings = Map(rabbits -> (Points.Draw + Points.Win))

    assert(expectedLeagueRankings === league.retrieveRankings())
  }

  test("Correctly orders team when there are no point ties") {
    val league = League()

    val rabbits = "Rabbits"
    league.allocatePoints(rabbits, Points.Lose)

    val penguins = "Penguins"
    league.allocatePoints(penguins, Points.Win)

    val expectedLeagueRankings =
      Map(penguins -> Points.Win, rabbits -> (Points.Lose))

    assert(
      expectedLeagueRankings.toSeq == league.retrieveRankings().toSeq
    )
  }

  test("Correctly orders team alphabetically when there are points ties") {
    val league = League()
    val tarantulas = "Tarantulas"
    league.allocatePoints(tarantulas, (Points.Win + Points.Win))

    val lions = "Lions"
    league.allocatePoints(
      lions,
      (Points.Win + Points.Draw + Points.Draw)
    )

    val snakes = "Snakes"
    league.allocatePoints(snakes, Points.Draw)

    val fcAwesome = "FC Awesome"
    league.allocatePoints(fcAwesome, Points.Draw)

    val grouches = "Grouches"
    league.allocatePoints(grouches, Points.Lose)

    val expectedLeagueRankings = LinkedHashMap(
      tarantulas -> (Points.Win + Points.Win),
      lions -> (Points.Win + Points.Draw + Points.Draw),
      fcAwesome -> Points.Draw,
      snakes -> Points.Draw,
      grouches -> Points.Lose
    )

    assert(
      expectedLeagueRankings.toSeq === league.retrieveRankings().toSeq
    )
  }

  //TODO come back, weird asertion error here
  test("correctly prints the league table") {
    val league = League()
    val tarantulas = "Tarantulas"
    league.allocatePoints(tarantulas, (Points.Win + Points.Win))

    val lions = "Lions"
    league.allocatePoints(
      lions,
      (Points.Win + Points.Draw + Points.Draw)
    )

    val snakes = "Snakes"
    league.allocatePoints(snakes, Points.Draw)

    val fcAwesome = "FC Awesome"
    league.allocatePoints(fcAwesome, Points.Draw)

    val grouches = "Grouches"
    league.allocatePoints(grouches, Points.Lose)

    val expectedLeagueRankings =
      """
        1. Tarantulas, 6 pts
        2. Lions, 5 pts
        3. FC Awesome, 1 pt
        3. Snakes, 1 pt
        5. Grouches, 0 pts"""

    assert(expectedLeagueRankings === league)
  }
}
