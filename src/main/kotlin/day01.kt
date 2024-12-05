import kotlin.math.absoluteValue

fun main() = aoc(1) { input ->
    var (list1, list2) = input.lines().map { it.split("   ") }.map { Pair(it[0].toInt(), it[1].toInt()) }.unzip()

    list1 = list1.sorted()
    list2 = list2.sorted()

    val sum = list1.mapIndexed { index, i -> (list2[index] - i).absoluteValue }.sum()
    val sum2 = list1.toSet().map { it to list2.count { v -> v == it } }.sumOf { it.first * it.second }

    Pair(sum, sum2)
}