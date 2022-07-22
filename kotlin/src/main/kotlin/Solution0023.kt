import kotlin.math.ceil

class Solution0023 : Solution() {

    override fun solve(): String {
        val abundance = (1L..28123L).map { getProperDivisors(it).sum() > it }

        return (1..28123).filter { n ->
            val end = ceil(n.toDouble() / 2.0).toInt()
            for (i in 1..end) {
                if (abundance[i - 1] && abundance[n - i - 1]) return@filter false
            }
            return@filter true
        }.sumOf { it }.toString()
    }

}