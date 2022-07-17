class Solution0007 : Solution(7) {

    override fun solve(): String {
        return primeGenerator().take(10_001).last().toString()
    }

}