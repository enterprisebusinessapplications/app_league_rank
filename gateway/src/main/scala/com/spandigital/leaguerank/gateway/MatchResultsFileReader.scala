package com.spandigital.leaguerank.gateway

import com.spandigital.leaguerank.model.{MatchResult, TeamResult}
import scala.util.{Try, Success, Failure}
import scala.io.Source
import scala.util.Using

object MatchResultsFileReader {
  //parse file and return full universe of match resulta
  def read(filename: String): List[MatchResult] =
    Using(Source.fromFile(filename)) { reader =>
      val fileContent = reader.getLines().toList
      fileContent.map { resultLine =>
        val matchResults = resultLine.split(", ").toList
        matchResults match {
          case teamAScore :: teamBScore =>
            MatchResult(TeamResult(teamAScore), TeamResult(teamBScore.head))
          //case _ => throw new IllegalArgumentException #assumption = as per spec matchResults line is always incorrectly formatted")
        }
      }
    } match {
      case Success(result) => result
      case Failure(e) =>
        println(
          s"An errot occured parsing file $filename. exception: ${e.getMessage}"
        )
        throw e
    }
}
