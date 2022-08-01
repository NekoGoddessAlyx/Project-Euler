class Solution0022 : Solution() {

    override fun solve(): String {
        val namesRaw: String = this.javaClass.getResource("p022_names.txt")?.readText()
            ?: throw Exception("Could not load 'p022_names.txt'")
        val names = namesRaw.split(",")
            .map { it.removeSurrounding("\"") }
            .toMutableList()

        names.sort()

        // the names are all upper case, subtract 64 to get a 1-based character number (A = 1, B = 2, ...)
        return names.foldIndexed(0L) {
            index, acc, s ->  acc + (index + 1) * s.sumOf { it.code - 64 }
        }.toString()
    }

}