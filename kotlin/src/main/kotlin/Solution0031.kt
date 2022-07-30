import kotlin.math.floor

class Solution0031 : Solution() {

    override fun solve(): String {
        return total(200, listOf(200, 100, 50, 20, 10, 5, 2, 1))
            .toString()
    }

    private fun total(total: Int, coins: List<Int>): Int {
        // only one coin type left, there's only one way to add them up
        if (coins.size == 1) return 1

        val coin = coins.first()

        // this coin is too big, ignore it
        if (coin > total) return total(total, coins.subList(1, coins.size))

        // the sum of all possible permutations with each possible number of the current coin
        return (0..floor(total.toDouble() / coin).toInt())
            .sumOf { total(total - coin * it, coins.subList(1, coins.size)) }
    }

}