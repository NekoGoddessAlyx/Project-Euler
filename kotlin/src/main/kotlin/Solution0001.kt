class Solution0001 : Solution(1) {

    override fun solve(): String {
        return (0 until 1000)
            .asSequence()
            .filter { it % 3 == 0 || it % 5 == 0 }
            .sum()
            .toString()
    }

}