fun main() = aoc(7) { input ->
    val pairs = input.lines().map { it.split(": ") }.map { it[0].toLong() to it[1].split(" ").map(String::toLong) }

    pairs.map { (total, numbers) ->
        numbers.foldIndexed(listOf<Long>() to listOf<Long>()) { i, (last, last2), n ->
            if (i == 0) listOf(n) to listOf(n)
            else (last.map { it + n } + last.map { it * n } to last2.map { it + n } + last2.map { it * n } + last2.map { (it.toString() + n.toString()).toLong() })
        }.let { (totals, totals2) ->
            (if (totals.find { it == total } != null) total else 0L) to (if (totals2.find { it == total } != null) total else 0L)
        }
    }.unzip().let { (part1, part2) -> part1.sum() to part2.sum() }
}