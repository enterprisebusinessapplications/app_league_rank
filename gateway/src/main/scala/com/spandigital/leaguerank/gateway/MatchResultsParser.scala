package com.spandigital.leaguerank.gateway
import com.spandigital.leaguerank.model.{MatchResult, TeamResult}

object MatchResultsParser {
  def parse(matchResults: List[String]): List[MatchResult] =
    matchResults.map { matchLine =>
      val matchResults = matchLine.split(", ").toList
      matchResults match {
        case teamAScore :: teamBScore =>
          MatchResult(TeamResult(teamAScore), TeamResult(teamBScore.head))
        case _ =>
          throw new IllegalArgumentException("invalid match results format")
      }
    }
}
