import kotlin.system.exitProcess

// Solution base class
abstract class Solution(val problemNumber: Int) {

    override fun toString(): String = problemNumberAndLink(problemNumber)

    abstract fun solve(): String

}

private fun problemNumberAndLink(problemNumber: Int): String =
    "Problem %1\$04d (https://projecteuler.net/problem=%1\$d)".format(problemNumber)

// solutions map, ensure new solutions are added here
val solutions = mapOf(
    1 to Solution0001(),
    2 to Solution0002(),
    3 to Solution0003(),
    4 to Solution0004(),
    5 to Solution0005(),
    6 to Solution0006(),
    7 to Solution0007(),
)

fun main() {
    println("Project Euler")
    println("${solutions.size} problem(s) have been solved.")

    while (true) {
        print("Run solution (enter a number or anything else to quit): ")
        val input = readlnOrNull()?.toIntOrNull() ?: exitProcess(0)

        val solver = solutions.getOrDefault(input, null)
        if (solver == null) {
            println(problemNumberAndLink(input))
            println("No solution available for problem $input.")
            continue
        }

        println(solver.toString())
        println(solver.solve())
    }
}