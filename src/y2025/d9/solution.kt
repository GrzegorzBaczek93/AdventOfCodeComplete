package y2025.d9

import utils.points.Point2D
import utils.readInput
import utils.shape.Polygon2D
import utils.shape.Rectangle2D
import kotlin.math.max

fun solution() {
    val input = readInput(2025, 9)
    println(parse(input).maxArea())
    println(parse(input).maxAreaInside())
}

private fun parse(input: List<String>): List<Point2D> = input
    .map { line -> line.split(',').map { it.toInt() } }
    .map { (x, y) -> Point2D(x = x, y = y) }

private fun List<Point2D>.maxArea(): Long {
    var maxArea = 0L

    (0..<lastIndex).forEach { i ->
        (i + 1..lastIndex).forEach { j ->
            val rect = Rectangle2D.fromOppositeVertices(this[i], this[j])
            maxArea = max(maxArea, rect.getArea())
        }
    }

    return maxArea
}

private fun List<Point2D>.maxAreaInside(): Long {
    var maxArea = 0L
    val polygon = Polygon2D(this)

    (0..<lastIndex).forEach { i ->
        (i + 1..lastIndex).forEach { j ->
            val rect = Rectangle2D.fromOppositeVertices(this[i], this[j])
            if (polygon.contains(rect)) {
                maxArea = max(maxArea, rect.getArea())
                println(rect)
            }
        }
    }

    return maxArea
}
