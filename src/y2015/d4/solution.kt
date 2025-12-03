package y2015.d4

import utils.fullMd5
import utils.readInput

fun solution() {
    val input = readInput(2015, 4).first()
    println("First valid hash: ${calculateHash(input)}")
}

private fun calculateHash(input: String): Int {
    var counter = 0
    while ("$input$counter".fullMd5().isValidHash().not()) { counter++ }
    return counter
}

private fun String.isValidHash(): Boolean = this.startsWith(prefix = "000000")