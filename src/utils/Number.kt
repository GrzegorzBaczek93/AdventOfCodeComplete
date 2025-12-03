package utils

import kotlin.math.max
import kotlin.math.min

fun Int.parity(onEven: () -> Unit = {}, onOdd: () -> Unit = {}) {
    if (this % 2 == 0) {
        onEven()
    } else {
        onOdd()
    }
}

fun min(p1: Int, p2: Int, p3: Int): Int {
    return min(min(p1, p2), p3)
}

fun max(p1: Int, p2: Int, p3: Int): Int {
    return max(max(p1, p2), p3)
}

fun Int.factorial(): Int {
    return if (this > 1) {
        this * (this - 1).factorial()
    } else {
        1
    }
}
