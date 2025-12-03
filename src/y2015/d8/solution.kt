package y2015.d8

import utils.readInput
//paulina 190

fun solution() {
    val input = readInput(2015, 8)

    println("Calculated number of chars: ${calculateNumberOfChars(input)}")
}

private fun calculateNumberOfChars(input: List<String>): Int {
    var totalChars = 0
    var charsInMemory = 0
    var encodingChars = 0

    input.forEach { line ->
        var currentCharsInMemory = 0
        var index = 0
        while (index < line.length) {
            when (line[index]) {
                '"' -> {
                    index += 1
                    encodingChars += 2
                }
                '\\' -> {
                    when (line[index + 1]) {
                        '\\', '"' -> {
                            index += 2
                            currentCharsInMemory += 1
                            encodingChars += 2
                        }
                        'x' -> {
                            index += 4
                            currentCharsInMemory += 1
                            encodingChars += 1
                        }
                    }
                }
                else -> {
                    currentCharsInMemory += 1
                    index += 1
                }
            }
        }
        totalChars += line.length
        charsInMemory += currentCharsInMemory
    }

    println("Extra encoding: $encodingChars")
    return totalChars - charsInMemory
}
