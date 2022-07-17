import kotlin.test.Test
import kotlin.test.assertContentEquals

class UtilsTest {

    @Test fun testFibGenerator() {
        val fibGenerator = fibGenerator()
        assertContentEquals(listOf(1, 2, 3, 5, 8, 13), fibGenerator.take(6).toList())
    }

}