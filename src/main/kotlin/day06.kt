fun main() = aoc(6) { input ->
    val coords = input.lines().mapIndexed { y, line -> line.mapIndexed { x, c -> (x to y) to c } }.flatten().toMap()
    val start = coords.filterValues { it == '^' }.keys.first()

    loop(coords, start).let { steps ->
        steps.first.size to steps.first.fold(0) { sum2, obs ->
            val newCoords = coords.toMutableMap()
            newCoords[obs] = '#'
            return@fold sum2 + if (loop(newCoords, start).second) 1 else 0
        }
    }
}

fun loop(coords: Map<Pair<Int, Int>, Char>, start: Pair<Int, Int>): Pair<Set<Pair<Int, Int>>, Boolean> {
    var position = start
    var direction = Pair(0, -1)
    val steps: MutableSet<Pair<Pair<Int, Int>, Pair<Int, Int>>> = mutableSetOf()

    while (position in coords && (position to direction) !in steps) {
        steps.add(position to direction)
        if (coords[position + direction] == '#') {
            direction = direction.rot()
        } else {
            position += direction
        }
    }

    return steps.map { it.first }.toSet() to ((position to direction) in steps)
}