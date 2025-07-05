package com.kosiso.advancenoteapp_cleanarchitecture.feature_note.presentation.notes

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kosiso.advancenoteapp_cleanarchitecture.common.Constants
import com.kosiso.advancenoteapp_cleanarchitecture.common.Constants.ORDER_SECTION
import com.kosiso.advancenoteapp_cleanarchitecture.common.Constants.SORT
import com.kosiso.advancenoteapp_cleanarchitecture.di.AppModule
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.presentation.MainActivity
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.presentation.util.Screen
import com.kosiso.advancenoteapp_cleanarchitecture.ui.theme.AdvanceNoteAppCleanArchitectureTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
// tells hilt not to use AppModule. Hilt would search and eventually use the TestAppModule
@UninstallModules(AppModule::class)
class NotesScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalAnimationApi::class)
    @Before
    fun setUp(){
        hiltRule.inject()
    }

    // testing the sorting composable
    // whether the drop down that shows the radio btns shows aor now
    @Test
    fun clickToggleOrderSection_isVisible() {
        composeRule.onNodeWithTag(ORDER_SECTION).assertDoesNotExist()
        composeRule.onNodeWithContentDescription(SORT).performClick()
        composeRule.onNodeWithTag(ORDER_SECTION).assertIsDisplayed()
    }
}