package com.example.chronos

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.chronos.ui.pages.ForgotPage
import com.example.chronos.ui.pages.LoginPage
import com.example.chronos.ui.pages.RegisterPage
import com.example.chronos.ui.pagesinner.CreatePage
import com.example.chronos.ui.pagesinner.EditTaskPage
import com.example.chronos.ui.pagesinner.UpdateProfilePage
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChronosInstrumentedTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  // Context of the app under test.
  @Test
  fun useAppContext() {
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    assertEquals("com.example.chronos", appContext.packageName)
  }

  // Test on register page
  // Saving new chron is ignored
  @Test
  fun testOnRegisterPage() {
    composeTestRule.setContent {
      RegisterPage(rememberNavController())
    }

    // Input fields
    composeTestRule.onNodeWithText("USERNAME")
      .performTextInput("Test User")
    composeTestRule.onNodeWithText("PASSWORD")
      .performTextInput("TestUser")
    composeTestRule.onNodeWithText("CONFIRM PASSWORD")
      .performTextInput("TestUser")
    composeTestRule.onNodeWithText("EMAIL ADDRESS")
      .performTextInput("testuser@gmail.com")

    // Check if the register button appears
    composeTestRule.onNodeWithText("REGISTER")
      .assertIsDisplayed()
  }

  // Test on login page
  // Log in is ignored
  @Test
  fun testOnLoginPage() {
    composeTestRule.setContent {
      LoginPage(rememberNavController())
    }

    // Input fields
    composeTestRule.onNodeWithText("USERNAME")
      .performTextInput("Test User")
    composeTestRule.onNodeWithText("PASSWORD")
      .performTextInput("TestUser")

    // Check if the login button appears
    composeTestRule.onNodeWithText("LOGIN")
      .assertIsDisplayed()
  }

  // Test on forgot page
  // Reset data is ignored
  fun testOnForgotPage() {
    composeTestRule.setContent {
      ForgotPage(rememberNavController())
    }

    // Input fields
    composeTestRule.onNodeWithText("EMAIL ADDRESS")
      .performTextInput("testuser@gmail.com")
    composeTestRule.onNodeWithText("NEW PASSWORD")
      .performTextInput("TestUser")
    composeTestRule.onNodeWithText("CONFIRM NEW PASSWORD")
      .performTextInput("TestUser")

    // Check if the reset button appears
    composeTestRule.onNodeWithText("RESET")
      .assertIsDisplayed()
  }

  // Test on create page
  // Saving new epheron is ignored
  @Test
  fun testOnCreatePage() {
    composeTestRule.setContent {
      CreatePage()
    }

    // Input fields
    composeTestRule.onNodeWithText("TITLE")
      .performTextInput("This is a title")
    composeTestRule.onNodeWithText("DESCRIPTION")
      .performTextInput("This is a description")

    // Date and time selection
    // Inputs are not tested as the node value are unknown
    // Cancel buttons are pressed as default
    composeTestRule.onNodeWithText("START DATE AND TIME")
      .performClick()
    composeTestRule.onNodeWithText("Cancel")
      .performClick()
    composeTestRule.onNodeWithText("END DATE AND TIME")
      .performClick()
    composeTestRule.onNodeWithText("Cancel")
      .performClick()

    // Priority level selection
    composeTestRule.onNodeWithText("PRIORITY LEVEL")
      .performClick()
    composeTestRule.onNodeWithText("LOW")
      .performClick()
    composeTestRule.onNodeWithText("OK")
      .performClick()
  }

  // Test on edit page
  // Saving existing epheron data is ignored
  @Test
  fun testOnEditPage() {
    composeTestRule.setContent {
      EditTaskPage(rememberNavController())
    }

    // Input fields
    composeTestRule.onNodeWithText("UPDATE TITLE")
      .performTextInput("This is a title")
    composeTestRule.onNodeWithText("UPDATE DESCRIPTION")
      .performTextInput("This is a description")

    // Date and time selection
    // Inputs are not tested as the node value are unknown
    // Cancel buttons are pressed as default
    composeTestRule.onNodeWithText("START DATE AND TIME")
      .performClick()
    composeTestRule.onNodeWithText("Cancel")
      .performClick()
    composeTestRule.onNodeWithText("END DATE AND TIME")
      .performClick()
    composeTestRule.onNodeWithText("Cancel")
      .performClick()

    // Priority level selection
    composeTestRule.onNodeWithText("PRIORITY LEVEL")
      .performClick()
    composeTestRule.onNodeWithText("LOW")
      .performClick()
    composeTestRule.onNodeWithText("OK")
      .performClick()

    // Check whether the action buttons are displayed
    composeTestRule.onNodeWithContentDescription("Delete")
      .assertIsDisplayed()
    composeTestRule.onNodeWithContentDescription("Save")
      .assertIsDisplayed()
    composeTestRule.onNodeWithContentDescription("Back")
      .assertIsDisplayed()
  }

  // Test on update profile page
  // Saving existing chron data is ignored
  @Test
  fun testOnUpdatePage() {
    composeTestRule.setContent {
      UpdateProfilePage(rememberNavController())
    }

    // Input fields
    composeTestRule.onNodeWithText("UPDATE USERNAME")
      .performTextInput("Update Test User")
    composeTestRule.onNodeWithText("UPDATE EMAIL ADDRESS")
      .performTextInput("updatetestuser@gmail.com")
    composeTestRule.onNodeWithText("UPDATE PASSWORD")
      .performTextInput("UpdateTestUser")
    composeTestRule.onNodeWithText("CONFIRM UPDATE PASSWORD")
      .performTextInput("UpdateTestUser")

    // Check whether the actions buttons are displayed
    composeTestRule.onNodeWithContentDescription("Back")
      .assertIsDisplayed()
    composeTestRule.onNodeWithContentDescription("Save Profile")
      .assertIsEnabled()
  }
}