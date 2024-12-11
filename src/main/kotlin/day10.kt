fun main() = aoc(10) { input ->
    val coords = input.lines().mapIndexed { y, line -> line.mapIndexed { x, c -> Coord(x, y) to c.toString().toInt() } }.flatten().toMap()
    println(coords)

    val starts = coords.filterValues { it == 0 }.map { it.key to 0 }.toList()
    println(starts)

    val directions = listOf(
        Coord(1, 0),
        Coord(0, 1),
        Coord(-1, 0),
        Coord(0, -1)
    )

    val paths = starts.map { start ->
        val paths = mutableListOf(mutableListOf(start))

        while (true) {
            val updatedPath = mutableListOf<MutableList<Pair<Coord, Int>>>()
            println(paths)
            paths.forEach {
                val last = it.last()

                directions.forEach { direction ->
                    val next = last.first + direction
                    if (coords.containsKey(next) && coords[next] == last.second + 1) {
                        updatedPath.add((it + (next to (last.second + 1))).toMutableList())
                    }
                }
            }

            if (updatedPath.isEmpty()) {
                return@map paths.map { it.first() to it.last() }.toSet().size to paths.size
            }

            paths.clear()
            paths.addAll(updatedPath)
        }

        // Without this, the compiler complains that the function does not return a value
        @Suppress("UNREACHABLE_CODE")
        return@map 0 to 0
    }

    println(paths)

    paths.unzip().let {
        it.first.sum() to it.second.sum()
    }
}