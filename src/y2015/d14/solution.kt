package y2015.d14

import utils.readInput

fun solution() {
    val input = readInput(2015, 14)
    println(getFastest(input, 2503))
}

private fun getFastest(input: List<String>, time: Int): Int {
    val reindeers = input.parse()
    repeat(time) {
        reindeers.forEach { it.move() }
        val bestDistance = reindeers.maxOf { it.distance }
        reindeers.forEach { reindeer ->
            if (reindeer.distance == bestDistance) {
                reindeer.points += 1
            }
        }
    }

    return reindeers.maxOf { it.points }
}

private fun List<String>.parse(): List<Reindeer> {
    return map { row ->
        val split = row.split(' ')
        Reindeer(
            name = split[0],
            speed = split[3].toInt(),
            runTime = split[6].toInt(),
            restTime = split[13].toInt(),
        )
    }
}

private data class Reindeer(
    val name: String,
    val speed: Int,
    val runTime: Int,
    val restTime: Int,
    var distance: Int = 0,
    var isRunning: Boolean = true,
    var timer: Int = 0,
    var points: Int = 0,
) {
    fun move() {
        if (isRunning) {
            timer += 1
            distance += speed
            if (timer == runTime) {
                isRunning = false
                timer = 0
            }
        } else {
            timer += 1
            if (timer == restTime) {
                isRunning = true
                timer = 0
            }
        }
    }
}