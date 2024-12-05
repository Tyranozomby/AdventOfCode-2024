import java.io.File

fun getText(fileName: String): String = File("src/main/resources/$fileName").readText()

fun aoc(day: Int, block: (String) -> Any) {
    println("Day $day of Advent of Code!\n")

    val input = getText("day0$day.txt")
    val result = block(input)

    if (result is Pair<*, *>) {
        val (sum, sum2) = result
        println("Part 1: $sum\nPart 2: $sum2")
    } else {
        println("Result: $result")
    }
}