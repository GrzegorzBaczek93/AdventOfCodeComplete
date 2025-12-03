package y2025.d2

import utils.readInput

fun solution() {
    val input = readInput(2025, 2)
    println(solve(input.first()))
}

private fun solve(input: String): Long {
    return input.split(',').fold(0L) { acc, range ->
        val (low, high) = range.split('-').map { it.toLong() }
        acc + (low..high).filter { number -> number.toString().isValidV2() }.sum()
    }
}

private fun String.isValid(): Boolean {
    return if (this.length % 2 == 1) {
        false
    } else {
        this.slice(0..<this.length / 2) == this.slice(this.length / 2..<this.length)
    }
}

private fun String.isValidV2(chunkSize: Int = this.length / 2): Boolean {
    if (chunkSize == 0) return false

    val first = this.take(chunkSize)
    val chunks = this.chunked(chunkSize)

    for (chunk in chunks.drop(1)) {
        if (chunk != first) { return this.isValidV2(chunkSize - 1) }
    }

    return true
}
