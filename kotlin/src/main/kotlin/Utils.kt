import kotlin.math.ceil
import kotlin.math.sqrt

/** Fibonacci sequence generator starting with 1 and 2 */
fun fibGenerator() = sequence {
    // seed values (will result in the first number being 1 and the second number being 2
    var a = 0
    var b = 1

    while (true) {
        val next = a + b
        a = b
        b = next
        yield(next)
    }
}

fun sqrt(n: Long): Long = sqrt(n.toDouble()).toLong()

/** Returns a list of all prime factors of a number */
fun getPrimeFactors(n: Long): Map<Long, Long> {
    var number = n
    val primeFactors = mutableMapOf<Long, Long>()

    // remove the 2s until the number is odd
    while (number % 2 == 0L) {
        primeFactors[2L] = primeFactors.getOrDefault(2L, 0L) + 1
        number /= 2L
    }

    // number is now odd, increment by 2
    val factorizationEnd = sqrt(n)
    var factor = 3L

    while (factor <= factorizationEnd) {
        while (number % factor == 0L) {
            primeFactors[factor] = primeFactors.getOrDefault(factor, 0L) + 1
            number /= factor
        }

        factor += 2
    }

    // handle the case of prime numbers greater than 2
    if (number > 2) primeFactors[number] = primeFactors.getOrDefault(number, 0L) + 1

    return primeFactors
}

fun getDivisors(n: Long): List<Long> {
    val factors = mutableListOf(1L)

    var factor = 2L
    val end = ceil(sqrt(n.toDouble())).toLong()
    while (factor <= end) {
        if (n % factor == 0L) {
            factors += factor
            factors += n / factor
        }
        factor++
    }

    factors += n
    factors.sort()
    return factors.distinct()
}

fun getNumberOfDivisors(n: Long): Long {
    var numFactors = 0L
    var factor = 1L

    val end = ceil(sqrt(n.toDouble())).toLong()
    while (factor < end) {
        if (n % factor == 0L) numFactors += 2
        factor++
    }

    // take care of square numbers
    if (sqrt(n) * sqrt(n) == n) numFactors++

    return numFactors
}

/** Prime number sequence generator through brute force */
fun primeGenerator(): Sequence<Long> = sequence {
    yield(2L)

    var number = 3L

    while (true) {
        if (isPrime(number)) yield(number)

        number += 2L
    }
}

/** Optimized test for prime numbers */
fun isPrime(n: Long): Boolean {
    if (n == 2L || n == 3L) return true

    if (n <= 1L || n % 2L == 0L || n % 3L == 0L) return false

    var i = 5
    while (i * i <= n) {
        if (n % i == 0L || n % (i + 2L) == 0L) return false
        i += 6
    }

    return true
}

fun triangleNumberGenerator() = sequence<Long> {
    var previousTotal = 0L
    var index = 1L

    while (true) {
        previousTotal += index
        index++
        yield(previousTotal)
    }
}

/** Collatz sequence, start must be >= 2 */
fun collatzSequenceGenerator(start: Long) = sequence {
    yield(start)

    var n = start
    while (true) {
        if (n % 2L == 0L) n /= 2L else n = 3 * n + 1
        yield(n)

        if (n == 1L) return@sequence
    }
}

/** Collatz sequence length, start must be >= 2 */
fun collatzSequenceLength(start: Long): Long {
    var length = 1L

    var n = start
    while (true) {
        if (n % 2L == 0L) n /= 2L else n = 3 * n + 1
        length++

        // have faith they all get to 1 eventually :)
        if (n == 1L) return length
    }
}