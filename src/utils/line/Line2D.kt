package utils.line

import utils.points.Point2D

data class Line2D(val start: Point2D, val end: Point2D) {
    fun intersects(other: Line2D): Boolean {
        fun ccw(a: Point2D, b: Point2D, c: Point2D): Boolean {
            return (c.y - a.y) * (b.x - a.x) > (b.y - a.y) * (c.x - a.x)
        }

        return ccw(this.start, other.start, other.end) != ccw(this.end, other.start, other.end) &&
                ccw(this.start, this.end, other.start) != ccw(this.start, this.end, other.end)
    }
}

fun Point2D.isOnLine(line: Line2D): Boolean {
    val rangeX = if (line.start.x > line.end.x) {
        line.end.x..line.start.x
    } else {
        line.start.x..line.end.x
    }
    val rangeY = if (line.start.y > line.end.y) {
        line.end.y..line.start.y
    } else {
        line.start.y..line.end.y
    }
    return this.x in rangeX || this.y in rangeY
}
