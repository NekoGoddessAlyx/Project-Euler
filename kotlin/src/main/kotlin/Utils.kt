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

/** Returns a list of all prime factors of a number (including repeated factors) */
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

    // handle the case of prime numbers greater than 2
    if (number > 2) primeFactors += number

    return primeFactors
}

/** Returns a list of all prime factors of a number as a map */
fun factorizeMap(n: Long): Map<Long, Long> {
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