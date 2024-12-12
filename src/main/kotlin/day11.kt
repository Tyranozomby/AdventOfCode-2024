fun main() = aoc(11) { input ->
    val stones = input.split(" ").map { it.toLong() }.toMutableList()
    println(stones)

    val list = mutableMapOf<String, Long>()

    stones.sumOf { startingStone -> count(startingStone, 25, list) } to stones.sumOf { startingStone -> count(startingStone, 75, list) }
}

fun count(stone: Long, iter: Int, list: MutableMap<String, Long>): Long {
    if (iter == 0) return 1

    val key = "$stone,$iter"
    if (!list.containsKey(key)) {
        list[key] = when {
            stone == 0L -> count(1, iter - 1, list)
            stone.toString().length % 2 == 0 -> {
                val split = stone.toString().chunked(stone.toString().length / 2)
                count(split[0].toLong(), iter - 1, list) + count(split[1].toLong(), iter - 1, list)
            }

            else -> count(stone * 2024, iter - 1, list)
        }
    }

    return list[key]!!
}