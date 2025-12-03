package y2015.d2

import utils.min
import utils.readInput

fun solution() {
    val input = readInput(2015, 2)
    println("Wrapping paper size: ${calculatePaperSize(input)}")
    println("Ribbon size: ${calculateRibbonSize(input)}")
}

private fun calculatePaperSize(input: List<String>): Int {
    return input.fold(0) { acc, paper -> acc + singlePaperSize(paper) }
}

private fun singlePaperSize(input: String): Int {
    val (length, width, height) = input.split('x').map { it.toInt() }
    val s1 = length * width
    val s2 = width * height
    val s3 = height * length
    return 2 * s1 + 2 * s2 + 2 * s3 + min(s1, s2, s3)
}

private fun calculateRibbonSize(input: List<String>): Int {
    return input.fold(0) { acc, paper -> acc + singleRibbonSize(paper) }
}

private fun singleRibbonSize(input: String): Int {
    val (length, width, height) = input.split('x').map { it.toInt() }.sorted()

    return 2 * length + 2 * width + length * width * height
}