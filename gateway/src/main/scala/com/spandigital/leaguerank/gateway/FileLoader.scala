package com.spandigital.leaguerank.gateway

import com.spandigital.leaguerank.model.{MatchResult, TeamResult}
import scala.util.{Try, Success, Failure}
import scala.io.Source
import scala.util.Using

object FileLoader {
  def loadFile(filename: String): List[String] =
    Using(Source.fromFile(filename)) { reader =>
      reader.getLines().toList
    } match {
      case Success(result) => result
      case Failure(e) =>
        println(
          s"An errot occured loading file $filename. exception: ${e.getMessage}"
        )
        throw e
    }
}
