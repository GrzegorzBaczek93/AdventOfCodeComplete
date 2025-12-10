package y2015.d6

import utils.points.Point2D
import utils.readInput
import utils.sum
import kotlin.math.max

fun solution() {
    val input = readInput(2015, 6)
    println("Enabled lights ${configureLights(input)}")
}

private fun configureLights(input: List<String>): Int {
    val grid = MutableList(1000) { MutableList(1000) { 0 } }

    input.forEach { rawInstruction ->
        val instruction = Instruction(rawInstruction)
        instruction.apply(grid)
    }

    return grid.sum()
}

private class Instruction private constructor() {

    private lateinit var modifier: Modifier
    private lateinit var from: Point2D
    private lateinit var to: Point2D

    constructor(raw: String) : this() {
        parse(raw)
    }

    private fun parse(raw: String) {
        modifier = Modifier.fromString(raw)
        val (r1, r2) = raw.split("through")
        from = r1.toPosition()
        to = r2.toPosition()
    }

    private fun String.toPosition(): Point2D {
        val (x, y) = filterNot { it.isWhitespace() || it.isLetter() }.split(',').map { it.toInt() }
        return Point2D(x, y)
    }

    fun apply(grid: MutableList<MutableList<Int>>) {
        for (x in from.x..to.x) {
            for (y in from.y..to.y) {
                grid[x][y] = modifier.apply(grid[x][y])
            }
        }
    }

    private enum class Modifier(val value: String) {
        TURN_ON("turn on") {
            override fun apply(prev: Int): Int = prev + 1
        },
        TURN_OFF("turn off") {
            override fun apply(prev: Int): Int = max(prev - 1, 0)
        },
        TOGGLE("toggle") {
            override fun apply(prev: Int): Int = prev + 2
        };

        abstract fun apply(prev: Int): Int

        companion object {
            fun fromString(raw: String) =
                entries.firstOrNull { raw.startsWith(it.value) } ?: throw Exception("Unsupported instruction")
        }
    }
}
