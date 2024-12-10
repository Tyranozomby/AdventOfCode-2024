fun main() = aoc(9) { input ->
    var index = 0
    var isSpace = false

    val list = mutableListOf<Int>()
    val pairList = mutableListOf<Pair<Int, Int>>()

    input.map(Char::toString).map(String::toInt).forEach { i ->
        repeat(i) { list.add(if (isSpace) -1 else index) }

        if (isSpace) {
            pairList.add(-1 to i)
            index++
        } else {
            pairList.add(index to i)
        }

        isSpace = !isSpace
    }

    for (i in list.size - 1 downTo 0) {
        println(i)
        val last = list[i]

        for (j in 0 until i) {
            val space = list[j]
            if (space == -1) {
                println(j)
                list[j] = last
                list[i] = -1
                break
            }
        }
    }

    for (i in pairList.size - 1 downTo 0) {
        println(i)
        val last = pairList[i]
        if (last.first == -1) {
            continue
        }

        for (j in 0 until i) {
            val space = pairList[j]
            if (space.first == -1 && space.second >= last.second) {
                pairList[j] = (space.first to (space.second - last.second))
                pairList.add(j, last)
                pairList[i + 1] = -1 to last.second
                break
            }
        }
    }

    val fold = pairList.fold(mutableListOf<Int>()) { acc, v ->
        repeat(v.second) { acc.add(v.first) }
        acc
    }

    list.foldIndexed(0L) { i, acc, v -> acc + (if (v == -1) 0 else v) * i } to fold.foldIndexed(0L) { i, acc, v -> acc + (if (v == -1) 0 else v) * i }
}