

package com.example.compose_navigation_demo

import com.example.home.model.Destination
import com.example.home.ui.DrawerContent
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import com.example.home.R

class DrawerContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Account_Header_Displayed() {
        composeTestRule.setContent {
            Column {
                com.example.home.ui.DrawerContent(
                    onNavigate = {},
                    onLogout = {},
                    onClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.label_name)
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.label_email)
        ).assertIsDisplayed()
    }

    //<editor-fold desc="Upgrade">
    @Test
    fun Upgrade_Displayed() {
        composeTestRule.setContent {
            Column {
                com.example.home.ui.DrawerContent(
                    onNavigate = {},
                    onLogout = {},
                    onClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText(
            com.example.home.model.Destination.Upgrade.title
        ).assertIsDisplayed()
    }

    @Test
    fun Upgrade_Navigation_Callback_Triggered() {
        val onNavigate: (destination: com.example.home.model.Destination) -> Unit = mock()
        composeTestRule.setContent {
            Column {
                com.example.home.ui.DrawerContent(
                    onNavigate = onNavigate,
                    onLogout = {},
                    onClick = {}
                )
            }
        }
        composeTestRule.onNodeWithText(
            com.example.home.model.Destination.Upgrade.title
        ).performClick()

        verify(onNavigate).invoke(com.example.home.model.Destination.Upgrade)
    }
    //</editor-fold>

    //<editor-fold desc="Settings">
    @Test
    fun Settings_Displayed() {
        composeTestRule.setContent {
            Column {
                com.example.home.ui.DrawerContent(
                    onNavigate = {},
                    onLogout = {},
                    onClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText(
            com.example.home.model.Destination.Settings.title
        ).assertIsDisplayed()
    }

    @Test
    fun Settings_Navigation_Callback_Triggered() {
        val onNavigate: (destination: com.example.home.model.Destination) -> Unit = mock()
        composeTestRule.setContent {
            Column {
                com.example.home.ui.DrawerContent(
                    onNavigate = onNavigate,
                    onLogout = {},
                    onClick = {}
                )
            }
        }
        composeTestRule.onNodeWithText(
            com.example.home.model.Destination.Settings.title
        ).performClick()

        verify(onNavigate).invoke(com.example.home.model.Destination.Settings)
    }
    //</editor-fold>

    //<editor-fold desc="Logout">
    @Test
    fun Logout_Displayed() {
        composeTestRule.setContent {
            Column {
                com.example.home.ui.DrawerContent(
                    onNavigate = {},
                    onLogout = {},
                    onClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.log_out)
        ).assertIsDisplayed()
    }

    @Test
    fun Logout_Navigation_Callback_Triggered() {
        val onLogout: () -> Unit = mock()
        composeTestRule.setContent {
            Column {
                com.example.home.ui.DrawerContent(
                    onNavigate = {},
                    onLogout = onLogout,
                    onClick = {}
                )
            }
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.log_out)
        ).performClick()

        verify(onLogout).invoke()
    }
    //</editor-fold>
}