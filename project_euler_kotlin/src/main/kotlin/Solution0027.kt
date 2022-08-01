class Solution0027 : Solution() {

    override fun solve(): String {
        var bestA = 0L
        var bestB = 0L
        var bestN = 0L

        for (a in -999L .. 999L) {
            for (b in -1000L..1000L) {
                var n = 0L
                while (isPrime((n * n) + (a * n) + b)) n++
                if (n > bestN) {
                    bestA = a
                    bestB = b
                    bestN = n
                }
            }
        }

        return (bestA * bestB).toString()
    }

}