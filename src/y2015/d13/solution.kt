package y2015.d13

import utils.algorithms.Permutations
import utils.isDot
import utils.readInput
import kotlin.math.max

fun solution() {
    val input = readInput(2015, 13)

    println(input.calculateHappiness())
}

private val names = listOf(
    "Alice",
    "Bob",
    "Carol",
    "David",
    "Eric",
    "Frank",
    "George",
    "Mallory",
    "Gregory",
)

private fun List<String>.calculateHappiness(): Int {
    val permutations = Permutations(names)
    var result = 0
    val people = this.parse()

    while (permutations.hasNext()) {
        val permutation = permutations.getNext()
        val temp = calculate(permutation, people)
        result = max(temp, result)
    }

    return result
}

private fun List<String>.parse(): List<Person> {
    return List(size = names.size) { index ->
        val units = this.filter { it.startsWith(names[index]) }.map { row ->
            val split = row.split(' ')
            val sign = if (split[2] == "lose") -1 else 1

            split[10].filterNot { it.isDot() } to (split[3].toInt() * sign)
        }.toMap()
        Person(name = names[index], units = units)
    }
}

private fun calculate(permutation: List<String>, people: List<Person>): Int {
    var result = 0

    permutation.indices.forEach { index ->
        val (n1, n2) = when {
            index == 0 -> {
                permutation[1] to permutation[permutation.lastIndex]
            }

            index == permutation.lastIndex -> {
                permutation[0] to permutation[permutation.lastIndex - 1]
            }

            else -> {
                permutation[index - 1] to permutation[index + 1]
            }
        }
        result += people.first { it.name == permutation[index] }.units.filter { it.key == n1 || it.key == n2 }
            .map { it.value }.sum()
    }

    return result
}

private data class Person(val name: String, val units: Map<String, Int>)
