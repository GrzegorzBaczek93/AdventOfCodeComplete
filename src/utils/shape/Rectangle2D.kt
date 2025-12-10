package utils.shape

import utils.line.Line2D
import utils.points.Point2D
import kotlin.math.abs

class Rectangle2D private constructor(
    private val p1: Point2D,
    private val p2: Point2D,
    private val p3: Point2D,
    private val p4: Point2D,
) {
    fun getArea(): Long {
        return (abs(p1.x - p2.x.toLong()) + 1) * (abs(p1.y - p3.y.toLong()) + 1)
    }

    fun getEdges(): List<Line2D> {
        return listOf(
            Line2D(p1, p2),
            Line2D(p2, p3),
            Line2D(p3, p4),
            Line2D(p4, p1),
        )
    }

    fun getVertexes(): List<Point2D> {
        return listOf(p1, p2, p3, p4)
    }

    override fun toString(): String {
        return "Rectangle($p1, $p2, $p3, $p4)"
    }

    companion object {
        fun fromOppositeVertices(v1: Point2D, v2: Point2D): Rectangle2D {
            return Rectangle2D(
                p1 = v1,
                p2 = Point2D(x = v2.x, y = v1.y),
                p3 = v2,
                p4 = Point2D(x = v1.x, y = v2.y),
            )
        }
    }
}
