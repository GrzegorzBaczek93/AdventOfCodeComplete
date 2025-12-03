package y2015.d5

import utils.readInput

private val englishVowels = listOf('a', 'e', 'i', 'o', 'u')
private val notNiceStrings = listOf("ab", "cd", "pq", "xy")

fun solution() {
    val input = readInput(2015, 5)
    println("Nice string count: ${countNiceStrings(input)}")
    println("Nice string count: ${countNiceStringsUpgraded(input)}")
}

private fun countNiceStrings(input: List<String>): Int {
    return input.fold(0) { acc, s -> if (s.isNice()) acc + 1 else acc }
}

private fun countNiceStringsUpgraded(input: List<String>): Int {
    return input.fold(0) { acc, s -> if (s.isNiceUpgraded()) acc + 1 else acc }
}

private fun String.isNice(): Boolean {
    var current = this
    var vowelsCount = 0
    var prevChar: Char? = null
    var containsDouble = false

    while (current.isNotEmpty()) {
        for (index in notNiceStrings.indices) {
            if (current.startsWith(notNiceStrings[index])) return false
        }

        if (current.first() == prevChar) containsDouble = true
        prevChar = current.first()

        if (englishVowels.contains(current.first())) vowelsCount++

        current = current.drop(1)
    }

    return containsDouble && vowelsCount >= 3
}

private fun String.isNiceUpgraded(): Boolean {
    if (this.containsSeparatedDuplicate().not()) return false
    if (this.containsDuplicate(2)) return true
    return false
}

private fun String.containsSeparatedDuplicate(): Boolean {
    var current = this

    while (current.isNotEmpty()) {
        val temp = current.take(3)
        if (temp.length != 3) return false
        if (temp[0] == temp[2]) return true

        current = current.drop(1)
    }

    return false
}

private fun String.containsDuplicate(size: Int): Boolean {
    val pieces = mutableListOf<String>()

    for (i in 0..<length - 1) {
        val temp = this.substring(i, i + size)
        val foundIndex = pieces.indexOfFirst { it == temp }
        if (foundIndex != -1 && foundIndex != i - 1) return true
        pieces.addLast(temp)
    }
    return false
}
