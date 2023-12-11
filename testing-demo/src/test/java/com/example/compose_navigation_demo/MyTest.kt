package com.example.compose_navigation_demo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.toLowerCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Locale

data class MyTest(
    val data: Int,
    var isEnable: Boolean = false
)

fun checkBuilder(a: MyTest, b: MyTest, actionHandler: (MyTest, MyTest) -> MyTest) {
    println(actionHandler(a, b))
}

fun main() {
    
//    var test :String ?=null
//    with(test){
//        println(test)
//    }
//    test?.run {
//
//    }

//    val result = MyTest(2, true) +  MyTest(3).apply { isEnable = false }
//    println( result )
//    checkBuilder(MyTest(2, false), MyTest(3, false)) { a, b ->
//        a + b
//    }


//    var dataee = mutableStateOf(true)
//    dataee.value = false
    var dataee by mutableStateOf(true)
    dataee = false

    println(MyTest(2, true) == MyTest(2, true))


//    val temp = MyTest(2)
//
//    temp.apply {
//        isEnable = true
//    }
//    println(temp)
//    println(temp.isEnable)

//    val squareLambda: (Int) -> Int = {
//        it * it
//    }
//
//    println("Result : " + squareLambda(2))
//
//    callAction(squareLambda)
//

    val value = listOf<Any>("abcd", 1, 3,"xyz", 6).filterSomething<Int>()
    println(value)
    println(listOf<Any>("abcd", 1, 3,"xyz", 6).filterSomething<String>())
}

fun callAction(someAction: (Int) -> Int) {
    println("inside callAction :: ${someAction(2)}")
}

//This can use with JSON parsing (GSON or MOSHI also with custom logic)
inline fun <reified T> List<Any>.filterSomething() : List<T> {
    return this.filter { it is T }.map { it as T }
}

infix operator fun MyTest.plus(b: MyTest): MyTest {
    val mEnable = this.isEnable || b.isEnable
    return MyTest(data = data + b.data).apply {
        isEnable = mEnable
    }
}