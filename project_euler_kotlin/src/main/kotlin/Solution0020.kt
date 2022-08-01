class Solution0020 : Solution() {

    override fun solve(): String {
        return factorial(100)
            .toString()
            .sumOf { it.digitToInt().toLong() }
            .toString()
    }

}