class Solution0028 : Solution() {

    override fun solve(): String {
//        return (1 + (3..1001).step(2).sumOf { (it - 2) * (it - 2) * 4 + (it - 1) * 10 }).toString()

        // hhhhhhhh
        val n = 1001.0
        val s = 2.0 * n * n * n / 3.0 +
                n * n / 2.0 +
                4.0 * n / 3.0 +
                -1.5
        return s.toLong().toString()
    }

}