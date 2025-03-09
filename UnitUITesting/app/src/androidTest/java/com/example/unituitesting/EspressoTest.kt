package com.example.unituitesting

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.unituitesting.uiTesting.EspresoActivity
import com.geeksforgeek.unituitesting.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EspressoTest {
    @get:Rule
    val activityScenario = activityScenarioRule<EspresoActivity>()

    @Test
    fun changeText_sameActivity(){
        onView(withId(R.id.etEmail)).perform(typeText("geeksforGeek@gmail.com"))
        onView(withId(R.id.etPassword)).perform(typeText("12345678"))
        onView(withId(R.id.btnLogin)).perform(click())
    }
}













