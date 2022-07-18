class Solution0003 : Solution(3) {

    override fun solve(): String {
        return getPrimeFactors(600_851_475_143)
            .keys
            .last()
            .toString()
    }

}