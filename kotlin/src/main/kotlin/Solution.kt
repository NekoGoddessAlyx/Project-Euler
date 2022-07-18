import kotlin.reflect.full.primaryConstructor
import kotlin.system.exitProcess

// Solution base class
sealed class Solution(val problemNumber: Int) {

    override fun toString(): String = problemNumberAndLink(problemNumber)

    abstract fun solve(): String

}

private fun problemNumberAndLink(problemNumber: Int): String =
    "Problem %1\$04d (https://projecteuler.net/problem=%1\$d)".format(problemNumber)

private val solutions: Map<Int, Solution> by lazy {
    Solution::class.sealedSubclasses.map {
        it.primaryConstructor?.call() ?: throw Exception("There was a problem loading solutions")
    }.associateBy { it.problemNumber }
}

val solutionCount: Int = solutions.size

fun getSolution(problemNumber: Int): Solution? = solutions[problemNumber]

fun main() {
    println("Project Euler")
    println("$solutionCount problem(s) have been solved.")

    while (true) {
        print("Run solution (enter a number or anything else to quit): ")
        val input = readlnOrNull()?.toIntOrNull() ?: exitProcess(0)

        val solver = getSolution(input)
        if (solver == null) {
            println(problemNumberAndLink(input))
            println("No solution available for problem $input.")
            continue
        }

        println(solver.toString())
        println(solver.solve())
    }
}