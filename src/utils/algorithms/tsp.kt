package utils.algorithms

/**
 * Traveling Salesman Problem brute force algorithm
 */
fun tsp(destinations: List<String>, distances: List<Distance>, shortest: Boolean = true): Pair<List<String>, Int> {
    var currentRoute = emptyList<String>()
    var currentLength = 0

    val permutations = Permutations(destinations)
    while (permutations.hasNext()) {
        val route = permutations.getNext()
        val distance = calculateDistance(route, distances)
        if (shortest && (currentLength == 0 || distance < currentLength)) {
            currentRoute = route
            currentLength = distance
        }
        if (!shortest && distance > currentLength) {
            currentRoute = route
            currentLength = distance
        }
    }

    return currentRoute to currentLength
}

private fun calculateDistance(route: List<String>, distances: List<Distance>): Int {
    return route.windowed(2, 1).fold(0) { acc, (from, to) ->
        acc + distances.firstOrNull { it.destinations.containsAll(listOf(from, to)) }!!.distance
    }
}

data class Distance(val destinations: List<String>, val distance: Int)
