class Solution0003 : Solution() {

    override fun solve(): String {
        return getPrimeFactors(600_851_475_143)
            .keys
            .last()
            .toString()
    }

}