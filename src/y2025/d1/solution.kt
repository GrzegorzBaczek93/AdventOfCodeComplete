package y2025.d1

import utils.readInput

fun solution() {
    val input = readInput(2025, 1)
    println(count(input))
}

private fun count(input: List<String>): Int {
    var currentPosition = 50

    return input.fold(0) { acc, line ->
        val (position, zeros) = calculatePosition(initialPosition = currentPosition, clicks = line.parse())
        currentPosition = position
        acc + zeros
    }
}

private fun String.parse(): Int {
    val isLeftRotation = this.first() == 'L'
    val clicks = this.filter { it.isDigit() }.toInt()
    return if (isLeftRotation) clicks * -1 else clicks
}

private fun calculatePosition(initialPosition: Int, clicks: Int): Pair<Int, Int> {
    var zeros = 0
    var result = initialPosition
    var remaining = clicks

    if (clicks > 0) {
        while (remaining > 0) {
            if (result + remaining > 99) {
                zeros += 1
                remaining -= (100 - result)
                result = 0
            } else {
                result += remaining
                remaining = 0
            }
        }
    } else {
        while (remaining < 0) {
            if (result + remaining < 0) {
                if (result != 0) zeros += 1
                remaining += result
                result = 100
            } else {
                result += remaining
                remaining = 0
            }
        }
        if (result == 0) zeros += 1
    }

    return result to zeros
}
