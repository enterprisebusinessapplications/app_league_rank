package com.spandigital.leaguerank.cli

import org.scalatest.funsuite.AnyFunSuite
import com.spandigital.leaguerank.cli.Main

//TODO implement the tests!
class MainUnitTests extends AnyFunSuite {
  test("logs correct message when no filename parameter supplied.") {
    Main.main(Array())
    //verify that message is loggedd usisng Console.println()
    //assert(result === "Please provide a filename.")
  }

  test("logs correct message when more than 1 filename is supplied.") {
    Main.main(Array("filename1.txt", "filename2.txt"))
    //verify that message is loggedd using Console.println()
    //assert(result === "Please provide a filename.")
  }

  test(
    "correctly ochestrates taking in file names and delegating to correct objects"
  ) {
    //val result = Main.main(Array("filename1.txt"))
    //verify that orchestration happens correctly
  }
}
