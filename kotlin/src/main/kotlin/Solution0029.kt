import kotlin.math.pow

class Solution0029 : Solution() {

    override fun solve(): String {
        return (2..100)
            .flatMap { a -> (2..100).map { b -> a.toDouble().pow(b) } }
            .distinct()
            .size
            .toString()
    }

}