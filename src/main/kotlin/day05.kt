fun main() = aoc(5) { input ->
    val (rules, sequences) = input.split(Regex("(\r?\n){2}"))
    val cmp = { x: Int, y: Int -> if ("$x|$y" in rules) -1 else 0 }

    sequences.lines().fold(0 to 0) { (sum, sum2), seq ->
        val num = seq.split(",").map(String::toInt)
        val sorted = num.sortedWith(Comparator(cmp))
        if (num != sorted) sum to (sum2 + sorted[sorted.size / 2]) else (sum + sorted[sorted.size / 2]) to sum2
    }
}