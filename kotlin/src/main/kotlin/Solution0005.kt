import kotlin.math.max
import kotlin.math.pow

class Solution0005 : Solution() {

    override fun solve(): String {
        return lcm((2L..20L)).toString()
    }

    //
    /*
     * Calculates the lcm from the prime decomposition
     *
     * The fundamental theorem of arithmetic states that every integer greater than 1
     * can be represented uniquely as a product of prime numbers, up to the order of the factors.
     *
     * Taking the greatest exponent of each factor of each number and multiplying them yields the least common multiple.
     */
    private fun lcm(numbers: Iterable<Long>): Long {
        val primeFactorsMap = mutableMapOf<Long, Long>()

        numbers.forEach {
            val factors = getPrimeFactors(it)
            factors.forEach { (k, v) -> primeFactorsMap.merge(k, v) { v1, v2 -> max(v1, v2)} }
        }

        var num = 1L
        for ((k, v) in primeFactorsMap) {
            num *= k.toDouble().pow(v.toDouble()).toLong()
        }

        return num
    }

}