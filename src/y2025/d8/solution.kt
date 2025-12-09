package y2025.d8

import utils.multiply
import utils.readInput
import utils.vectors.Vector3D
import utils.vectors.distanceTo

fun solution() {
    val input = readInput(2025, 8)
    println(solve(input))
}

private fun solve(input: List<String>): Long {
    val parsed = parse(input)
    val sorted = sortConnections(parsed)
    val circuits = mutableListOf<MutableSet<Vector3D<Long>>>()

    sorted.forEach { (first, second) ->
        val foundCircuits = circuits.filter { it.contains(first) || it.contains(second) }
        when (foundCircuits.size) {
            0 -> {
                circuits.add(mutableSetOf(first, second))
            }
            1 -> {
                foundCircuits.first().addAll(listOf(first, second))
            }
            else -> {
                val newCircuit = foundCircuits.flatten().toMutableSet()
                circuits.removeAll(foundCircuits)
                circuits.add(newCircuit)
            }
        }
//        Uncomment for part 2
//        if (circuits.firstOrNull()?.size == parsed.size) {
//            return first.x * second.x
//        }
    }

    return circuits.sortedBy { it.size }.reversed().take(3).map { it.size.toLong() }.multiply()
}

private fun parse(input: List<String>): List<Vector3D<Long>> {
    return input.map { line ->
        val (x, y, z) = line.split(',')
        Vector3D(x = x.toLong(), y = y.toLong(), z = z.toLong())
    }
}

private fun sortConnections(input: List<Vector3D<Long>>): List<Pair<Vector3D<Long>, Vector3D<Long>>> {
    val unchecked = input.toMutableList()
    val result = mutableListOf<Pair<Pair<Vector3D<Long>, Vector3D<Long>>, Double>>()

    while (unchecked.size > 1) {
        val first = unchecked.removeFirst()
        unchecked.forEach { other -> result += (first to other) to first.distanceTo(other) }
    }

    return result.sortedBy { it.second }.map { it.first }
}