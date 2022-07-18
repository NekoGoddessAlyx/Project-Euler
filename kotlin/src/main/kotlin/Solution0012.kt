class Solution0012 : Solution(12) {

    override fun solve(): String {
        return triangleNumberGenerator()
            .dropWhile { getNumberOfDivisors(it) <= 500 }
            .take(1)
            .single()
            .toString()
    }

}