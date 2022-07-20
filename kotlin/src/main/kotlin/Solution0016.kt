import java.math.BigInteger

class Solution0016 : Solution(16) {

    override fun solve(): String {
        return BigInteger.TWO
            .pow(1000)
            .toString()
            .sumOf { it.digitToInt() }
            .toString()
    }

}