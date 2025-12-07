package y2025.d6

import utils.multiply
import utils.readInput

internal fun solution2() {
    val input = readInput(2025, 6)

    println("Part 2: ${doMath(input)}")
}

private fun doMath(input: List<String>): ULong {
    var result: ULong = 0u
    var signIndex = 0

    while (true) {
        val sign = readNextSign(signIndex, input.last())
        if (sign.isWhitespace()) break

        val (index, numbers) = parseNextNumbers(signIndex, input)
        val calculated = when (sign) {
            '+' -> numbers.sum().toULong()
            '*' -> numbers.multiply().toULong()
            else -> throw Exception()
        }
        result += calculated
        signIndex = index
    }
    return result
}

private fun readNextSign(startIndex: Int, data: String): Char {
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

    return result
}


private fun parseNextNumbers(startIndex: Int, data: List<String>): Pair<Int, List<Long>> {
    var index = startIndex
    val numbers = mutableListOf<Long>()

    while (true) {
        when {
            index > data.maxOf { it.length } -> break
            data.all { it.getOrElse(index, {' '}).isWhitespace() } -> break
            else -> {
                var stringNumber = ""
                data.dropLast(1).forEach { line ->
                    stringNumber += line.getOrElse(index, {' '})
                }
                numbers.add(stringNumber.filter { it.isDigit() }.toLong())
                index += 1
            }
        }
    }

    return index + 1 to numbers
}