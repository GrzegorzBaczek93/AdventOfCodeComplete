package utils

fun <T : Comparable<T>> ClosedRange<T>.isSubRange(other: ClosedRange<T>): Boolean =
    start in other && endInclusive in other

fun <T : Comparable<T>> OpenEndRange<T>.isSubRange(other: OpenEndRange<T>): Boolean =
    start in other && endExclusive in other

fun <T : Comparable<T>> ClosedRange<T>.isOverlapping(other: ClosedRange<T>): Boolean =
    start in other || endInclusive in other || other.start in this || other.endInclusive in this

fun <T : Comparable<T>> OpenEndRange<T>.isOverlapping(other: OpenEndRange<T>): Boolean =
    start in other || endExclusive in other || other.start in this || other.endExclusive in this

infix fun <T : Comparable<T>> ClosedRange<T>.join(other: ClosedRange<T>): ClosedRange<T> {
    return min(this.start, other.start)..max(this.endInclusive, other.endInclusive)
}

infix fun <T : Comparable<T>> OpenEndRange<T>.join(other: OpenEndRange<T>): OpenEndRange<T> {
    return min(this.start, other.start)..<max(this.endExclusive, other.endExclusive)
}

