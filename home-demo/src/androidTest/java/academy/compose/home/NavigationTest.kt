

package academy.compose.home

import academy.compose.home.model.Destination
import academy.compose.home.ui.Navigation
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Feed_Displayed_By_Default() {
        composeTestRule.setContent {
            Navigation(navController = rememberNavController())
        }
        composeTestRule.onNodeWithTag(Destination.Feed.path)
            .assertIsDisplayed()
    }

    @Test
    fun Contacts_Displayed() {
        assertNavigation(Destination.Contacts)
    }

    @Test
    fun Calendar_Displayed() {
        assertNavigation(Destination.Calendar)
    }

    @Test
    fun Create_Displayed() {
        assertNavigation(Destination.Add)
    }

    @Test
    fun Upgrade_Displayed() {
        assertNavigation(Destination.Upgrade)
    }

    @Test
    fun Settings_Displayed() {
        assertNavigation(Destination.Settings)
    }

    private fun assertNavigation(destination: Destination) {
        composeTestRule.setContent {
            val navController = rememberNavController()
            Navigation(navController = navController)
            navController.navigate(destination.path)
        }
        composeTestRule.onNodeWithTag(destination.path)
            .assertIsDisplayed()
    }
}
