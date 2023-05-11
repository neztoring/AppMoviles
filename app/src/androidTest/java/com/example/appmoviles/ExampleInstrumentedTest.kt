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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.appmoviles.ui.adapters.AlbumsAdapter
import java.util.concurrent.TimeUnit


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

    @Test
    fun navigateAlbumDetail() {

        val userBtn: ViewInteraction =
            onView(allOf(withId(R.id.button_usuario), withText("Soy un Usuario"), isDisplayed()))
        userBtn.perform(click())

        onView(withId(R.id.drawerLayoutUser))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.navViewUser))
            .perform(NavigationViewActions.navigateTo(R.id.albums));

        TimeUnit.SECONDS.sleep(2L)
        onView(withId(R.id.albumsRv))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()))
    }

    @Test
    fun navigateCollectorMenu() {

        val userBtn: ViewInteraction =
            onView(allOf(withId(R.id.button_coleccionista), withText(R.string.label_button_coleccionista), isDisplayed()))
        userBtn.perform(click())

        onView(withId(R.id.drawerLayoutCollector))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open()); // Open Drawer

        onView(allOf(withId(R.id.fav_performers), withText(R.string.fav_performers), isDisplayed()))
        onView(allOf(withId(R.id.albums_handler), withText(R.string.albums_handler), isDisplayed()))
        onView(allOf(withId(R.id.create_album), withText(R.string.create_album), isDisplayed()))
        onView(allOf(withId(R.id.associate_track), withText(R.string.associate_track), isDisplayed()))
        onView(allOf(withId(R.id.profile_change), withText(R.string.profile_change), isDisplayed()))

    }

    @Test
    fun changeProfileCollectorMenu() {

        val userBtn: ViewInteraction =
            onView(allOf(withId(R.id.button_coleccionista), withText(R.string.label_button_coleccionista), isDisplayed()))
        userBtn.perform(click())

        onView(withId(R.id.drawerLayoutCollector))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.profile_change)).perform(click());

        onView(allOf(withId(R.id.button_coleccionista), withText(R.string.label_button_coleccionista), isDisplayed()))

    }
}