class Solution0028 : Solution() {

    override fun solve(): String {
        return (1 + (3..1001).step(2).sumOf { (it - 2) * (it - 2) * 4 + (it - 1) * 10 }).toString()
    }

}