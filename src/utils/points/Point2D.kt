package utils.points

data class Point2D(
    val x: Int,
    val y: Int,
)

fun Point2D.nextTop(): Point2D = Point2D(this.x, this.y - 1)
fun Point2D.nextBottom(): Point2D = Point2D(this.x, this.y + 1)
fun Point2D.nextLeft(): Point2D = Point2D(this.x - 1, this.y)
fun Point2D.nextRight(): Point2D = Point2D(this.x + 1, this.y)
fun Point2D.nextTopLeft(): Point2D = Point2D(this.x - 1, this.y - 1)
fun Point2D.nextTopRight(): Point2D = Point2D(this.x + 1, this.y - 1)
fun Point2D.nextBottomLeft(): Point2D = Point2D(this.x - 1, this.y + 1)
fun Point2D.nextBottomRight(): Point2D = Point2D(this.x + 1, this.y + 1)

fun Point2D.getAllAdjacent(): List<Point2D> = listOf(
    nextTop(),
    nextBottom(),
    nextRight(),
    nextLeft(),
    nextTopLeft(),
    nextTopRight(),
    nextBottomLeft(),
    nextBottomRight(),
)

fun <T> Point2D.nextTopOrNull(map: List<List<T>>): Point2D? =
    Point2D(this.x, this.y - 1).takeIf { it.isInBounds(map) }

fun <T> Point2D.nextBottomOrNull(map: List<List<T>>): Point2D? =
    Point2D(this.x, this.y + 1).takeIf { it.isInBounds(map) }

fun <T> Point2D.nextLeftOrNull(map: List<List<T>>): Point2D? =
    Point2D(this.x - 1, this.y).takeIf { it.isInBounds(map) }

fun <T> Point2D.nextRightOrNull(map: List<List<T>>): Point2D? =
    Point2D(this.x + 1, this.y).takeIf { it.isInBounds(map) }

fun Point2D.nextTopOrNull(rangeX: IntRange, rangeY: IntRange): Point2D? =
    Point2D(this.x, this.y - 1).takeIf { it.isInBounds(rangeX, rangeY) }

fun Point2D.nextBottomOrNull(rangeX: IntRange, rangeY: IntRange): Point2D? =
    Point2D(this.x, this.y + 1).takeIf { it.isInBounds(rangeX, rangeY) }

fun Point2D.nextLeftOrNull(rangeX: IntRange, rangeY: IntRange): Point2D? =
    Point2D(this.x - 1, this.y).takeIf { it.isInBounds(rangeX, rangeY) }

fun Point2D.nextRightOrNull(rangeX: IntRange, rangeY: IntRange): Point2D? =
    Point2D(this.x + 1, this.y).takeIf { it.isInBounds(rangeX, rangeY) }

fun Point2D.getFourSides(rangeX: IntRange, rangeY: IntRange): List<Point2D> =
    listOfNotNull(
        this.nextTopOrNull(rangeX, rangeY),
        this.nextBottomOrNull(rangeX, rangeY),
        this.nextLeftOrNull(rangeX, rangeY),
        this.nextRightOrNull(rangeX, rangeY),
    )


fun <T> Point2D.isInBounds(map: List<List<T>>): Boolean {
    val indicesY = map.first().indices
    val indicesX = map.indices

    return this.isInBounds(indicesX, indicesY)
}

fun Point2D.isInBounds(rangeX: IntRange, rangeY: IntRange): Boolean {
    return this.x in rangeX && this.y in rangeY
}

fun <T> List<List<T>>.get(point2D: Point2D) = this[point2D.y][point2D.x]