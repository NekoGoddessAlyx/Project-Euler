import java.math.BigInteger
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
    }

}