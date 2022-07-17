class Solution0006 : Solution(6) {

    override fun solve(): String {
        var squareOfSum = (1L..100L).sum()
        squareOfSum *= squareOfSum

        val sumOfSquares = (1L..100L).sumOf { it * it }

        return (squareOfSum - sumOfSquares).toString()
    }

}