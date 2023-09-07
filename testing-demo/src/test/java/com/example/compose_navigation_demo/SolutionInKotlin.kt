package com.example.compose_navigation_demo

fun main() {

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
fun printLog(){

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