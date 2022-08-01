class Solution0009 : Solution() {

    override fun solve(): String {
        for (a in 1L until 1000L) {
            for (b in a until 1000L - a) {
                val c = 1000L - a - b
                if (a * a + b * b == c * c) return (a * b * c).toString()
            }
        }

        return ""
    }

}