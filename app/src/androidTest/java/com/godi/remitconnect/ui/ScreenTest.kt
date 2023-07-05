package com.godi.remitconnect.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.godi.remitconnect.MainScreen
import com.godi.remitconnect.ui.theme.RemitConnectTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun myTest() {
        // Start the app
        composeTestRule.setContent {
            RemitConnectTheme {
                MainScreen()
            }
        }

        composeTestRule.onNodeWithText("Cards").performClick()

        composeTestRule.onNodeWithText("Coming soon!").assertIsDisplayed()
    }
}