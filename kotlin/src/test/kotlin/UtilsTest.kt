import kotlin.test.Test
import kotlin.test.assertContentEquals

class UtilsTest {

    @Test fun testFibGenerator() {
        val fibGenerator = fibGenerator()
        assertContentEquals(listOf(1, 2, 3, 5, 8, 13), fibGenerator.take(6).toList())
    }

    @Test fun testFactorize() {
        assertContentEquals(listOf(2, 2, 3, 3, 31), factorize(1116))
        assertContentEquals(listOf(2, 2, 5), factorize(20))
        assertContentEquals(listOf(19), factorize(19))
    }

    @Test fun testPrimeGenerator() {
        val primeGenerator = primeGenerator()
        assertContentEquals(listOf(2, 3, 5, 7, 11, 13), primeGenerator.take(6).toList())
    }

}