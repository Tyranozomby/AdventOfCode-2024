import java.io.File

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

operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = Pair(first + other.first, second + other.second)
operator fun Pair<Int, Int>.minus(other: Pair<Int, Int>) = Pair(first - other.first, second - other.second)
operator fun Pair<Int, Int>.times(other: Int) = Pair(first * other, second * other)
fun Pair<Int, Int>.inv() = Pair(-second, first)