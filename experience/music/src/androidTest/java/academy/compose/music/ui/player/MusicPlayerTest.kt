package academy.compose.music.ui.player

import academy.compose.music.Tags.TAG_PLAYER
import academy.compose.music.Tags.TAG_PLAYER_BAR
import academy.compose.practical.music_catalog.TestDataFactory
import academy.compose.music.model.MusicDashboardState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToString
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalMaterial3Api::class)
class MusicPlayerTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @ExperimentalAnimationApi
    @Test
    fun Collapsed_Player_Bar_Displayed() {
        composeTestRule.setContent {
            val scaffoldState = rememberBottomSheetScaffoldState(
                bottomSheetState = rememberStandardBottomSheetState(
                    initialValue = SheetValue.PartiallyExpanded
                )
            )
            MusicPlayer(
                scaffoldState = scaffoldState,
                state = MusicDashboardState(
                    nowPlaying = TestDataFactory.makeNowPlaying()
                ),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TAG_PLAYER_BAR)
            .assertIsDisplayed()
    }

    @ExperimentalAnimationApi
    @Test
    fun Player_Never_Revealed_While_Player_Bar_Collapsed() {
        composeTestRule.setContent {
            val scaffoldState = rememberBottomSheetScaffoldState(
                bottomSheetState = rememberStandardBottomSheetState(
                    initialValue = SheetValue.Expanded
                )
            )
            MusicPlayer(
                scaffoldState = scaffoldState,
                state = MusicDashboardState(),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TAG_PLAYER)
            .assertDoesNotExist()
    }

    @ExperimentalAnimationApi
    @Test
    fun Revealed_Player_Bar_Displayed() {
        composeTestRule.setContent {
            val scaffoldState = rememberBottomSheetScaffoldState(
                bottomSheetState = rememberStandardBottomSheetState(
                    initialValue = SheetValue.Expanded
                )
            )
            MusicPlayer(
                scaffoldState = scaffoldState,
                state = MusicDashboardState(
                    nowPlaying = TestDataFactory.makeNowPlaying()
                ),
                handleEvent = {}
            )
        }
        print(composeTestRule.onRoot().printToString())
        composeTestRule.onNodeWithTag(TAG_PLAYER)
            .assertIsDisplayed()
    }

    @ExperimentalAnimationApi
    @Test
    fun Player_Bar_Never_Displayed_When_Player_Revealed() {
        composeTestRule.setContent {
            val scaffoldState = rememberBottomSheetScaffoldState(
                bottomSheetState = rememberStandardBottomSheetState(
                    initialValue = SheetValue.PartiallyExpanded
                )
            )
            MusicPlayer(
                scaffoldState = scaffoldState,
                state = MusicDashboardState(),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TAG_PLAYER_BAR)
            .assertDoesNotExist()
    }
}