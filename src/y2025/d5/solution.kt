package y2025.d5

import utils.ranges.ClosedRangeSet
import utils.readInput

fun solution() {
    val input = readInput(2025, 5)
    val (ranges, ids) = parse(input)
    println(countFresh(ranges, ids))
    println(countFresh(ranges))
}

private fun countFresh(ranges: List<LongRange>, ids: List<Long>): Long {
    return ids.fold(0L) { acc, id ->
        if (ranges.any { id in it }) {
            acc + 1
        } else {
            acc
        }
    }
}

private fun countFresh(ranges: List<LongRange>): Long {
    val reducedRanges = ClosedRangeSet<Long>()
    ranges.forEach { range -> reducedRanges.insert(range) }
    return reducedRanges.getRanges().sumOf { it.endInclusive - it.start + 1 }
}

private fun parse(input: List<String>): Pair<List<LongRange>, List<Long>> {
    val ranges = mutableListOf<LongRange>()
    val ids = mutableListOf<Long>()
    var readingRanges = true

    input.forEach { line ->
        when {
            line.isEmpty() -> {
                readingRanges = false
            }

            readingRanges -> {
                val (low, high) = line.split('-').map { it.toLong() }
                ranges.addLast(LongRange(low, high))
            }

            else -> {
                ids.addLast(line.toLong())
            }
        }
    }

    return ranges to ids
}
