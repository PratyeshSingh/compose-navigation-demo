

package academy.compose.home

import academy.compose.home.Tags.TAG_BOTTOM_NAVIGATION
import academy.compose.home.Tags.TAG_RAIL_CREATE
import academy.compose.home.Tags.TAG_RAIL_NAVIGATION
import academy.compose.home.ui.Home
import android.content.res.Configuration
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class HomeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    //<editor-fold desc="Navigation Rail">
    @Test
    fun Navigation_Rail_No_Displayed_In_Portrait() {
        composeTestRule.setContent {
            Home(orientation = Configuration.ORIENTATION_PORTRAIT)
        }
        composeTestRule.onNodeWithTag(TAG_RAIL_NAVIGATION)
            .assertDoesNotExist()
    }

    @Test
    fun Navigation_Rail_Not_Displayed_For_Non_Root_Destination() {
        composeTestRule.setContent {
            Home(orientation = Configuration.ORIENTATION_PORTRAIT)
        }

        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.cd_create_item)
        ).performClick()

        composeTestRule.onNodeWithTag(TAG_RAIL_NAVIGATION)
            .assertDoesNotExist()
    }

    @Test
    fun Navigation_Rail_Not_Displayed_For_Non_Root_Destination_In_Landscape() {
        composeTestRule.setContent {
            Home(orientation = Configuration.ORIENTATION_LANDSCAPE)
        }

        composeTestRule.onNodeWithTag(
            TAG_RAIL_CREATE
        ).performClick()

        composeTestRule.onNodeWithTag(TAG_RAIL_NAVIGATION)
            .assertDoesNotExist()
    }

    @Test
    fun Navigation_Rail_Displayed_In_Landscape() {
        composeTestRule.setContent {
            Home(orientation = Configuration.ORIENTATION_LANDSCAPE)
        }
        composeTestRule.onNodeWithTag(TAG_RAIL_NAVIGATION)
            .assertIsDisplayed()
    }
    //</editor-fold>

    //<editor-fold desc="Bottom Navigation">
    @Test
    fun Bottom_Navigation_Displayed_In_Portrait() {
        composeTestRule.setContent {
            Home(orientation = Configuration.ORIENTATION_PORTRAIT)
        }
        composeTestRule.onNodeWithTag(TAG_BOTTOM_NAVIGATION)
            .assertIsDisplayed()
    }

    @Test
    fun Bottom_Navigation_Not_Displayed_In_Landscape() {
        composeTestRule.setContent {
            Home(orientation = Configuration.ORIENTATION_LANDSCAPE)
        }
        composeTestRule.onNodeWithTag(TAG_BOTTOM_NAVIGATION)
            .assertDoesNotExist()
    }

    @Test
    fun Bottom_Navigation_Not_Displayed_For_Non_Root_Destination() {
        composeTestRule.setContent {
            Home(orientation = Configuration.ORIENTATION_LANDSCAPE)
        }

        composeTestRule.onNodeWithTag(
            TAG_RAIL_CREATE
        ).performClick()

        composeTestRule.onNodeWithTag(TAG_BOTTOM_NAVIGATION)
            .assertDoesNotExist()
    }

    @Test
    fun Bottom_Navigtion_Not_Displayed_For_Non_Root_Destination_In_Portrait() {
        composeTestRule.setContent {
            Home(orientation = Configuration.ORIENTATION_PORTRAIT)
        }

        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.cd_create_item)
        ).performClick()

        composeTestRule.onNodeWithTag(TAG_BOTTOM_NAVIGATION)
            .assertDoesNotExist()
    }
    //</editor-fold>
}