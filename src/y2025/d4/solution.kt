package y2025.d4

import utils.*
import utils.points.Point2D
import utils.points.getAllAdjacent

fun solution() {
    val input = readInput(2025, 4)
    println(countRollsWithRemoval(input))
}

private fun countRolls(input: List<String>): Int {
    val rolls = parse(input = input)
    return rolls.fold(0) { acc, position ->
        if (rolls.isValid(position)) acc + 1 else acc
    }
}

private fun countRollsWithRemoval(input: List<String>): Int {
    var shouldSearch = true
    val rolls = parse(input = input).toMutableSet()
    val rollsToRemove = mutableSetOf<Point2D>()
    val initialSize = rolls.size

    while (shouldSearch) {
        shouldSearch = false
        rolls.forEach { position ->
            if (rolls.isValid(position)) {
                rollsToRemove.add(position)
                shouldSearch = true
            }
        }
        rolls.removeAll(rollsToRemove)
        rollsToRemove.clear()
    }

    return initialSize - rolls.count()
}

private fun parse(input: List<String>): Set<Point2D> {
    val result = mutableSetOf<Point2D>()
    input.walkIndexed { x, y, c -> if (c == '@') result.add(Point2D(x = x, y = y)) }
    return result
}

private fun Set<Point2D>.isValid(point2D: Point2D): Boolean {
    val emptyPositions = point2D
        .getAllAdjacent()
        .map { other -> this.contains(other) }
        .count { it.not() }

    return emptyPositions > 4
}
