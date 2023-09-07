

package academy.compose.video

import academy.compose.video.Tags.TAG_CONTROLS
import academy.compose.video.Tags.TAG_VIDEO_PLAYER
import academy.compose.video.model.VideoState
import academy.compose.video.ui.VideoPlayer
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@ExperimentalAnimationApi
@RunWith(AndroidJUnit4::class)
class VideoPlayerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @ExperimentalComposeUiApi
    @Test
    fun Player_Controls_Displayed_By_Default() {
        composeTestRule.setContent {
            VideoPlayer(
                videoState = VideoState(),
                handleEvent = { }
            )
        }
        composeTestRule.onNodeWithTag(
            TAG_CONTROLS
        ).assertIsDisplayed()
    }

    @ExperimentalComposeUiApi
    @Test
    fun Video_Player_Is_Displayed() {
        composeTestRule.setContent {
            VideoPlayer(
                videoState = VideoState(),
                handleEvent = { }
            )
        }
        composeTestRule.onNodeWithTag(
            TAG_VIDEO_PLAYER
        ).assertIsDisplayed()
    }
}