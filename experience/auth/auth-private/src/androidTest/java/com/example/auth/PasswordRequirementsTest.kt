

package com.example.auth

import academy.compose.authentication.R
import com.example.auth.model.PasswordRequirement
import com.example.auth.ui.PasswordRequirements
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class PasswordRequirementsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Password_Requirements_Displayed_As_Not_Satisfied() {
        val requirements = com.example.auth.model.PasswordRequirement.values().toList()
        val satisfiedRequirement = requirements[(0 until requirements.count()).random()]

        composeTestRule.setContent {
            com.example.auth.ui.PasswordRequirements(
                satisfiedRequirements = listOf(
                    satisfiedRequirement
                )
            )
        }

        com.example.auth.model.PasswordRequirement.values().forEach { passwordRequirement ->
            val requirement = InstrumentationRegistry.getInstrumentation().targetContext
                .getString(passwordRequirement.label)
            val message = if (passwordRequirement == satisfiedRequirement) {
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.password_requirement_satisfied, requirement)
            } else {
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.password_requirement_needed, requirement)
            }

            composeTestRule
                .onNodeWithText(message)
                .assertIsDisplayed()
        }
    }
}