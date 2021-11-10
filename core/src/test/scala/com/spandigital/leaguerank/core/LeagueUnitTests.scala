package com.spandigital.leaguerank.core

import org.scalatest.funsuite.AnyFunSuite
import com.spandigital.leaguerank.model._
import com.spandigital.leaguerank.core.League
import scala.collection.mutable.LinkedHashMap
import com.spandigital.leaguerank.model.{
  MatchResultDefaultObject,
  TeamScoreDefaultObject
}

class LeagueUnitTests extends AnyFunSuite {

  test("Correctly creates new league with no point allocations") {
    val league = new League()
    assert(Map[String, Int]() === league.retrieveRankings())
  }

  test("Correctly allocates points to new team") {
    val league = new League()
    val rabbits = "Rabbits"
    val dogs = "dogs"
    league.allocateRankings(
      List(
        MatchResultDefaultObject.copy(
          teamA = TeamScoreDefaultObject.copy(rabbits, 1),
          teamB = TeamScoreDefaultObject.copy(dogs, 0)
        )
      )
    )

    val expectedLeagueRankings =
      Map(rabbits -> Points.Win, dogs -> Points.Lose)

    assert(expectedLeagueRankings === league.retrieveRankings())
  }

  test("Correctly allocates points to an existing team") {
    val league = new League()
    val rabbits = "Rabbits"
    val dogs = "dogs"
    league.allocateRankings(
      List(
        MatchResultDefaultObject.copy(
          teamA = TeamScoreDefaultObject.copy(rabbits, 1),
          teamB = TeamScoreDefaultObject.copy(dogs, 0)
        )
      )
    )
    val expectedLeagueRankings =
      Map(rabbits -> Points.Win, dogs -> Points.Lose)

    assert(expectedLeagueRankings === league.retrieveRankings())

    league.allocateRankings(
      List(
        MatchResultDefaultObject.copy(
          teamA = TeamScoreDefaultObject.copy(rabbits, 0),
          teamB = TeamScoreDefaultObject.copy(dogs, 5)
        )
      )
    )

    val updatedExpectedLeagueRankings =
      Map(rabbits -> Points.Win, dogs -> Points.Win)

    assert(updatedExpectedLeagueRankings === league.retrieveRankings())
  }

  test("Correctly orders team when there are no ties in points") {
    val league = new League()
    val rabbits = "Rabbits"
    val penguins = "Penguins"

    val expectedLeagueRankings =
      Map(penguins -> Points.Win, rabbits -> (Points.Lose))

    league.allocateRankings(
      List(
        MatchResultDefaultObject.copy(
          teamA = TeamScoreDefaultObject.copy(rabbits, 0),
          teamB = TeamScoreDefaultObject.copy(penguins, 5)
        )
      )
    )

    assert(
      expectedLeagueRankings == league.retrieveRankings()
    )
  }

  test("Correctly orders team alphabetically when there are ties in points") {
    val league = new League()
    val tarantulas = "Tarantulas"
    val lions = "Lions"
    val snakes = "Snakes"
    val fcAwesome = "FC Awesome"
    val grouches = "Grouches"

    league.allocateRankings(
      List(
        MatchResultDefaultObject.copy(
          teamA = TeamScoreDefaultObject.copy(lions, 3),
          teamB = TeamScoreDefaultObject.copy(snakes, 3)
        ),
        MatchResultDefaultObject.copy(
          teamA = TeamScoreDefaultObject.copy(tarantulas, 1),
          teamB = TeamScoreDefaultObject.copy(fcAwesome, 0)
        ),
        MatchResultDefaultObject.copy(
          teamA = TeamScoreDefaultObject.copy(lions, 1),
          teamB = TeamScoreDefaultObject.copy(fcAwesome, 1)
        ),
        MatchResultDefaultObject.copy(
          teamA = TeamScoreDefaultObject.copy(tarantulas, 3),
          teamB = TeamScoreDefaultObject.copy(snakes, 1)
        ),
        MatchResultDefaultObject.copy(
          teamA = TeamScoreDefaultObject.copy(lions, 4),
          teamB = TeamScoreDefaultObject.copy(grouches, 0)
        )
      )
    )

    val expectedLeagueRankings = LinkedHashMap(
      tarantulas -> (Points.Win + Points.Win),
      lions -> (Points.Win + Points.Draw + Points.Draw),
      fcAwesome -> Points.Draw,
      snakes -> Points.Draw,
      grouches -> Points.Lose
    )

    assert(
      expectedLeagueRankings === league.retrieveRankings()
    )
  }

  //TODO come back, weird asertion error here
  test("correctly prints the league table") {
    val league = new League()
    val tarantulas = "Tarantulas"
    val lions = "Lions"
    val snakes = "Snakes"
    val fcAwesome = "FC Awesome"
    val grouches = "Grouches"

    league.allocateRankings(
      List(
        MatchResultDefaultObject.copy(
          teamA = TeamScoreDefaultObject.copy(lions, 3),
          teamB = TeamScoreDefaultObject.copy(snakes, 3)
        ),
        MatchResultDefaultObject.copy(
          teamA = TeamScoreDefaultObject.copy(tarantulas, 1),
          teamB = TeamScoreDefaultObject.copy(fcAwesome, 0)
        ),
        MatchResultDefaultObject.copy(
          teamA = TeamScoreDefaultObject.copy(lions, 1),
          teamB = TeamScoreDefaultObject.copy(fcAwesome, 1)
        ),
        MatchResultDefaultObject.copy(
          teamA = TeamScoreDefaultObject.copy(tarantulas, 3),
          teamB = TeamScoreDefaultObject.copy(snakes, 1)
        ),
        MatchResultDefaultObject.copy(
          teamA = TeamScoreDefaultObject.copy(lions, 4),
          teamB = TeamScoreDefaultObject.copy(grouches, 0)
        )
      )
    )

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
