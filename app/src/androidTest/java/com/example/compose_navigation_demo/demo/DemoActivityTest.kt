package com.example.compose_navigation_demo.demo

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.test.printToString
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.compose_navigation_demo.ui.theme.ComposenavigationdemoTheme
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DemoActivityTest {

    @get:Rule
    val createAndroidComposeRule = createAndroidComposeRule<ComponentActivity>()


    @get:Rule
    val rule = createComposeRule()

    @Test
    fun `Dummy-success-case`() {
        rule.setContent {}

        rule.onNodeWithText("1").performClick()
        rule.onNodeWithText("2").performClick()

        rule.onNode(
            hasText("3")
                    and
                    hasClickAction()
        ).performClick()

        rule.onNodeWithText("1+2+3").assertExists()
    }


    @Test
    fun `Dummy-error-case`() {
        createAndroidComposeRule.setContent {}

        //    val DONE = createAndroidComposeRule.activity.getString(R.string.app_name)

        createAndroidComposeRule.onNode(
            hasText("DONE") // Done Button
                    and
                    hasClickAction()
        )
        createAndroidComposeRule.onNode(
            hasText("Cancel") // Cancel Button
                    and
                    hasClickAction()
        )

        createAndroidComposeRule.onNodeWithText("1+2+3").assertDoesNotExist()

    }

    @Test
    fun `MainActivity-case`() {

        var clickAction: HomeAction ?=null

        val contentList = mutableListOf<String>()
        for (i in 1..100) {
            contentList.add("$i")
        }

        rule.setContent {
            ComposenavigationdemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        listener = {
                            clickAction = it
                        },
                        contentList = contentList.toImmutableList(),
                        updateItem = false
                    )
                }
            }
        }

        println("=====SEMANTICS START=====")
        println(rule.onRoot().printToString())
        println("=====SEMANTICS END=====")

        val firstNodeINList = rule.onNodeWithText("Hello Index=0 ::Item1!")
        firstNodeINList.performClick()
        assert(clickAction == HomeAction.AddToCart("Index=0 ::Item1"))

        firstNodeINList.onParent().performScrollToIndex(contentList.size)
        rule.onNodeWithText("Load More").assertExists()
//        rule.onNode("3").performScrollToNode(hasText("Load More"))

//        rule.onNodeWithText("Load More").performScrollToIndex("Load More")
        rule.onNodeWithText("Load More").performClick()
        assert(clickAction == HomeAction.LoadMore)
    }
}