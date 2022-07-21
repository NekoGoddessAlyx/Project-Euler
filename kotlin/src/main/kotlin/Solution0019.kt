class Solution0019 : Solution(19) {

    private fun numDaysInMonth(year: Int, month: Int) = when (month) {
        0 -> 31 // jan
        1 -> if (year % 4 == 0) 29 else 28  // feb
        2 -> 31 // mar
        3 -> 30 // apr
        4 -> 31 // may
        5 -> 30 // jun
        6 -> 31 // jul
        7 -> 31 // aug
        8 -> 30 // sep
        9 -> 31 // oct
        10 -> 30 // nov
        11 -> 31 // dec
        else -> 1_000_000
    }

    override fun solve(): String {
        var dayInWeek = 1 // 0-based (sunday = 0, saturday = 6)

        // fast-forward the day of week to 1 JAN 1901
        for (month in 0 until 12) {
            dayInWeek += numDaysInMonth(1900, month)
            dayInWeek %= 7
        }

        var firstOfMonthSundays = 0
        for (year in 1901..2000) {
            for (month in 0 until 12) {
                if (dayInWeek == 0) firstOfMonthSundays++

                dayInWeek += numDaysInMonth(year, month)
                dayInWeek %= 7
            }
        }

        return firstOfMonthSundays.toString()
    }

}