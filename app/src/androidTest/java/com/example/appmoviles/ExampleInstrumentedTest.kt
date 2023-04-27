package com.example.appmoviles

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.appmoviles.ui.MainActivity
import org.hamcrest.Matchers.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.view.Gravity
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@LargeTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var mActivityTestRule = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.appmoviles", appContext.packageName)
    }

    @Test
    fun navigateListPerformers() {

        val userBtn: ViewInteraction =
            onView(allOf(withId(R.id.button_usuario), withText("Soy un Usuario"), isDisplayed()))
        userBtn.perform(click())

        onView(withId(R.id.drawerLayoutUser))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.navViewUser))
            .perform(NavigationViewActions.navigateTo(R.id.performer));

    }

    @Test
    fun navigateListAlbums() {

        val userBtn: ViewInteraction =
            onView(allOf(withId(R.id.button_usuario), withText("Soy un Usuario"), isDisplayed()))
        userBtn.perform(click())

        onView(withId(R.id.drawerLayoutUser))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.navViewUser))
            .perform(NavigationViewActions.navigateTo(R.id.albums));

    }
}