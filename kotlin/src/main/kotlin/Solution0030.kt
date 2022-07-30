import kotlin.math.pow

class Solution0030 : Solution() {

    override fun solve(): String {
        // 9^5 = 59049
        var numDigits = 1
        while (numDigits * 59049 >= all9s(numDigits)) numDigits++

        return (10..all9s(numDigits))
            .filter { it.toString().sumOf { c -> c.digitToInt().toDouble().pow(5) } == it.toDouble() }
            .sum()
            .toString()
    }

    private fun all9s(numDigits: Int) = "9".repeat(numDigits).toInt()

}