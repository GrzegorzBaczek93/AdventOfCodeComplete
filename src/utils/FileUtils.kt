package utils

import java.io.File

/**
 * Reads lines from the given input txt file.
 */
fun readInput(year: Int, day: Int, name: String = "input") =
    File("src/y$year/d$day/", "$name.txt").readLines()
