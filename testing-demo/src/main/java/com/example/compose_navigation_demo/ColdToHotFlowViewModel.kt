package com.example.compose_navigation_demo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.yield

class ColdToHotFlowViewModel(
) : ViewModel() {
    internal val newsState:Flow<List<String>> = NewsRemoteDataSource(viewModelScope).latestNews

    fun listOFSuper(){
        val uploader = arrayListOf("aaaa","bbbb","ccc","dddd","eeeeee")
        viewModelScope.launch {
            supervisorScope {
                val jobResultList = mutableListOf<Deferred<String>>()
//                val jobResultList = mutableListOf<Job>()
                uploader.onEach { url ->
//                    jobResultList.add(
//                        launch {
//                            uploadImage(url)
//                        }
//                    )

                    jobResultList.add(
                        async {
                            try {
                                uploadImage(url)
                            } catch (t: Throwable) {
                                t.stackTraceToString()
                            }
                        }
                    )

                }

                jobResultList.awaitAll().let {
                    Log.d("ColdToHotFlowViewModel", it.toString())
                }
            }
        }
    }

    suspend fun uploadImage(url: String): String {

        if(url == "bbbb"){
            throw Exception("Crahs test")
        }

        delay(1000)
        Log.d("ColdToHotFlowViewModel",url)
        return "Uploaded_${System.currentTimeMillis()}_$url"
    }
}


class NewsRemoteDataSource(
    externalScope: CoroutineScope
) {
//    val latestNews: Flow<List<String>> = flow {
//        emit(listOf("Hello", "", "check", "abcd"))
//        delay(2000L)
//        emit(listOf("ABCd", "", "check", "abcd"))
//        delay(2000L)
//        emit(listOf("NCMKD", "", "check", "abcd"))
//    }.shareIn(
//        externalScope,
//        replay = 1,
//        started = SharingStarted.Lazily
//    )


    val latestNews1: Flow<List<String>> = flow {
        delay(5000L)
        emit(listOf("Hello", "first", "check", "abcd"))
        delay(5000L)
        emit(listOf("ABCd", "second", "like", "1234"))
        delay(5000L)
        emit(listOf("NCMKD", "third", "xyz"))
    }

    val latestNews = MutableStateFlow(emptyList<String>())
    init {
        externalScope.launch {
            latestNews.stateIn(
                externalScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = null
            )

            testme()
        }
    }

    suspend fun testBuffer() {
        latestNews.takeIf {
            it.value.size >= 100
        }?.let {
            val myList = it.value.toImmutableList()
            it.value = emptyList()
        }
    }

    suspend fun testme() {
        with(latestNews) {
            delay(5000L)
            this.value = listOf("Hello", "first", "check", "abcd")
            delay(5000L)
            this.value = listOf("ABCd", "second", "like", "1234")
            delay(5000L)
            this.value = listOf("NCMKD", "third", "xyz")
        }
    }
}