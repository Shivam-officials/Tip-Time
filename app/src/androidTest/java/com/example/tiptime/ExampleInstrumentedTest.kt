package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Class containg all the test for testing tip_options
 */
@RunWith(AndroidJUnit4::class)
class CalculatorTest {

    @get:Rule()
    var activity = ActivityScenarioRule(MainActivity::class.java)


    /**
     *testing the defualt tip option by giving '50.00' as input and expeciting '10.00' as result while round up switch is off
     */
    @Test
    fun calculate_default_tip() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("50.00"))

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result)).check(matches(withText(containsString("10.00"))))


    }

    /**
     *testing the defualt tip option by giving '55.00' as input and expeciting '10.00' as result while round up switch is on
     */
    @Test
    fun calculate_18_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("55"))

        onView(withId(R.id.option_eighteen_percent)).perform(click())

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.round_up_switch)).perform(click())

        onView(withId(R.id.tip_result)).check(matches(withText(containsString("10.00"))))

    }

}