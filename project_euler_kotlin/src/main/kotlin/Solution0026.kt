class Solution0026 : Solution() {

    override fun solve(): String {
        return unitFractionGenerator()
            .takeWhile { it.divisor < 1_000 }
            .maxByOrNull { it.repeatingFraction.length }
            ?.divisor
            .toString()
    }

}