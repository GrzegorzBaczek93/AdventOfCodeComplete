package utils.ranges

import utils.isOverlapping
import utils.join
import kotlin.math.max

class ClosedRangeSet<T : Comparable<T>> : RangeSet<T, ClosedRange<T>>(ClosedRangeOperation())

class OpenEndRangeSet<T : Comparable<T>> : RangeSet<T, OpenEndRange<T>>(OpenEndRangeOperation())

/**
 * Implementation here is not finished at all, should be incrementally extended if needed
 */
abstract class RangeSet<T : Comparable<T>, R>(private val operations: RangeOperation<T, R>) {
    private val ranges: MutableList<R> = mutableListOf()

    fun getRanges() = ranges.toList()

    fun insert(range: R) {
        val insertedIndex = add(range)
        merge(insertedIndex)
    }

    private fun add(range: R): Int {
        ranges.indices.forEach { index ->
            if (operations.getStart(range) <= operations.getStart(ranges[index])) {
                ranges.add(index, range)
                return index
            }
        }
        ranges.addLast(range)
        return ranges.lastIndex
    }

    private fun merge(startIndex: Int) {
        var j = max(startIndex - 1, 0)
        while (j < ranges.lastIndex) {
            if (operations.areOverlapping(ranges[j], ranges[j + 1])) {
                ranges[j] = operations.join(ranges[j], ranges[j + 1])
                ranges.removeAt(j + 1)
            } else {
                j += 1
            }
        }
    }
}

interface RangeOperation<T : Comparable<T>, R> {
    fun areOverlapping(a: R, b: R): Boolean
    fun join(a: R, b: R): R
    fun contains(range: R, value: T): Boolean
    fun createRange(start: T, end: T): R
    fun getStart(range: R): T
    fun getEnd(range: R): T
}

class ClosedRangeOperation<T : Comparable<T>> : RangeOperation<T, ClosedRange<T>> {
    override fun areOverlapping(a: ClosedRange<T>, b: ClosedRange<T>): Boolean = a.isOverlapping(b)

    override fun join(a: ClosedRange<T>, b: ClosedRange<T>): ClosedRange<T> = a.join(b)

    override fun contains(range: ClosedRange<T>, value: T): Boolean = value in range

    override fun createRange(start: T, end: T): ClosedRange<T> = start..end

    override fun getStart(range: ClosedRange<T>): T = range.start

    override fun getEnd(range: ClosedRange<T>): T = range.endInclusive
}

class OpenEndRangeOperation<T : Comparable<T>> : RangeOperation<T, OpenEndRange<T>> {
    override fun areOverlapping(a: OpenEndRange<T>, b: OpenEndRange<T>): Boolean = a.isOverlapping(b)

    override fun join(a: OpenEndRange<T>, b: OpenEndRange<T>): OpenEndRange<T> = a.join(b)

    override fun contains(range: OpenEndRange<T>, value: T): Boolean = value in range

    override fun createRange(start: T, end: T): OpenEndRange<T> = start..<end

    override fun getStart(range: OpenEndRange<T>): T = range.start

    override fun getEnd(range: OpenEndRange<T>): T = range.endExclusive
}
