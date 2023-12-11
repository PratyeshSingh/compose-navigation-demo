package com.example.compose_navigation_demo

import android.net.LocalSocketAddress.Namespace
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.compose_navigation_demo.ui.theme.ComposenavigationdemoTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DemoActivity : ComponentActivity(),
    NamespacedComponent by ProvideNamespace(LoggingNamespace.DemoActivity)
{

    private val mMyViewModel by viewModels<MyViewModel>()
//    private val updateItem = mutableStateOf(false)


    val tempViewModel : FlowOperatorViewModel by viewModels()
    val coldToHotFlowViewModel : ColdToHotFlowViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contentList = mutableListOf<String>()
        for (i in 1..100) {
            contentList.add("$i")
        }
        setContent {
            ComposenavigationdemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val updateItem = mMyViewModel.localState.collectAsState()
                    HomeScreen(
                        listener = ::proceed,
//                        contentList = contentList.toImmutableList(),
                        contentList = coldToHotFlowViewModel.newsState.collectAsState(initial = contentList).value.toImmutableList(),
                        updateItem = updateItem.value
                    )
                }
            }
        }

//        lifecycleScope.launch {
//            mMyViewModel.localState.collectLatest {
//                updateItem.value = it
//            }
//        }

//        tempViewModel.check()
//        collectLatestLivecycleFlow(tempViewModel.countDownFlow) {
//            Log.d("DemoActivity:: onCreate", "$it")
//        }

//        lifecycleScope.launch {
//            coldToHotFlowViewModel.newsState.collect {
//                Log.d("DemoActivity:: newsState", "$it")
//            }
//        }

        coldToHotFlowViewModel.listOFSuper()
    }

    private fun proceed(action: HomeAction) {
        when (action) {
            HomeAction.LoadMore -> {
                Log.d("TEST", "Load more")
                mMyViewModel.changeState(2000)
            }

            is HomeAction.AddToCart -> {
                Log.d("TEST", "Add to cart")
            }
        }
    }
}


@Composable
internal fun HomeScreen(
    listener: HomeClickHandler,
    contentList: ImmutableList<String>,
    updateItem : Boolean
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.Top
        ),
        horizontalAlignment = Alignment.Start,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        itemsIndexed(
            items = contentList,
            key = { _, item -> item }
        ) { index, item ->
            Greeting("Index=$index ::Item$item", listener = listener)
        }
        item {
            Text(
                modifier = Modifier.clickable(onClick = { listener(HomeAction.LoadMore) }),
                text = "Load More"
            )
            println("UpdateItem:: $updateItem")
        }
    }
}

@Composable
fun Greeting(name: String, listener: HomeClickHandler) {
    Text(
        modifier = Modifier.clickable(onClick = { listener(HomeAction.AddToCart(name)) }),
        text = "Hello $name!"
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposenavigationdemoTheme {
        Greeting(
            name = "Android",
            listener = {}
        )
    }
}


fun <T> DemoActivity.collectLatestLivecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED){
            flow.collectLatest(collect)
        }
    }
}