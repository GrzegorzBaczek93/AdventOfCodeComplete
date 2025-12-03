package y2015.d3

import utils.*

fun solution() {
    val input = readInput(2015, 3).first()
    println("Visited houses count: ${calculateVisitedHouses(input)}")
    println("Visited houses count with robot: ${calculateVisitedHousesWithRobot(input)}")
}

private fun calculateVisitedHouses(input: String): Int {
    val visitedPositions = mutableSetOf<Position>()
    var currentPosition = Position(0, 0)

    visitedPositions.add(currentPosition)
    input.forEach { direction ->
        val nextPosition = currentPosition.toNextPosition(direction)
        visitedPositions.add(nextPosition)
        currentPosition = nextPosition
    }

    return visitedPositions.size
}

private fun calculateVisitedHousesWithRobot(input: String): Int {
    val visitedPositions = mutableSetOf<Position>()
    var currentSantaPosition = Position(0, 0)
    var currentRobotPosition = Position(0, 0)

    visitedPositions.add(currentSantaPosition)
    input.windowed(2, 2) { directions ->
        val nextSantaPosition = currentSantaPosition.toNextPosition(directions.first())
        val nextRobotPosition = currentRobotPosition.toNextPosition(directions.last())
        visitedPositions.addAll(listOf(nextSantaPosition, nextRobotPosition))
        currentSantaPosition = nextSantaPosition
        currentRobotPosition = nextRobotPosition
    }

    return visitedPositions.size
}

private fun Position.toNextPosition(direction: Char) = when (direction) {
    '^' -> this.nextTop()
    '>' -> this.nextRight()
    '<' -> this.nextLeft()
    'v' -> this.nextBottom()
    else -> throw Exception("Unsupported direction")
}