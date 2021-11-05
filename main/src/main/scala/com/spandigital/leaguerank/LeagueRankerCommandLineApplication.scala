package com.spandigital.leaguerank

import com.spandigital.leaguerank.gateway.MatchResultsFileReader
import com.spandigital.leaguerank.core.LeagueRankingService
object LeagueRankerCommandLineApplication extends App {
    
    println("I am ready to rank teams")
    val matchReults = MatchResultsFileReader.read("file")
    val leaguaeRankintTable = LeagueRankingService.calculateLeagueRankings(matchReults)
    //format and print results in desired format
}