import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class UtilsTest {

    @Test
    fun testFibGenerator() {
        val fibGenerator = fibGenerator()
        assertContentEquals(listOf(1, 1, 2, 3, 5, 8, 13), fibGenerator.take(7).toList())
    }

    @Test
    fun testBigFibGenerator() {
        val fibGenerator = bigFibGenerator()
        assertContentEquals(
            listOf(
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.TWO,
                BigInteger.valueOf(3),
                BigInteger.valueOf(5),
                BigInteger.valueOf(8),
                BigInteger.valueOf(13)
            ), fibGenerator.take(7).toList()
        )
    }

    @Test
    fun testGetPrimeFactors() {
        assertMapContentEquals(mapOf(2L to 2L, 3L to 2L, 31L to 1L), getPrimeFactors(1116L))
        assertMapContentEquals(mapOf(2L to 2L, 5L to 1L), getPrimeFactors(20L))
        assertMapContentEquals(mapOf(19L to 1L), getPrimeFactors(19L))
    }

    private fun <K, V> assertMapContentEquals(expected: Map<K, V>, actual: Map<K, V>) {
        assertContentEquals(expected.keys.toList(), actual.keys.toList())
        assertContentEquals(expected.values.toList(), actual.values.toList())
    }

    @Test
    fun testGetDivisors() {
        assertContentEquals(listOf(1L), getDivisors(1L))
        assertContentEquals(listOf(1L, 3L), getDivisors(3L))
        assertContentEquals(listOf(1L, 2L, 3L, 6L), getDivisors(6L))
        assertContentEquals(listOf(1L, 3L, 7L, 21L), getDivisors(21L))
        assertContentEquals(listOf(1L, 2L, 4L, 7L, 14L, 28L), getDivisors(28L))
    }

    @Test
    fun getNumDivisors() {
        assertEquals(1, getNumberOfDivisors(1L))
        assertEquals(2, getNumberOfDivisors(3L))
        assertEquals(4, getNumberOfDivisors(6L))
        assertEquals(3, getNumberOfDivisors(9L))
        assertEquals(4, getNumberOfDivisors(10L))
        assertEquals(4, getNumberOfDivisors(15L))
        assertEquals(4, getNumberOfDivisors(21L))
        assertEquals(6, getNumberOfDivisors(28L))
    }

    @Test
    fun testPrimeGenerator() {
        val primeGenerator = primeGenerator()
        assertContentEquals(listOf(2, 3, 5, 7, 11, 13), primeGenerator.take(6).toList())
    }

    @Test
    fun testTriangleNumberGenerator() {
        val triangleGenerator = triangleNumberGenerator()
        assertContentEquals(listOf(1L, 3L, 6L, 10L, 15L, 21L, 28L), triangleGenerator.take(7).toList())
    }

    @Test
    fun testCollatzSequenceGenerator() {
        val collatzSequenceGenerator = collatzSequenceGenerator(13L)
        assertContentEquals(listOf(13L, 40L, 20L, 10L, 5L, 16L, 8L, 4L, 2L, 1L), collatzSequenceGenerator.toList())
    }

    @Test
    fun testCollatzSequenceLength() {
        assertEquals(10, collatzSequenceLength(13))
        assertEquals(2, collatzSequenceLength(2))
        assertEquals(7, collatzSequenceLength(10))
    }

    @Test
    fun testFactorial() {
        assertEquals(BigInteger.ONE, factorial(0L))
        assertEquals(BigInteger.ONE, factorial(1L))
        assertEquals(BigInteger.valueOf(2), factorial(2L))
        assertEquals(BigInteger.valueOf(6), factorial(3L))
        assertEquals(BigInteger.valueOf(24), factorial(4L))
        assertEquals(BigInteger.valueOf(3628800), factorial(10L))
    }

    @Test
    fun testCombinations() {
        assertEquals(1, combination(0, 0))
        assertEquals(2, combination(2, 1))
        assertEquals(6, combination(4, 2))
        assertEquals(137846528820, combination(40, 20))
    }

    @Test
    fun testOrderedPermutationsGenerator() {
        val orderedPermutationsGenerator = orderedPermutationGenerator("ABC".toList())
        assertContentEquals(
            listOf("ABC", "ACB", "BAC", "BCA", "CAB", "CBA"),
            orderedPermutationsGenerator.take(6).map { String(it.toCharArray()) }.toList()
        )

        val orderedPermutationsGenerator2 = orderedPermutationGenerator("ABC".toList(), 2)
        assertContentEquals(
            listOf("AB", "AC", "BA", "BC", "CA", "CB"),
            orderedPermutationsGenerator2.take(6).map { String(it.toCharArray()) }.toList()
        )
    }

    @Test
    fun testUnitFractionGenerator() {
        val unitFractionGenerator = unitFractionGenerator()

        val firstNine = unitFractionGenerator.take(9).map { it.toString() }.toList()
        assertContentEquals(
            listOf("0.5", "0.(3)", "0.25", "0.2", "0.1(6)", "0.(142857)", "0.125", "0.(1)", "0.1"),
            firstNine
        )

        // check each number in the format:
        // 0.(nonRepeatingFraction)(repeatingFraction)(repeatingFraction)(repeatingFraction)
        unitFractionGenerator
            .take(1_000)
            .forEach {

                // calculate the expected result using big decimal
                val scale = it.nonRepeatingFraction.length + it.repeatingFraction.length * 3 + 3
                val one = BigDecimal.ONE.setScale(scale, RoundingMode.UNNECESSARY)
                val divisor = BigDecimal.valueOf(it.divisor)
                var expected = (one / divisor).setScale(scale, RoundingMode.DOWN).toString()
                expected = expected.slice(0 until expected.lastIndex - 2)

                // build the actual representation
                val actual = buildString {
                    append("0.")
                    append(it.nonRepeatingFraction)
                    append(it.repeatingFraction)
                    append(it.repeatingFraction)
                    append(it.repeatingFraction)
                }

                assertEquals(expected, actual, "d = ${it.divisor} , $it")
            }
    }

}