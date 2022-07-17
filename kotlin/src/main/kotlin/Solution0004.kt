class Solution0004: Solution(4) {

    override fun solve(): String {
        return (100..999)
            .flatMap { a -> (a..999).map { b -> a * b } }
            .filter { it.isPalindrome() }
            .maxOf { it }
            .toString()
    }

    private fun Int.isPalindrome(): Boolean {
        val string = this.toString()
        val len = string.length
        var index = 0

        while (index <= len / 2) {
            if (string[index] != string[len - 1 - index]) return false
            index++
        }

        return true
    }

}