import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class SolutionTest {

    @ParameterizedTest(name = "Problem {0}")
    @CsvFileSource(files = ["../solutions.csv"], numLinesToSkip = 1)
    fun testSolutions(problemNumber: String, solution: String) {
        assertEquals(solution, getSolution(problemNumber.toInt())?.solve())
    }

}