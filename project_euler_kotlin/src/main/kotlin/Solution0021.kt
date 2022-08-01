class Solution0021 : Solution() {

    override fun solve(): String {
        // [n : d(n)]
        val sumOfProperDivisors = (1L until 10_000L)
            .associateWith { n -> getProperDivisors(n).sum() }

        return sumOfProperDivisors
            .filter { it.key != it.value && sumOfProperDivisors.getOrDefault(it.value, 0) == it.key }
            .keys
            .sum()
            .toString()
    }

}