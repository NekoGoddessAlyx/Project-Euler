class Solution0010 : Solution() {

    override fun solve(): String {
        return primeGenerator().takeWhile { it < 2_000_000L }.sum().toString()
    }

}