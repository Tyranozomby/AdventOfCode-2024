private fun isSafe(numbers: List<Int>): Boolean {
    val inc = numbers.zipWithNext { a, b -> b - a }
    return inc.all { it in 1..3 } || inc.all { it in -3..-1 }
}

fun main() = aoc(2) { input ->
    val numbersList = input.lines().map { line -> line.split(" ").map { it.toInt() } }

    val sum = numbersList.count { isSafe(it) }
    val sum2 = numbersList.count { row -> row.indices.any { isSafe(row.filterIndexed { index, _ -> index != it }) } }

    Pair(sum, sum2)
}