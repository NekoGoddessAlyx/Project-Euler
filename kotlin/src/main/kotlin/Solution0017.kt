class Solution0017 : Solution() {

    override fun solve(): String {
        return (1L..1000L)
            .sumOf { makeWordy(it).length.toLong() }
            .toString()
    }

    private val onesMap = mapOf(
        '0' to "zero",
        '1' to "one",
        '2' to "two",
        '3' to "three",
        '4' to "four",
        '5' to "five",
        '6' to "six",
        '7' to "seven",
        '8' to "eight",
        '9' to "nine"
    )

    private val tensMap = mapOf(
        '0' to "",
        '1' to "",
        '2' to "twenty",
        '3' to "thirty",
        '4' to "forty",
        '5' to "fifty",
        '6' to "sixty",
        '7' to "seventy",
        '8' to "eighty",
        '9' to "ninety"
    )

    private val teensMap = mapOf(
        '0' to "ten",
        '1' to "eleven",
        '2' to "twelve",
        '3' to "thirteen",
        '4' to "fourteen",
        '5' to "fifteen",
        '6' to "sixteen",
        '7' to "seventeen",
        '8' to "eighteen",
        '9' to "nineteen"
    )

    /** Magically turns your number into words! Spaces and hyphens not included */
    private fun makeWordy(n: Long): String {
        if (n !in 1 .. 1000)
            throw IllegalArgumentException("Number must be in range 1 <= n <= 1000")

        // thousands
        if (n == 1000L) return "onethousand"

        val digits = n.toString()
        var index = 0
        val sb = StringBuilder()

        // hundreds
        if (n >= 100) {
            sb.append(onesMap[digits[0]])
            sb.append("hundred")

            if (digits[1] != '0' || digits[2] != '0') sb.append("and")

            // this is for checking the tens place
            index++
        }

        // tens
        if (n >= 10) {
            when (digits[index]) {
                '0' -> {}
                '1' -> {
                    sb.append(teensMap[digits[index + 1]])
                    return sb.toString()
                }
                else -> sb.append(tensMap[digits[index]])
            }

            index++

            if (digits[index] == '0') return sb.toString()
        }

        // ones
        sb.append(onesMap[digits[index]])
        return sb.toString()
    }

}