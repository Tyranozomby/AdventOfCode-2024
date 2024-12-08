import java.io.File
import kotlin.math.abs

fun getText(fileName: String): String = File("src/main/resources/$fileName").readText()

fun aoc(day: Int, block: (String) -> Any?) {
    println("Day $day of Advent of Code!\n")

    val padDay = day.toString().padStart(2, '0')

    val input = getText("day$padDay.txt")
    val result = block(input)

    if (result is Pair<*, *>) {
        val (sum, sum2) = result
        println("Part 1: $sum\nPart 2: $sum2")
    } else if (result != null) {
        println("Result: $result")
    }
}

@Suppress("MemberVisibilityCanBePrivate")
data class Coord(val x: Int, val y: Int) {
    constructor(pair: Pair<Int, Int>) : this(pair.first, pair.second)

    operator fun plus(other: Pair<Int, Int>) = Coord(x + other.first, y + other.second)
    operator fun plus(other: Coord) = Coord(x + other.x, y + other.y)

    operator fun minus(other: Coord) = Coord(x - other.x, y - other.y)
    operator fun minus(other: Pair<Int, Int>) = Coord(x - other.first, y - other.second)

    operator fun times(other: Int) = Coord(x * other, y * other)

    fun rot() = Coord(-y, x)
    fun distanceTo(other: Coord) = abs(x - other.x) + abs(y - other.y)

    override fun toString() = "($x, $y)"
}