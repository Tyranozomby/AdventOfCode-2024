fun main() = aoc(6) { input ->
    val coords = input.lines().mapIndexed { y, line -> line.mapIndexed { x, c -> Coord(x, y) to c } }.flatten().toMap()
    val start = coords.filterValues { it == '^' }.keys.first()

    loop(coords, start).let { steps ->
        steps.first.size to steps.first.fold(0) { sum2, obs ->
            sum2 + if (loop(coords.toMutableMap().apply { this[obs] = '#' }, start).second) 1 else 0
        }
    }
}

fun loop(coords: Map<Coord, Char>, start: Coord): Pair<Set<Coord>, Boolean> {
    var position = start
    var direction = Coord(0, -1)
    val steps: MutableSet<Pair<Coord, Coord>> = mutableSetOf()

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