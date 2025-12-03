package utils.algorithms

import utils.swap
import kotlin.math.floor

/**
 * Class helper to get all List<T> permutations iterative
 */
class Permutations<T>(private val input: List<T>) {
    private val indexes: MutableList<Int> = input.indices.toMutableList()
    private var increase: Int = -1

    fun hasNext(): Boolean = this.increase != indexes.size - 1

    fun getNext(): List<T> {
        return if (increase == -1) {
            getFirst()
        } else {
            getNextInternal()
        }
    }

    private fun getFirst(): List<T> {
        this.increase = 0
        return input
    }

    private fun getNextInternal(): List<T> {
        if (increase == 0) {
            indexes.swap(increase, increase + 1)
            increase += 1
            while (increase < indexes.size - 1 && indexes[increase] > indexes[increase + 1]) {
                increase += 1
            }
        } else {
            if (indexes[increase + 1] > indexes[0]) {
                indexes.swap(increase + 1, 0)
            } else {
                var start = 0
                var end = increase
                var mid = floor(end / 2.toDouble()).toInt()
                val tVal = indexes[increase + 1]
                while ((indexes[mid] < tVal && indexes[mid - 1] > tVal).not()) {
                    if (indexes[mid] < tVal) {
                        end = mid - 1
                    } else {
                        start = mid + 1
                    }
                    mid = floor((start + end) / 2.toDouble()).toInt()
                }
                indexes.swap(increase + 1, mid)
            }
            (0..increase / 2).forEach { i ->
                indexes.swap(i, increase - i)
            }
            increase = 0
        }

        return List(input.size) { input[indexes[it]] }
    }
}