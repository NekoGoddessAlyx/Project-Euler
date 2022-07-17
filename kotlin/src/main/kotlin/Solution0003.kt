class Solution0003 : Solution(3) {

    override fun solve(): String {
        return factorize(600_851_475_143)
            .last()
            .toString()
    }

}