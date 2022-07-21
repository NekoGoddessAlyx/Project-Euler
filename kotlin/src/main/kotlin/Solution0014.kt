class Solution0014 : Solution() {

    override fun solve(): String {
        return (999_999L downTo 1L)
            .maxByOrNull(::collatzSequenceLength)
            .toString()
    }

}