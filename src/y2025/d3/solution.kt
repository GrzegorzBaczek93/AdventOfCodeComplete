package y2025.d3

import utils.readInput

fun solution() {
    val input = readInput(2025, 3)
    println(maxPower(input))
}

private fun maxPower(input: List<String>): Long {
    return input.fold(0L) { acc, line ->
        acc + line.maxPower(12)
    }
}

private fun String.maxPower(size: Int): Long {
    var stringNumber = ""
    var indexOfStart = 0

    (size - 1 downTo 0).forEach { i ->
        val chunk = this.slice(indexOfStart..this.lastIndex - i)
        val max = chunk.maxOf { it.digitToInt() }
        indexOfStart += chunk.indexOfFirst { it.digitToInt() == max } + 1
        stringNumber += max
    }

    return stringNumber.toLong()
}