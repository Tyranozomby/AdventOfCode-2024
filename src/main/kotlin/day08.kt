fun main() = aoc(8) { input ->
    val coords = input.lines().mapIndexed { y, line -> line.mapIndexed { x, c -> Coord(x, y) to c } }.flatten().toMap()
    println(coords)

    val antennas = coords.filterValues { it != '.' }.map { it.value to it.key }.groupBy({ it.first }, { it.second })
    println(antennas)

    val antiNodes = mutableSetOf<Coord>()
    val antiNodes2 = mutableSetOf<Coord>()

    antennas.forEach { (antenna, positions) ->
        println("Antennas $antenna at $positions")

        val combinations = positions.flatMap { a -> positions.filter { it != a }.map { a to it } }
        println(combinations)

        combinations.forEach { (a1, a2) ->
            val distance = a2 - a1
            println("Distance between $a1 and $a2 is $distance")

            var next = a1 - distance
            if (next in coords) {
                antiNodes += next
            }

            antiNodes2 += a1

            while (next in coords) {
                antiNodes2 += next
                next -= distance
            }
        }
    }

    coords.toMutableMap().apply { antiNodes2.filter { this[it] == '.' }.forEach { this[it] = '#' } }.let { map ->
        map.toList().chunked(input.lines()[0].length).forEach { println(it.joinToString("") { it.second.toString() }) }
    }

    antiNodes.size to antiNodes2.size
}