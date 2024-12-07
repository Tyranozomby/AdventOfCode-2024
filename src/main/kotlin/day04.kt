fun main() = aoc(4) { input ->
    val coords = input.lines().mapIndexed { y, line -> line.mapIndexed { x, c -> (x to y) to c.toString() } }.flatten().toMap()

    val get = { coord: Pair<Int, Int> -> coords.getOrDefault(coord, "") }

    return@aoc coords.keys.fold(0 to 0) { (sum, sum2), c ->
        return@fold sum + listOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1).count { d ->
            (0..3).joinToString("") { get(c + d * it) } == "XMAS"
        } to sum2 + listOf(-1 to -1, -1 to 1, 1 to -1, 1 to 1).count { d ->
            (-1..1).joinToString("") { get(c + d * it) } == "MAS" && get(c + d.rot()) + get(c - d.rot()) == "MS"
        }
    }
}