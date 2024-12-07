import java.math.BigInteger

fun main() = aoc(7) { input ->
    val pairs = input.lines().map { it.split(": ") }.map { it[0].toBigInteger() to it[1].split(" ").map(String::toBigInteger) }

    pairs.map { (total, numbers) ->
        numbers.foldIndexed(listOf<BigInteger>() to listOf<BigInteger>()) { i, (last, last2), n ->
            if (i == 0) listOf(n) to listOf(n) else {
                (last.map { it + n } + last.map { it * n } to
                        last2.map { it + n } + last2.map { it * n } + last2.map { BigInteger(it.toString() + n.toString()) })
            }
        }
            .let { (totals, totals2) -> (if (totals.find { it == total } != null) total else BigInteger.ZERO) to (if (totals2.find { it == total } != null) total else BigInteger.ZERO) }
    }.unzip().let { (part1, part2) -> part1.fold(BigInteger.ZERO) { acc, i -> acc + i } to part2.fold(BigInteger.ZERO) { acc, i -> acc + i } }
}