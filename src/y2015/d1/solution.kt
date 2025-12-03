package y2015.d1

import utils.readInput

fun solution() {
    val input = readInput(2015, 1).first()
    println("Last floor: ${calculateLastFloor(input)}")
    println("Index of first basement ${positionOfFirstBasement(input)}")
}

private fun calculateLastFloor(input: String): Int {
    return input.fold(0) { acc, char -> acc + char.toDirection() }
}

private fun positionOfFirstBasement(input: String): Int {
    var acc = 0
    for (index in input.indices) {
        val direction = input[index].toDirection()
        acc += direction

        if (acc == -1) {
            return index + 1
        }
    }

    return -1
}

private fun Char.toDirection() = when (this) {
    '(' -> 1
    ')' -> -1
    else -> throw Exception("Incorrect char")
}