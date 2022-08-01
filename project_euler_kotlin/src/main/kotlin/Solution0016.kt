import java.math.BigInteger

class Solution0016 : Solution() {

    override fun solve(): String {
        return BigInteger.TWO
            .pow(1000)
            .toString()
            .sumOf { it.digitToInt() }
            .toString()
    }

}