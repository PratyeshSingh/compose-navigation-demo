package com.example.compose_navigation_demo

import java.text.DateFormat
import java.util.Date
import java.util.TimeZone
import java.util.concurrent.TimeUnit


/*
* Create a fun number of array and return the possible permutations of it
*
* Input: nums = [1,2,3]
*
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
*
*
*
* Input: nums = [0,1]

Output: [[0,1],[1,0]]
*
* */
fun main() {
    val inputArray = intArrayOf(1, 2, 3)
    val permutations = ArrayList<List<Int>>()

    val n = inputArray.size

    val indices = IntArray(inputArray.size)
    // Initialize indices
    for (i in 0 until n) {
        indices[i] = 0
    }

    permutations.add(inputArray.toList())
    var i = 0

    while (i < n) {
        if (indices[i] < i) {
            swap(inputArray, i, if (i % 2 == 0) 0 else indices[i])
            permutations.add(inputArray.toList())
            indices[i]++
            i = 0
        } else {
            indices[i] = 0
            i++
        }
    }

    for (perm in permutations) {
        println(perm.joinToString(", "))
    }
}

fun swap(input: IntArray, i: Int, j: Int) {
    val temp = input[i]
    input[i] = input[j]
    input[j] = temp
}

