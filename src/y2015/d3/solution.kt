package y2015.d3

import utils.*
import utils.points.*

fun solution() {
    val input = readInput(2015, 3).first()
    println("Visited houses count: ${calculateVisitedHouses(input)}")
    println("Visited houses count with robot: ${calculateVisitedHousesWithRobot(input)}")
}

private fun calculateVisitedHouses(input: String): Int {
    val visitedPoint2DS = mutableSetOf<Point2D>()
    var currentPoint2D = Point2D(0, 0)

    visitedPoint2DS.add(currentPoint2D)
    input.forEach { direction ->
        val nextPosition = currentPoint2D.toNextPosition(direction)
        visitedPoint2DS.add(nextPosition)
        currentPoint2D = nextPosition
    }

    return visitedPoint2DS.size
}

private fun calculateVisitedHousesWithRobot(input: String): Int {
    val visitedPoint2DS = mutableSetOf<Point2D>()
    var currentSantaPoint2D = Point2D(0, 0)
    var currentRobotPoint2D = Point2D(0, 0)

    visitedPoint2DS.add(currentSantaPoint2D)
    input.windowed(2, 2) { directions ->
        val nextSantaPosition = currentSantaPoint2D.toNextPosition(directions.first())
        val nextRobotPosition = currentRobotPoint2D.toNextPosition(directions.last())
        visitedPoint2DS.addAll(listOf(nextSantaPosition, nextRobotPosition))
        currentSantaPoint2D = nextSantaPosition
        currentRobotPoint2D = nextRobotPosition
    }

    return visitedPoint2DS.size
}

private fun Point2D.toNextPosition(direction: Char) = when (direction) {
    '^' -> this.nextTop()
    '>' -> this.nextRight()
    '<' -> this.nextLeft()
    'v' -> this.nextBottom()
    else -> throw Exception("Unsupported direction")
}