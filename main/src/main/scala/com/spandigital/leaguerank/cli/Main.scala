package com.spandigital.leaguerank.cli

import com.spandigital.leaguerank.gateway.MatchResultsFileReader
import com.spandigital.leaguerank.core.LeagueRankingCaculator
object Main extends App {
  try {
    if (args.length == 0)
      println("Please provide a filename.")
    else if (args.length > 1)
      println("Please provide one filename.")
    else {
      val matchResultsFileName = args(0)
      println("Commencing league rank calculation...")
      val matchResults = MatchResultsFileReader.read(matchResultsFileName)
      val leagueTable = LeagueRankingCaculator.calculate(matchResults)
      print(leagueTable)
      println("Done")
    }
  } catch {
    case e: Exception =>
      println(
        "failed during the execution of ranking teams. Please refer to stack trace below"
      )
      e.printStackTrace()
  }
}
