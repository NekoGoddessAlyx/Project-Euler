import java.math.BigInteger

class Solution0015 : Solution() {

    /*
     * (1, 0) can be restated as starting at (0, 0) with goal (19, 20)
     * (0, 0) with goal (0, 0) can be said to be complete
     *
     * solve recursively
     *
     * The total number of moves that can be made at one point are the sum of both possible single movements
     *
     * If either are 0, there is only one path
     * If x or y are 1, there are y + 1 or x + 1 moves respectively
     * If x and y are equal, both possible moves will have the same result
     *
     *
     * AFTER SOLVING
     * I had drawn a diagram and figured out some rules as outline above.
     * After solving and looking at the forums, I have discovered I had stumbled on Pascal's Triangle.
     * I will return to this another time after learning more about it because this is a pretty slow solution. But fun!
     */

//    override fun solve(): String {
//        return solutionsInGrid(20, 20).toString()
//    }
//
//    private fun solutionsInGrid(x: Int, y: Int): Long {
//        // base conditions
//        if (x == 0 || y == 0) return 1L
//        if (x == 1 && y == 1) return 2L
//        if (x == 1) return y + 1L
//        if (y == 1) return x + 1L
//
//        // save some time if they're equal
//        if (x == y) return solutionsInGrid(x - 1, y) * 2
//
//        // sum of both possible moves
//        return solutionsInGrid(x - 1, y) + solutionsInGrid(x, y - 1)
//    }

    /*
     * Revisiting time!
     * Time to have this problem not be a hiccup in my tests
     *
     * I have now read up on pascal's triangle
     *
     * the formula for working out the value at any place in the triangle:
     *
     *    n!
     * -------
     * k!(n-k)!
     */

    override fun solve(): String {
        return combination(40, 20).toString()
    }

}