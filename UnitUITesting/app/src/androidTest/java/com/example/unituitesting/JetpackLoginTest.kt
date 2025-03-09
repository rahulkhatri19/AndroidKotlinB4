package com.example.unituitesting

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.unituitesting.Utility.EMAIL
import com.example.unituitesting.Utility.PASSWORD
import com.example.unituitesting.Utility.LOGIN
import com.example.unituitesting.uiTesting.JetpackLogin
import org.junit.Rule
import org.junit.Test

class JetpackLoginTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun checkTextFiled(){
        composeRule.setContent {
            JetpackLogin()
        }
        composeRule.onNodeWithText(EMAIL).performTextInput("Geeksforgeek@gmail.com")
        composeRule.onNodeWithText(PASSWORD).performTextInput("12345678")
        composeRule.onNodeWithText("button").performClick()

    }
}










