package com.leaguerank.model

import org.scalatest.funsuite.AnyFunSuite
import com.leaguerank.model.TeamScore
import com.leaguerank.model.TeamScoreDefaultObject

class TeamScoreUnitTests extends AnyFunSuite {

  test("can handle single word team name and score") {
    val teamMatchScore = "Lions 3"
    val teamScore = TeamScore(teamMatchScore)
    assert(
      TeamScoreDefaultObject.copy(name = "Lions", score = 3) == teamScore
    )
  }

  test("can handle team name with 2 words and score") {
    val teamMatchScore = "FC Awesome 5"
    val teamScore = TeamScore(teamMatchScore)
    assert(
      TeamScoreDefaultObject.copy(name = "FC Awesome", score = 5) == teamScore
    )
  }

  test("can handle team name with more than 2 words and score") {
    val teamMatchScore = "FC Awesome Players 5"
    val teamScore = TeamScore(teamMatchScore)
    assert(
      TeamScoreDefaultObject.copy(
        name = "FC Awesome Players",
        score = 5
      ) == teamScore
    )
  }
}
