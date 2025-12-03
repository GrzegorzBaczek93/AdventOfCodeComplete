package y2015.d9

import utils.algorithms.Distance
import utils.algorithms.tsp
import utils.readInput

fun solution() {
    val input = readInput(2015, 9)
    println("Route length: ${calculateShortestRoute(input)}")
}

private fun calculateShortestRoute(input: List<String>): Pair<List<String>, Int> {
    val destinations: List<String> = input.toDestinations()
    val distances: List<Distance> = input.toDistances()
    return tsp(destinations, distances, shortest = false)
}

private fun List<String>.toDistances(): List<Distance> {
    return map { line ->
        val split = line.split(' ')
        Distance(destinations = listOf(split[0], split[2]), distance = split[4].toInt())
    }
}

private fun List<String>.toDestinations(): List<String> {
    return map { line ->
        val split = line.split(' ')
        listOf(split[0], split[2])
    }.flatten().toSet().toList()
}
