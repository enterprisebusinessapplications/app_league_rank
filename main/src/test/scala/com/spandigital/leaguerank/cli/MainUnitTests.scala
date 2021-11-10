package com.spandigital.leaguerank.cli

import org.scalatest.funsuite.AnyFunSuite
import com.spandigital.leaguerank.cli.Main

class MainUnitTests extends AnyFunSuite {
  test("prints correct message when no filename parameter supplied.") {
    val outputStream = new java.io.ByteArrayOutputStream()
    Console.withOut(outputStream) {
      Main.main(Array())
    }

    val expectedMessage = "Please provide a filename."

    assert(outputStream.toString().contains(expectedMessage))
  }

  test("prints correct message when more than 1 filename is supplied.") {
    val outputStream = new java.io.ByteArrayOutputStream()
    Console.withOut(outputStream) {
      Main.main(Array("filename1.txt", "filename2.txt"))
    }

    val expectedMessage = "Please provide one filename."

    assert(outputStream.toString().contains(expectedMessage))
  }

  test("correctly handles FileNotFoundException.") {
    val expectedMessage =
      "File not found, please ensure the file is in the same directory as the program. Provided filename was: invalid.txt."
    val outputStream = new java.io.ByteArrayOutputStream()
    Console.withOut(outputStream) {
      Main.main(Array("invalid.txt"))
    }
    assert(outputStream.toString().contains(expectedMessage))
  }
}
