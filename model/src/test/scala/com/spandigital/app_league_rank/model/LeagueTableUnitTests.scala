package com.spandigital.app_league_rank.model

import org.scalatest.funsuite.AnyFunSuite
import com.spandigital.leaguerank.model._
import scala.collection.mutable.LinkedHashMap

class LeagueTableUnitTests extends AnyFunSuite {

  test("Correctly creates new league with no point allocations") {
    LeagueRankings.newLeague()

    val rabbits = "Rabbits"
    LeagueRankings.allocatePoints(rabbits, Points.draw)
    LeagueRankings.allocatePoints(rabbits, Points.win)
    val expectedLeagueRankings = Map(rabbits -> (Points.draw + Points.win))

    assert(expectedLeagueRankings === LeagueRankings.retrieveRankings())

    LeagueRankings.newLeague()

    assert(Map[String, Int]() === LeagueRankings.retrieveRankings())
  }

  test("Correctly allocates points to new team") {
    LeagueRankings.newLeague()

    val rabbits = "Rabbits"
    LeagueRankings.allocatePoints(rabbits, Points.draw)

    val expectedLeagueRankings = Map(rabbits -> Points.draw)

    assert(expectedLeagueRankings === LeagueRankings.retrieveRankings())
  }

  test("Correctly allocates points to an existing team") {
    LeagueRankings.newLeague()

    val rabbits = "Rabbits"
    LeagueRankings.allocatePoints(rabbits, Points.draw)
    LeagueRankings.allocatePoints(rabbits, Points.win)

    val expectedLeagueRankings = Map(rabbits -> (Points.draw + Points.win))

    assert(expectedLeagueRankings === LeagueRankings.retrieveRankings())
  }

  test("Correctly orders team when there are no point ties") {
    LeagueRankings.newLeague()

    val rabbits = "Rabbits"
    LeagueRankings.allocatePoints(rabbits, Points.lose)

    val penguins = "Penguins"
    LeagueRankings.allocatePoints(penguins, Points.win)

    val expectedLeagueRankings =
      Map(penguins -> Points.win, rabbits -> (Points.lose))

    assert(
      expectedLeagueRankings.toSeq == LeagueRankings.retrieveRankings().toSeq
    )
  }

  test("Correctly orders team alphabetically when there are points ties") {
    LeagueRankings.newLeague()

    val tarantulas = "Tarantulas"
    LeagueRankings.allocatePoints(tarantulas, (Points.win + Points.win))

    val lions = "Lions"
    LeagueRankings.allocatePoints(
      lions,
      (Points.win + Points.draw + Points.draw)
    )

    val snakes = "Snakes"
    LeagueRankings.allocatePoints(snakes, Points.draw)

    val fcAwesome = "FC Awesome"
    LeagueRankings.allocatePoints(fcAwesome, Points.draw)

    val grouches = "Grouches"
    LeagueRankings.allocatePoints(grouches, Points.lose)

    val expectedLeagueRankings = LinkedHashMap(
      tarantulas -> (Points.win + Points.win),
      lions -> (Points.win + Points.draw + Points.draw),
      fcAwesome -> Points.draw,
      snakes -> Points.draw,
      grouches -> Points.lose
    )

    assert(
      expectedLeagueRankings.toSeq === LeagueRankings.retrieveRankings().toSeq
    )
  }

  //TODO come back, weird asertion error here
  test("correctly prints the league table") {
    LeagueRankings.newLeague()

    val tarantulas = "Tarantulas"
    LeagueRankings.allocatePoints(tarantulas, (Points.win + Points.win))

    val lions = "Lions"
    LeagueRankings.allocatePoints(
      lions,
      (Points.win + Points.draw + Points.draw)
    )

    val snakes = "Snakes"
    LeagueRankings.allocatePoints(snakes, Points.draw)

    val fcAwesome = "FC Awesome"
    LeagueRankings.allocatePoints(fcAwesome, Points.draw)

    val grouches = "Grouches"
    LeagueRankings.allocatePoints(grouches, Points.lose)

    val expectedLeagueRankings =
      """
        1. Tarantulas, 6 pts
        2. Lions, 5 pts
        3. FC Awesome, 1 pt
        3. Snakes, 1 pt
        5. Grouches, 0 pts"""

    assert(expectedLeagueRankings === LeagueRankings.printRankings())
  }
}
