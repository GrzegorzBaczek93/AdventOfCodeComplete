package y2025.d7

import utils.readInput

fun solution() {
    val input = readInput(2025, 7)

    println(countBeamSplit(input))
    println(countActiveLines(input))
}

private fun countBeamSplit(input: List<String>): Int {
    val startIndex = input.first().indexOfFirst { it == 'S' }
    var beams = setOf(startIndex)
    var splits = 0

    input.drop(1).forEach { line ->
        val newBeams = mutableSetOf<Int>()
        beams.forEach { bIndex ->
            when (line[bIndex]) {
                '.' -> newBeams.add(bIndex)
                '^' -> {
                    splits += 1
                    newBeams.addAll(listOf(bIndex - 1, bIndex + 1))
                }
            }
        }
        beams = newBeams
    }

    return splits
}

private fun countActiveLines(input: List<String>): Long {
    val startIndex = input.first().indexOfFirst { it == 'S' }
    return getLine(startIndex, input, 1)
}

private fun getLine(
    beamIndex: Int,
    input: List<String>,
    depth: Int,
    memo: MutableMap<Pair<Int, Int>, Long> = mutableMapOf(),
): Long {
    val key = beamIndex to depth
    memo[key]?.let { return it }

    val line = input[depth]
    return when {
        depth == input.lastIndex -> 1

        line[beamIndex] == '^' -> {
            val left = getLine(beamIndex - 1, input, depth, memo)
            val right = getLine(beamIndex + 1, input, depth, memo)
            left + right
        }

        else -> {
            getLine(beamIndex, input, depth + 1, memo).also { memo[key] = it }
        }
    }
}
