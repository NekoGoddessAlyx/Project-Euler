import java.math.BigInteger
import kotlin.math.ceil
import kotlin.math.sqrt

/** Fibonacci sequence generator starting with 1 and 1 */
fun fibGenerator() = sequence {
    yield(1)

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

/** Fibonacci sequence generator starting with 1 and 1 */
fun bigFibGenerator() = sequence {
    yield(BigInteger.ONE)

    // seed values (will result in the first number being 1 and the second number being 2
    var a = BigInteger.ZERO
    var b = BigInteger.ONE

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

fun getProperDivisors(n: Long): List<Long> = getDivisors(n).filter { it < n }

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

fun factorial(n: Long): BigInteger = when {
    n < 0 -> throw IllegalArgumentException("n must be a non-negative integer.")
    n == 0L -> BigInteger.ONE
    n == 1L -> BigInteger.ONE
    else -> (2..n).fold(BigInteger.ONE) { acc, l -> acc * BigInteger.valueOf(l) }
}

/** n choose k. The number of combinations of n things taken k at a time */
fun combination(n: Long, k: Long): Long {
    return (factorial(n) / (factorial(k) * factorial(n - k))).toLong()
}

/** Similar to combinations but the order matters (where it doesn't for combinations) */
fun permutations(n: Long, k: Long): Long {
    return (factorial(n) / factorial(n - k)).toLong()
}

fun <T : Comparable<T>> orderedPermutationGenerator(input: Iterable<T>) = sequence {
    val elements = input.toMutableList()
    if (elements.isEmpty()) return@sequence
    elements.sort()

    var hasNext = true

    while (hasNext) {
        yield(elements.toList())

        var k = 0
        var l = 0
        hasNext = false

        for (i in elements.size - 1 downTo 1) {
            if (elements[i] > elements[i - 1]) {
                k = i - 1
                hasNext = true
                break
            }
        }

        for (i in elements.size - 1 downTo k + 1) {
            if (elements[i] > elements[k]) {
                l = i
                break
            }
        }

        val t = elements[k]
        elements[k] = elements[l]
        elements[l] = t

        elements.subList(k + 1, elements.size).reverse()
    }
}

/**
 * Represents a number 1/[divisor]
 *
 * Contains a nonrepeating fractional part and a repeating fractional part as strings
 * The full decimal representation can be calculated as
 *
 * `0.(nonRepeatingFraction)(repeatingFraction)(repeatingFraction)(repeatingFraction)(...)`
 */
data class UnitFraction(val divisor: Long, val nonRepeatingFraction: String, val repeatingFraction: String) {
    override fun toString(): String = buildString {
        append("0.")
        append(nonRepeatingFraction)
        if (repeatingFraction.isNotEmpty()) {
            append("(")
            append(repeatingFraction)
            append(")")
        }
    }
}

/** Generates unit fractions (a number that contains a 1 in the numerator) starting from 2 */
fun unitFractionGenerator() = sequence {
    // the divisor (1/d)
    var d = 2L

    while (true) {
        // digits will hold all digits
        // if/when repeating digits are found, they will be removed and added to repeating digits
        val digits = StringBuilder()
        val remainders = mutableListOf<Long>()
        val repeatingDigits = StringBuilder()

        // dividend is the number to be continually divided to calculate the digits
        var dividend = 10L

        // dividend will be expanded (by 10) when necessary
        // keep track of that in order to append a 0 digit when appropriate
        var hadDigit = false

        main@while (dividend != 0L) {
            // expand the dividend if we can't neatly divide out the divisor
            while (dividend < d) {
                dividend *= 10L
                if (!hadDigit) {
                    digits.append(0)
                    remainders += dividend
                }
                hadDigit = false
            }

            // calculate the remainder and divide out what we can
            val remainder = dividend % d
            val digit = (dividend - remainder) / d

            // carry the remainder
            dividend = remainder

            // check if the current digit is already contained in the digits
            // if so, we're done dividing as we've found a repeating cycle
            for (index in digits.indices) {
                val c = digits[index]
                if (c == digit.toString()[0] && remainders[index] == remainder) {
                    if (index == digits.lastIndex || index == 0) {
                        repeatingDigits.appendRange(digits, index, digits.length)
                        digits.delete(index, digits.length)
                        break@main
                    } else if (digits[index - 1] == digits.last()) {
                        repeatingDigits.appendRange(digits, index - 1, digits.length - 1)
                        digits.delete(index - 1, digits.length)
                        break@main
                    }
                }
            }

            // otherwise, append the digit
            digits.append(digit)
            remainders += dividend
            hadDigit = true
        }

        yield(UnitFraction(d++, digits.toString(), repeatingDigits.toString()))
    }
}