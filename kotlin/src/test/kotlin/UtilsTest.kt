import kotlin.test.Test
import kotlin.test.assertContentEquals

class UtilsTest {

    @Test fun testFibGenerator() {
        val fibGenerator = fibGenerator()
        assertContentEquals(listOf(1, 2, 3, 5, 8, 13), fibGenerator.take(6).toList())
    }

    @Test fun testGetPrimeFactors() {
        assertMapContentEquals(mapOf(2L to 2L, 3L to 2L, 31L to 1L), getPrimeFactors(1116L))
        assertMapContentEquals(mapOf(2L to 2L, 5L to 1L), getPrimeFactors(20L))
        assertMapContentEquals(mapOf(19L to 1L), getPrimeFactors(19L))
    }

    private fun <K, V> assertMapContentEquals(expected: Map<K, V>, actual: Map<K, V>) {
        assertContentEquals(expected.keys.toList(), actual.keys.toList())
        assertContentEquals(expected.values.toList(), actual.values.toList())
    }

    @Test fun testPrimeGenerator() {
        val primeGenerator = primeGenerator()
        assertContentEquals(listOf(2, 3, 5, 7, 11, 13), primeGenerator.take(6).toList())
    }

}