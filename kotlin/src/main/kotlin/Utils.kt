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