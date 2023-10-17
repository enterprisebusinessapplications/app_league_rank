package com.leaguerank.gateway
import com.leaguerank.model.{MatchResult, TeamScore}

object MatchResultsParser {
  def parse(matchResults: List[String]): List[MatchResult] =
    matchResults.map { matchLine =>
      val matchResults = matchLine.split(", ").toList
      matchResults match {
        case teamAScore :: teamBScore =>
          MatchResult(TeamScore(teamAScore), TeamScore(teamBScore.head))
        case _ =>
          throw new IllegalArgumentException("invalid match results format")
      }
    }
}
