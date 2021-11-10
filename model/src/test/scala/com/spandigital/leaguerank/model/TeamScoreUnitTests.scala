package com.spandigital.leaguerank.model

import org.scalatest.funsuite.AnyFunSuite
import com.spandigital.leaguerank.model.TeamScore
import com.spandigital.leaguerank.model.TeamScoreDefaultObject

class TeamScoreUnitTests extends AnyFunSuite {

  test("can handle single word team name and score") {
    val teamMatchScore = "Lions 3"
    val teamResult = TeamScore(teamMatchScore)
    assert(
      TeamScoreDefaultObject.copy(name = "Lions", score = 3) == teamResult
    )
  }

  test("can handle team name with more than 2 words and score") {
    val teamMatchScore = "FC Awesome 5"
    val teamResult = TeamScore(teamMatchScore)
    assert(
      TeamScoreDefaultObject.copy(name = "FC Awesome", score = 5) == teamResult
    )
  }
}
