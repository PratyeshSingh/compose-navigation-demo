package com.example.compose_navigation_demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class FlowOperatorViewModel : ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue>0){
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        collectFlow()
    }

    fun check() {

    }

    private fun collectFlow() {
        viewModelScope.launch {
            SupervisorJob()
            val temp = supervisorScope {

            }

            // case :: Collect all value from begin for each it's subscribers

//            countDownFlow.collect { time ->
//                delay(1500L)
//                println("The Current value is $time")
//            }


            // case :: Collect latest value and skip/drop the older value

//            countDownFlow.collectLatest { time ->
//                delay(1500L)
//                println("The Current value is $time")
//            }



            // Print the SUM of fibonacci series (sum of current with all older value >0)
//            val accumulatedValue = countDownFlow.reduce { accumulator, value ->
//                accumulator + value
//            }
//            println("The Current value is $accumulatedValue")


            // Print INITIAL digit with SUM of fibonacci series (sum of current with all older value >0)
            val accumulatedValue = countDownFlow.fold(initial = 100) { accumulator, value ->
                accumulator + value
            }
            println("The Current value is $accumulatedValue")


            // case :: COMBINE trigger when any of the flow emit then `combine` get execute
//            val temp = flow<Int> { emit(100) }
//            countDownFlow.combine(temp) { first, sec ->
//                first + sec
//            }


            // case :: MERGE trigger any flow emit then `merge` execute
//            val temp = flow<Int> { emit(100) }
//            var outputString = StringBuilder()
//            merge(countDownFlow, temp).let {
//                outputString.append(it).append("\n")
//            }
//            println("The outputString value is $outputString")


            // case :: ZIP trigger when-ever both of flow emit then only `ZIP` executed
//            val temp = flow<Int> { emit(100) }
//            countDownFlow.zip(temp) { first, sec ->
//                first + sec
//            }

        }
    }
}