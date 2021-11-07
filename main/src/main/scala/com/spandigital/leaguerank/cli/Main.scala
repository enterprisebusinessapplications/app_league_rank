package com.spandigital.leaguerank.cli

import com.spandigital.leaguerank.gateway.MatchResultsFileReader
import com.spandigital.leaguerank.core.LeagueRankingCaculator
object Main extends App {
    
    println("I am ready to rank teams")
    val matchReults = MatchResultsFileReader.read("file")
    val leaguaeRankintTable = LeagueRankingCaculator.calculate(matchReults)
    //format and print results in desired format
}