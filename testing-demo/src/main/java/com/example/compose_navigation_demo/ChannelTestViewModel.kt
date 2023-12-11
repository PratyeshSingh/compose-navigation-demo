package com.example.compose_navigation_demo

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ChannelTestViewModel : ViewModel() {

    private val channel = Channel<Int>()
    private var channel1 : ReceiveChannel<Int> = Channel()

    init {
        producer()
        consumer1()
        consumer2()
    }

    fun producer() {

        CoroutineScope(Dispatchers.Main).launch {
//            for (i in 1..10) {
//                delay(200)
//                channel.send(i)
//            }
//            channel.close() // manual close of channel


            channel1 = produce /*(capacity = CONFLATED)*/ {
                // CONFLATED :: drop the older value and emit only the last /latest value
                // UNLIMITED :: unlimited buffer size to emit the result , So if receiver is on suspend then it may cause the OOM error.
                for (i in 1..10) {
                    delay(200)
                    send(i)
                }
            }
        }
    }

    fun consumer1() {
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("ChannelTestViewModel:: consumer-1", "${channel.isClosedForReceive}") // O/P will false as it is open
//            for (i in 1..10) {
            channel.consumeEach { // automatic receive all the emitted value till last instead for llop
                Log.d("ChannelTestViewModel:: consumer-1", channel.receive().toString())
            }

            Log.d("ChannelTestViewModel:: consumer-1", "${channel.isClosedForReceive}") // O/P will true as it is closed
        }
    }
    fun consumer2() {
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("ChannelTestViewModel:: consumer-2", "${channel.isClosedForReceive}")
//            for (i in 1..10) {
            channel.consumeEach {
                delay(500)
                Log.d("ChannelTestViewModel:: consumer-2", channel.receive().toString())
            }
            Log.d("ChannelTestViewModel:: consumer-2", "${channel.isClosedForReceive}")
        }
    }

}