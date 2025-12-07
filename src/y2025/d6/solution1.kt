package y2025.d6

import utils.multiply
import utils.readInput

internal fun solution1() {
    val input = readInput(2025, 6)

    println("Part 1: ${doMath(input)}")
}

private fun doMath(input: List<String>): ULong {
    var result: ULong = 0u
    var signIndex = 0
    val indexes = mutableMapOf<Int, Int>()
    (0..<input.lastIndex).map { it to 0 }.let { indexes.putAll(it) }

    while (true) {
        val (nextSignIndex, sign) = readNextSign(signIndex, input.last())
        if (sign.isWhitespace()) break
        signIndex = nextSignIndex

        val numbers = mutableListOf<Long>()
        (0..<input.lastIndex).forEach { index ->
            val (nextIndex, number) = readNextInt(indexes[index]!!, input[index])
            numbers.addLast(number.toLong())
            indexes[index] = nextIndex
        }

        when (sign) {
            '+' -> result += numbers.sum().toULong()
            '*' -> result += numbers.multiply().toULong()
        }
    }

    return result
}

private fun readNextSign(startIndex: Int, data: String): Pair<Int, Char> {
    var index = startIndex
    var result = ' '

    while (true) {
        when {
            index > data.lastIndex -> break
            data[index].isWhitespace() -> index += 1
            else -> {
                result = data[index]
                break
            }
        }
    }

    return index + 1 to result
}

private fun readNextInt(startIndex: Int, data: String): Pair<Int, Int> {
    var index = startIndex
    var result = ""

    while (true) {
        when {
            index > data.lastIndex -> break
            data[index].isDigit() -> {
                result += data[index]
                index += 1
            }

            data[index].isWhitespace() && result.isNotEmpty() -> break
            else -> index += 1
        }
    }

    return index to result.toInt()
}