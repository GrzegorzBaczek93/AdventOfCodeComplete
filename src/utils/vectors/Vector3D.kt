package utils.vectors

import kotlin.math.pow
import kotlin.math.sqrt

data class Vector3D<T : Number>(val x: T, val y: T, val z: T)

fun <T : Number> Vector3D<T>.distanceTo(other: Vector3D<T>): Double {
    return sqrt(
        (this.x.toDouble() - other.x.toDouble()).pow(2)
                + (this.y.toDouble() - other.y.toDouble()).pow(2)
                + (this.z.toDouble() - other.z.toDouble()).pow(2)
    )
}
