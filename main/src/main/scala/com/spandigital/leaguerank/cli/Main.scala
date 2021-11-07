package com.spandigital.leaguerank.cli

import com.spandigital.leaguerank.gateway.MatchResultsFileReader
import com.spandigital.leaguerank.core.LeagueRankingCaculator
object Main extends App {
  if (args.length == 0) 
    println("Please provide a filename.")
  else if (args.length > 1)
    println("Please provide one filename.")
  else {
    val matchResultsFileName = args(0)
    println("Commencing league rank calculation...")
    //TODO what about exception handling here, leave it in the reader?
    val matchReults = MatchResultsFileReader.read(matchResultsFileName)
    val leaguaeRankintTable = LeagueRankingCaculator.calculate(matchReults)
    //format and print results in desired format
  }
}
