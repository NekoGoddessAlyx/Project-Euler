class Solution0025 : Solution() {

    override fun solve(): String {
        // problem is 1-based
        return bigFibGenerator()
            .indexOfFirst { it.toString().length == 1000 }
            .plus(1)
            .toString()
    }

}