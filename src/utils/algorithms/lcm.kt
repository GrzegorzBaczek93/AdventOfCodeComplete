package utils.algorithms

/**
 * Least common multiplier algorithm
 */
fun lcm(numbers: List<Long>): Long {
    return numbers.fold(1) { acc, number -> (acc * number) / gcd(acc, number) }
}
