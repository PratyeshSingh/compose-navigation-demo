package com.example.compose_navigation_demo

import java.text.DateFormat
import java.util.Date
import java.util.TimeZone
import java.util.concurrent.TimeUnit


fun calculateDateDifferenceInMillis(date1InMillis: Long, date2InMillis: Long): Long {
    return date2InMillis - date1InMillis
}

fun calculateDateDifferenceInDays(date1InMillis: Long, date2InMillis: Long): Long {
    val differenceInMillis = calculateDateDifferenceInMillis(date1InMillis, date2InMillis)
    return TimeUnit.MILLISECONDS.toDays(differenceInMillis)
}

fun calculateDateDifferenceInWeek(daysDifference: Long): String {
    return when {
        daysDifference > 365 -> {
            "12 month ago"
        }

        daysDifference > 28 -> {
            "1 month ago"
        }

        daysDifference > 7 -> {
            "1 week ago"
        }

        daysDifference > 5 -> {
            "5 days ago"
        }

        daysDifference > 1 -> {
            "Yesterday"
        }

        daysDifference == 0L -> {
            "Today"
        }
        else -> "Invalid date"
    }
}

fun main() {


    val date1InMillis: Long = 1519983341000 /* your first date in milliseconds */
    val date2InMillis: Long = System.currentTimeMillis() /* your second date in milliseconds */

    val daysDifference = calculateDateDifferenceInDays(date1InMillis, date2InMillis)
//    println("Difference in days: $daysDifference")

    println("Difference ${calculateDateDifferenceInWeek(daysDifference)}")

//    sortWeekdays()


}
// color // Theme Support/ dark-mode etc
// loading /// naming
// swipe refresh
// View group >> compose optimization >> proper layering of Row/Colum/Box (recomposition check)
// Preview make
// MVVM with test coverage
// repository with test coverage
// properly function naming


fun sortWeekdays(
    daysString: String = "Wednesday,Friday,Sunday,Monday,Tuesday"
) {
    // Define a map to map weekdays to numerical values
    val weekdayMapping = mapOf(
        "Sunday" to 1,
        "Monday" to 2,
        "Tuesday" to 3,
        "Wednesday" to 4,
        "Thursday" to 5,
        "Friday" to 6,
        "Saturday" to 7
    )

    // Split the inputString by a comma (",") delimiter
    val weekdays = daysString.split(",")

    // Sort the weekdays based on their numerical values
    val sortedWeekdays = weekdays.sortedBy { weekdayMapping[it] }

    // Print the sorted weekdays
    println("Sorted weekdays:")
    sortedWeekdays.forEach { println(it) }
}


fun testOld() {
    println("hello")


    val dataList = listOf(
        Person(1, "Alice"),
        Person(2, "Bob"),
        Person(3, "Charlie")
    )

    val personMap = dataList.associateBy { it.id }

    println(personMap)


    val words = listOf(
        listOf("Hello", "World"),
        listOf("AAA", "BBBB"),
        listOf("XXX", "ZZZZ")
    )
    val letters = words.flatMap { it.toList() }

    println(letters)
}

data class Person(val id: Int, val name: String)

// write a function to print odd number from 0 to 10
fun printLog() {

}


//fun main() {
//
//    val myList = TestLinkList<String>()
//
////    myList.push(99)
//
//    listOf("a", "b", "c", "d").forEach {
//        myList.push(it)
//    }
//
//    for (i in 1..myList.size) {
//        println(myList.pop())
//    }
//}


class TestLinkList<T> {

    private var tail: TestLinkList<T>? = null
    private var prevNode: TestLinkList<T>? = null
    private var myValue: T? = null

    var size = 0
        private set

    fun push(point: T) {
        val tempTail = tail
        tail = TestLinkList<T>().apply {
            myValue = point
            prevNode = tempTail
        }
        size++
    }

    fun pop(): T {
        if (tail == null) {
            throw Exception("no left item")
        } else {
            size--
            val data = tail?.myValue
            tail = tail?.prevNode
            return data!!
        }
    }
}


/*
*
Explanation
MagicDictionary magicDictionary = new MagicDictionary();
magicDictionary.buildDict(["hello", "leetcode"]);
*
*
magicDictionary.search("hello"); // return False
magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
magicDictionary.search("hell"); // return False
magicDictionary.search("leetcoded"); // return False
 */
fun magicDictionary() {

    val matchingWord = "hello"

    val dataList = listOf("hhllo", "hell")

    dataList.forEach { item ->
        if (matchingWord == item) {
            // best case
            // false
        } else if (matchingWord.length != item.length) {
            // best case
            // false
        } else {
            //
            var counter = 0
            val matchingArray = matchingWord.toCharArray() // "h e l l o"
            val itemArray = item.toCharArray() // h h l l o
            matchingArray.forEachIndexed { index, matchingChar ->
                if (itemArray[index] == matchingChar) { // 4
                    // h
                    counter++
                }
            }

            if (counter == itemArray.size - 1) {
                // return true
            } else {
                // return false
            }
        }


    }
}


fun testLongestCommonPrefix() {
    val strs = arrayOf("flower", "flow", "flight")
//    val strs = arrayOf("dog","racecar","car")
    val output = longestCommonPrefix(strs)
    println("OUTPUT==$output")
}

fun longestCommonPrefix(strs: Array<String>): String {
    return if (strs.isEmpty()) {
        ""
    } else {
        var myPrefix = strs[0]
        strs.forEach { item ->
            myPrefix = myPrefix.findMatch(item) // listOf("flower", "flow", "flight")
        }
        myPrefix
    }
}

private fun String.findMatch(item: String): String {
    return if (this == item) {
        this
    } else if (length > item.length) {
        substring(0, length - 1).findMatch(item = item)
    } else if (length == item.length) {
        substring(0, length - 1).findMatch(item = item.substring(0, item.length - 1))
    } else { // swapping the operational attribute
        item.findMatch(item = this)
    }
}