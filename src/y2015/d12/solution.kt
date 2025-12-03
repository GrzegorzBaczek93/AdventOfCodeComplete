package y2015.d12

import kotlinx.serialization.json.*
import utils.readInput

fun solution() {
    val input = readInput(2015, 12).first()

    println(input.calcAllNumbers())
    println(input.calcNumbers())
}

private fun String.calcAllNumbers(): Int {
    var result = 0
    var i = 0
    var stringNumber = ""

    while (i < length) {
        when {
            this[i] == '-' -> stringNumber += '-'
            this[i].isDigit() -> stringNumber += this[i]
            else -> {
                if (stringNumber.isNotEmpty()) {
                    result += stringNumber.toInt()
                    stringNumber = ""
                }
            }
        }
        i += 1
    }
    return result
}

private fun String.calcNumbers(): Int {
    return Json.parseToJsonElement(this).calculate()
}

private fun JsonElement.calculate(): Int {
    return when (this) {
        is JsonPrimitive -> {
            this.intOrNull ?: 0
        }

        is JsonArray -> {
            this.fold(initial = 0) { acc, jsonElement -> acc + jsonElement.calculate() }
        }

        is JsonObject -> {
            if (this.values.any { it == JsonPrimitive("red") }) {
                0
            } else {
                this.values.fold(initial = 0) { acc, jsonElement -> acc + jsonElement.calculate() }
            }
        }
    }
}
