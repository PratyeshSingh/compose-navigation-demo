package com.example.compose_navigation_demo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class MyViewModelTest {

    @get:Rule
    private val coroutineRule = CoroutineTestRule(
        testDispatcher = StandardTestDispatcher()
    )
//
//    @Test
//    fun testStringFast() {
//        val viewModel = MyViewModel(coroutineRule.testDispatcher)
//        runTest {
//            viewModel.testString()
//        }
//    }
//
//    @Test
//    fun testString() {
//        val viewModel = MyViewModel(coroutineRule.testDispatcher)
//        runBlocking {
//            viewModel.testString()
//        }
//    }
//
//    @Test
//    fun `check my app`(){
//
//    }

    @Test
    fun changeStateUpdatesLocalStateAfterDelay() {
        val viewModel = MyViewModel(coroutineRule.testDispatcher)
        coroutineRule.testDispatcher.runTest {
            assert(!viewModel.localState.value)
            println("Current Thread 01:: "+Thread.currentThread().name)
            viewModel.changeState(10_000)

            /*
            * Since scheduler hold/add-in-queue result of the coroutine(testDispatcher's)
            * so to run them we need to call "advanceUntilIdle()" to release the data
            * */
            coroutineRule.testDispatcher.scheduler.advanceUntilIdle()
//            advanceUntilIdle()
            assert(viewModel.localState.value)
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineTestRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {

    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)

    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun TestDispatcher.runTest(block: suspend TestScope.() -> Unit) {
    runTest(context = this, dispatchTimeoutMs = 1_000L, testBody = block)
}