package com.example.chronos

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.chronos.ui.pagesinner.CreatePage
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChronosInstrumentedTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun useAppContext() {
    // Context of the app under test.
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    assertEquals("com.example.chronos", appContext.packageName)
  }

  @Test
  fun testUIContext() {
    composeTestRule.setContent {
      CreatePage()
    }
  }
}