package com.example.compose_navigation_demo

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SharedAndStateFlowViewModelTest {
    private lateinit var viewModel: SharedAndStateFlowViewModel
    private lateinit var testDispatchers: TestDispatchers

    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        viewModel = SharedAndStateFlowViewModel(testDispatchers)
    }

    @Test
    fun `countDownFlow, properly counts down from 5 to 0`() = runBlocking {

        testDispatchers.testDispatcher.runTest {
            viewModel.countDownFlow.test {
                for(i in 5 downTo 0) {
                    advanceTimeBy(1000L)
//                    advanceUntilIdle()
                    val emission = awaitItem()
                    assertThat(emission).isEqualTo(i)
                }
                cancelAndConsumeRemainingEvents()
            }

        }
    }

    @Test
    fun `squareNumber, number properly squared`() = runBlocking {
        val job = launch {
            viewModel.sharedFlow.test {
                val emission = awaitItem()
                assertThat(emission).isEqualTo(9)
                cancelAndConsumeRemainingEvents()
            }
        }
        viewModel.squareNumber(3)
        job.join()
        job.cancel()
    }
}