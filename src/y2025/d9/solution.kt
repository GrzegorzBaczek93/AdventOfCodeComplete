package y2025.d9

import utils.points.Point2D
import utils.readInput
import kotlin.math.max

fun solution() {
    val input = readInput(2025, 9)
    println(parse(input).maxArea())
}

private fun parse(input: List<String>): List<Point2D> = input
    .map { line -> line.split(',').map { it.toInt() } }
    .map { (x, y) -> Point2D(x = x, y = y) }

private fun List<Point2D>.maxArea(): Long {
    var maxArea = 0L

    (0..<lastIndex).forEach { i ->
        (i + 1..lastIndex).forEach { j ->
            val area = Point2D.areaBetween(this[i], this[j])
            maxArea = max(maxArea, area)
        }
    }

    return maxArea
}
