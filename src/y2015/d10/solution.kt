package y2015.d10

import utils.readInput
import utils.withStopwatch

fun solution() {
    val input = readInput(2015, 10).first()

    withStopwatch { println("Resolved sequence ${resolveSequence(input, 50).length}") }
}

private fun resolveSequence(input: String, n: Int): String {
    var current = input
    repeat(n) { current = current.applyTransformation() }
    return current
}

private fun String.applyTransformation(): String {
    var current = this
    var result = ""

    while (current.isNotEmpty()) {
        val char = current.first()
        val indexOfNext = current.indexOfFirst { it != char }

        if (indexOfNext == -1) {
            result += "${current.length}$char"
            current = ""
        } else {
            result += "$indexOfNext$char"
            current = current.drop(indexOfNext)
        }
    }

    return result
}