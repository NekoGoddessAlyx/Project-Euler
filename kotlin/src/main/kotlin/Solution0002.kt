class Solution0002 : Solution(2) {

    override fun solve(): String {
        return fibGenerator()
            .filter { it % 2 == 0 }
            .takeWhile { it < 4_000_000 }
            .sum()
            .toString()
    }

}