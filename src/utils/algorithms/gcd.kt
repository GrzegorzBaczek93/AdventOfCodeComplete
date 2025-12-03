package utils.algorithms

/**
 * Greatest common divisor algorithm
 */
fun gcd(num1: Long, num2: Long): Long {
    return if (num2 == 0L) num1 else gcd(num2, num1 % num2)
}
