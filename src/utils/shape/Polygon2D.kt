package utils.shape

import utils.line.Line2D
import utils.line.isOnLine
import utils.points.Point2D

class Polygon2D(points: List<Point2D>) {
    private val edges: List<Line2D>

    init {
        val temp = mutableListOf<Line2D>()
        points.windowed(2).forEach { (first, second) ->
            temp.addLast(Line2D(first, second))
        }
        temp.addLast(Line2D(points.last(), points.first()))
        edges = temp.toList()
    }

    fun contains(rect: Rectangle2D): Boolean {
        val rectVertexes = rect.getVertexes()
        for (vertex in rectVertexes) {
            if (!vertex.isOnEdge() || !vertex.isInside()) {
                return false
            }
        }

        val rectEdges = rect.getEdges()
        for (edge in edges) {
            if (rectEdges.any { it.intersects(edge) }) {
                return false
            }
        }

        return true
    }

    private fun Point2D.isOnEdge(): Boolean {
        for (edge in edges) {
            if (this.isOnLine(edge)) {
                return true
            }
        }
        return false
    }

    private fun Point2D.isInside(): Boolean {
        val rays = listOf(
            Line2D(this, Point2D(Int.MAX_VALUE, this.y)), // right
            Line2D(this, Point2D(0, this.y)), // left
            Line2D(this, Point2D(this.x, 0)), // top
            Line2D(this, Point2D(this.x, Int.MAX_VALUE)), // bottom
        )

        var zeroCount = 0
        for (ray in rays) {
            val intersections = edges.map { ray.intersects(it) }.count { it }
            when {
                intersections == 0 -> zeroCount += 1
                intersections % 2 == 0 -> {
                    return false
                }

                intersections % 2 == 1 -> {
                    return true
                }
            }
        }
        if (zeroCount == 4) {
            return false
        }

        return false
    }
}