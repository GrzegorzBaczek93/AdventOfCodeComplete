package y2015.d11

import utils.containsAny
import utils.readInput

fun solution() {
    val input = readInput(2015, 11).first()

    println(input.getNext())
}

private fun String.getNext(): String {
    var current = this.increment()

    while (current.isValid().not()) {
        current = current.increment()
    }

    return current
}

private fun String.increment(): String {
    var result = ""
    var reminder = 1

    this.reversed().forEach { char ->
        if (reminder == 1) {
            if (char == 'z') {
                result = "a$result"
            } else {
                result = "${(char.code + 1).toChar()}$result"
                reminder = 0
            }
        } else {
            result = "$char$result"
        }
    }

    if (reminder == 1) result = "a$result"

    return result
}

private fun String.isValid(): Boolean {
    if (this.length < 8) return false

    var threeStraightIncreasing = false
    (0..this.length - 3).forEach { index ->
        if (this[index + 1].code - this[index].code == 1 && this[index + 2].code - this[index].code == 2) {
            threeStraightIncreasing = true
            return@forEach
        }
    }
    if (threeStraightIncreasing.not()) return false

    if (this.containsAny(listOf("o", "i", "l"))) return false

    var overlaps = 0
    var i = 0
    while (i <= this.length - 2) {
        if (this[i].code == this[i + 1].code) {
            overlaps += 1
            i += 2
        } else {
            i += 1
        }
    }

    if (overlaps < 2) return false

    return true
}