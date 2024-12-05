fun main() = aoc(3) { input ->
    val matches = Regex("""(do\(\))|(don't\(\))|mul\((\d+),(\d+)\)""").findAll(input)
    var isEnabled = true

    matches.map { it.destructured }.fold(0 to 0) { acc, (hasDo, hasDont, x, y) ->
        if (hasDo.isNotBlank() || hasDont.isNotBlank()) {
            isEnabled = hasDo.isNotBlank()
            return@fold acc
        } else {
            val mul = x.toInt() * y.toInt()
            return@fold acc.first + mul to acc.second + if (isEnabled) mul else 0
        }
    }
}