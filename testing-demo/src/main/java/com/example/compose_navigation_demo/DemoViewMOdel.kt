package com.example.compose_navigation_demo

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyViewModel(
    val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {


    var localState = MutableStateFlow(false)


    fun changeState(dealy112: Long) {
        localState.update { false }

        println("Current Thread 1:: "+Thread.currentThread().name)

        viewModelScope.launch(CoroutineName("NameCheck") + dispatcher) {
            println("Current Thread 2:: "+Thread.currentThread().name)
            delay(dealy112)
            localState.update { true }
        }
    }

    suspend fun testString(): String {
        delay(10_000)
        return "TestString"
    }
}