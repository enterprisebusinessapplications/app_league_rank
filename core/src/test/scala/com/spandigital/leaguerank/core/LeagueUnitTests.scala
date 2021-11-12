package com.spandigital.leaguerank.core

import org.scalatest.funsuite.AnyFunSuite
import com.spandigital.leaguerank.model._
import com.spandigital.leaguerank.core.League
import scala.collection.mutable.LinkedHashMap
import com.spandigital.leaguerank.model.{
  MatchResultDefaultObject,
  TeamScoreDefaultObject,
  LeagueRankDefaultObject
}

class LeagueUnitTests extends AnyFunSuite {

  test("Correctly creates new league with no point allocations") {
    val league = new League()
    assert(Seq() === league.retrieveRankings())
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

    val expectedLeagueRankings = Seq(
      LeagueRankDefaultObject.copy(1, rabbits, Points.Win),
      LeagueRankDefaultObject.copy(2, dogs, Points.Lose)
    )

    assert(expectedLeagueRankings === league.retrieveRankings())
  }

  test("Correctly allocates points to an existing team") {
    val league = new League()
    val rabbits = "Rabbits"
    val dogs = "Dogs"
    league.allocateRankings(
      List(
        MatchResultDefaultObject.copy(
          teamA = TeamScoreDefaultObject.copy(rabbits, 1),
          teamB = TeamScoreDefaultObject.copy(dogs, 0)
        )
      )
    )

    val expectedLeagueRankings = Seq(
      LeagueRankDefaultObject.copy(1, rabbits, Points.Win),
      LeagueRankDefaultObject.copy(2, dogs, Points.Lose)
    )
    assert(expectedLeagueRankings === league.retrieveRankings())
    league.allocateRankings(
      List(
        MatchResultDefaultObject.copy(
          teamA = TeamScoreDefaultObject.copy(rabbits, 0),
          teamB = TeamScoreDefaultObject.copy(dogs, 5)
        )
      )
    )

    val updatedExpectedLeagueRankings = Seq(
      LeagueRankDefaultObject.copy(1, dogs, Points.Win),
      LeagueRankDefaultObject.copy(1, rabbits, Points.Win)
    )

    assert(updatedExpectedLeagueRankings === league.retrieveRankings())
  }

  test("Correctly orders team when there are no ties in points") {
    val league = new League()
    val rabbits = "Rabbits"
    val penguins = "Penguins"

    val expectedLeagueRankings = Seq(
      LeagueRankDefaultObject.copy(1, penguins, Points.Win),
      LeagueRankDefaultObject.copy(2, rabbits, Points.Lose)
    )

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

    val expectedLeagueRankings = Seq(
      LeagueRankDefaultObject.copy(1, tarantulas, Points.Win + Points.Win),
      LeagueRankDefaultObject
        .copy(2, lions, Points.Win + Points.Draw + Points.Draw),
      LeagueRankDefaultObject.copy(3, fcAwesome, Points.Draw),
      LeagueRankDefaultObject.copy(3, snakes, Points.Draw),
      LeagueRankDefaultObject.copy(5, grouches, Points.Lose)
    )

    assert(
      expectedLeagueRankings === league.retrieveRankings()
    )
  }

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

    val expectedLeagueRankings = """1. Tarantulas, 6 pts
2. Lions, 5 pts
3. FC Awesome, 1 pt
3. Snakes, 1 pt
5. Grouches, 0 pts"""
    val leagues = league.toString()
    assert(expectedLeagueRankings.equals(league.toString()))
  }
}
