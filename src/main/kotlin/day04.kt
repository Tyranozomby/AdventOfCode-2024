fun main() = aoc(4) { input ->
    val coords = input.lines().mapIndexed { y, r -> r.mapIndexed { x, c -> x + 1 to y to c.toString() } }.flatten().toMap()

    val get = { k: Pair<Int, Int> -> coords.getOrDefault(k, "") }

    return@aoc coords.keys.fold(0 to 0) { (sum, sum2), c ->
        return@fold sum + listOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1).count { d ->
            (0..3).joinToString("") { get(c + d * it) } == "XMAS"
        } to sum2 + listOf(-1 to -1, -1 to 1, 1 to -1, 1 to 1).count { d ->
            (-1..1).joinToString("") { get(c + d * it) } == "MAS" && get(c + d.inv()) + get(c - d.inv()) == "MS"
        }
    }
}