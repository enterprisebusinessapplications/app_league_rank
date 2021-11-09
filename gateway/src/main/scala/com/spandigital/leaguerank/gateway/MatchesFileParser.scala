package com.spandigital.leaguerank.gateway

import com.spandigital.leaguerank.model.{MatchResult, TeamResult}
import scala.util.{Try, Success, Failure}
import scala.io.Source
import scala.util.Using

object MatchesFileParser {
  def loadFile(filename: String): List[MatchResult] =
    Using(Source.fromFile(filename)) { reader =>
      val fileContent = reader.getLines().toList
      fileContent.map { resultLine =>
        val matchResults = resultLine.split(", ").toList
        matchResults match {
          case teamAScore :: teamBScore =>
            MatchResult(TeamResult(teamAScore), TeamResult(teamBScore.head))
          case _ => throw new IllegalArgumentException("invalid match results format")
        }
      }
    } match {
      case Success(result) => result
      case Failure(e) =>
        println(s"An errot occured parsing file $filename. exception: ${e.getMessage}")
        throw e
    }
}
