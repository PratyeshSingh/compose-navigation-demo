

package academy.compose.video

import academy.compose.video.model.PlayerStatus
import academy.compose.video.Tags.TAG_CONTROL_BUTTON
import academy.compose.video.ui.Controls
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalAnimationApi
@RunWith(AndroidJUnit4::class)
class ControlsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Player_Control_Not_Enabled_When_Video_Not_Ready() {
        composeTestRule.setContent {
            Controls(
                playerStatus = PlayerStatus.LOADING,
                togglePlayingState = { }
            )
        }
        composeTestRule.onNodeWithTag(
            TAG_CONTROL_BUTTON
        ).assertIsNotEnabled()
    }

    @Test
    fun Player_Control_Enabled_When_Video_Ready() {
        composeTestRule.setContent {
            Controls(
                playerStatus = PlayerStatus.IDLE,
                togglePlayingState = { }
            )
        }
        composeTestRule.onNodeWithTag(
            TAG_CONTROL_BUTTON
        ).assertIsEnabled()
    }

    @Test
    fun Pause_Control_Displayed_When_Playing() {
        composeTestRule.setContent {
            Controls(
                playerStatus = PlayerStatus.PLAYING,
                togglePlayingState = { }
            )
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.cd_pause)
        ).assertIsDisplayed()
    }

    @Test
    fun Play_Control_Displayed_When_Paused() {
        composeTestRule.setContent {
            Controls(
                playerStatus = PlayerStatus.PAUSED,
                togglePlayingState = { }
            )
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.cd_play)
        ).assertIsDisplayed()
    }

    @Test
    fun Toggled_Play_State_Triggered() {
        val togglePlayingState: () -> Unit = mock()
        composeTestRule.setContent {
            Controls(
                playerStatus = PlayerStatus.PAUSED,
                togglePlayingState = togglePlayingState
            )
        }
        composeTestRule.onNodeWithTag(
            TAG_CONTROL_BUTTON
        ).performClick()

        verify(togglePlayingState).invoke()
    }
}