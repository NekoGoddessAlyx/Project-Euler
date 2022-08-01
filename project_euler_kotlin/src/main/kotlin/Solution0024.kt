class Solution0024 : Solution() {

    override fun solve(): String {
        return orderedPermutationGenerator(0..9)
            .take(1_000_000)
            .last()
            .joinToString("")
    }
}