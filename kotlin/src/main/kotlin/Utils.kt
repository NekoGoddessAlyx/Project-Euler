/** Fibonacci sequence generator starting with 1 and 2 */
fun fibGenerator() = sequence {
    // seed values (will result in the first number being 1 and the second number being 2
    var a = 0
    var b = 1

    while (true) {
        var next = a + b
        a = b
        b = next
        yield(next)
    }
}

fun sqrt(n: Long): Long = kotlin.math.sqrt(n.toDouble()).toLong()

fun factorize(n: Long): List<Long> {
    var number = n
    val primeFactors = mutableListOf<Long>()

    // remove the 2s until the number is odd
    while (number % 2 == 0L) {
        primeFactors += 2L
        number /= 2L
    }

    // number is now odd, increment by 2
    val factorizationEnd = sqrt(n)
    var factor = 3L

    while (factor <= factorizationEnd) {
        while (number % factor == 0L) {
            primeFactors += factor
            number /= factor
        }

        factor += 2
    }

    return primeFactors
}