package y2015.d15

import utils.isDotOrComma
import utils.readInput
import kotlin.math.max

fun solution() {
    val input = readInput(2015, 15)
    println(getBestRecipe(input))
}

private fun getBestRecipe(input: List<String>): Long {
    val ingredients = parse(input)
    var currentBest = ingredients.calculate()

    ingredients.indices.forEach { i ->
        (i + 1..<ingredients.size).forEach { j ->
            while (ingredients.swap(i, j).calculate().also { println("Checking $i, $j => $it and current is $currentBest") } >= currentBest) {
                currentBest = ingredients.calculate()
                println("Swapping $i.${ingredients[i]} with $j.${ingredients[j]}")
            }
            while (ingredients.swap(j, i).calculate().also { println("Checking $j, $i => $it and current is $currentBest") } >= currentBest) {
                currentBest = ingredients.calculate()
                println("Swapping $j.${ingredients[j]} with $i.${ingredients[i]}")
            }
        }
    }

    return currentBest
}

private fun List<Ingredient>.swap(i1: Int, i2: Int): List<Ingredient> {
    if (this[i2].amount == 0) { return this }

    return this.mapIndexed { index: Int, ingredient: Ingredient ->
        if (index == i1) ingredient.amount += 1
        if (index == i2) ingredient.amount -= 1
        ingredient
    }
}

private fun List<Ingredient>.calculate(): Long {
    var capacity = 0L
    var durability = 0L
    var flavor = 0L
    var texture = 0L

    forEach { ingredient ->
        capacity += ingredient.capacity * ingredient.amount
        durability += ingredient.durability * ingredient.amount
        flavor += ingredient.flavor * ingredient.amount
        texture += ingredient.texture * ingredient.amount
    }

    return max(capacity * durability * flavor * texture, 0L)
}

private fun parse(input: List<String>): List<Ingredient> {
    return input.map { line ->
        val split = line.split(' ')
        Ingredient(
            name = split[0].filter { it.isLetter() },
            capacity = split[2].filterNot { it.isDotOrComma() }.toInt(),
            durability = split[4].filterNot { it.isDotOrComma() }.toInt(),
            flavor = split[6].filterNot { it.isDotOrComma() }.toInt(),
            texture = split[8].filterNot { it.isDotOrComma() }.toInt(),
            calories = split[10].filterNot { it.isDotOrComma() }.toInt(),
            amount = 100 / input.size,
        )
    }
}

private data class Ingredient(
    val name: String,
    val capacity: Int,
    val durability: Int,
    val flavor: Int,
    val texture: Int,
    val calories: Int,
    var amount: Int,
)