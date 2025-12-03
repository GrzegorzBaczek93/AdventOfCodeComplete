package utils

fun String.toRange(): IntRange =
    split("-").let { (first, second) -> Integer.parseInt(first)..Integer.parseInt(second) }

fun String.containsDuplicates(): Boolean = toSet().size != this.length

fun String.swapFirst(oldChar: Char, newChar: Char): String {
    val index = indexOfFirst { it == oldChar }
    if (index == -1) return this

    return swap(index, newChar)
}

fun String.swap(index: Int, newChar: Char): String {
    val list = this.toMutableList()
    list[index] = newChar
    return list.joinToString(separator = "")
}

fun String.indexesOfAll(char: Char): List<Int> {
    val list = mutableListOf<Int>()

    forEachIndexed { index, c ->
        if (char == c) list.add(index)
    }

    return list
}

fun String.containsAny(others: List<String>): Boolean {
    var current = this

    while(current.isNotEmpty()) {
        for (index in others.indices) {
            if (current.startsWith(others[index])) {
                return true
            }
        }
        current = current.drop(1)
    }

    return false
}