package com.example.appmoviles

import android.graphics.Point
import android.graphics.Rect
import android.os.SystemClock
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.appmoviles.ui.MainActivity
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.*
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
    fun navigatePerformerDetail() {

        val userBtn: ViewInteraction =
            onView(allOf(withId(R.id.button_usuario), withText("Soy un Usuario"), isDisplayed()))
        userBtn.perform(click())

        onView(withId(R.id.drawerLayoutUser))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.navViewUser))
            .perform(NavigationViewActions.navigateTo(R.id.performer));

        TimeUnit.SECONDS.sleep(2L)
        onView(withId(R.id.fragments_rv))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()))
    }

    @Test
    fun navigateCollectorMenu() {

        val userBtn: ViewInteraction =
            onView(
                allOf(
                    withId(R.id.button_coleccionista),
                    withText(R.string.label_button_coleccionista),
                    isDisplayed()
                )
            )

        userBtn.perform(click())

        onView(withId(R.id.drawerLayoutCollector))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open()); // Open Drawer

        onView(allOf(withId(R.id.fav_performers), withText(R.string.fav_performers), isDisplayed()))
        onView(allOf(withId(R.id.albums_handler), withText(R.string.albums_handler), isDisplayed()))
        onView(allOf(withId(R.id.create_album), withText(R.string.create_album), isDisplayed()))
        onView(
            allOf(
                withId(R.id.associate_track),
                withText(R.string.associate_track),
                isDisplayed()
            )
        )
        onView(allOf(withId(R.id.profile_change), withText(R.string.profile_change), isDisplayed()))

    }

    @Test
    fun changeProfileCollectorMenu() {

        val userBtn: ViewInteraction =
            onView(
                allOf(
                    withId(R.id.button_coleccionista),
                    withText(R.string.label_button_coleccionista),
                    isDisplayed()
                )
            )
        userBtn.perform(click())

        onView(withId(R.id.drawerLayoutCollector))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.profile_change)).perform(click());

        onView(
            allOf(
                withId(R.id.button_coleccionista),
                withText(R.string.label_button_coleccionista),
                isDisplayed()
            )
        )

    }

    @Test
    fun addTrackToAlbumFailRequiredField() {

        val userBtn: ViewInteraction =
            onView(
                allOf(
                    withId(R.id.button_coleccionista),
                    withText(R.string.label_button_coleccionista),
                    isDisplayed()
                )
            )
        userBtn.perform(click())

        onView(withId(R.id.drawerLayoutCollector))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.associate_track)).perform(click());
        TimeUnit.SECONDS.sleep(2L)

        onView(withId(R.id.track_name)).perform(typeText("Mar Azul"))

        onView(withId(R.id.autoCompleteTextViewMinutes)).perform(click());
        onData(equalTo("3")).inRoot(RootMatchers.isPlatformPopup()).perform(click());

        onView(withId(R.id.autoCompleteTextViewSeconds)).perform(click());
        onData(equalTo("25")).inRoot(RootMatchers.isPlatformPopup()).perform(click());

        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.button_save_track)).perform(click());
        TimeUnit.SECONDS.sleep(2L)

        onView(
            allOf(
                withId(R.id.til_track_album),
                withText(R.string.form_required_field),
                isDisplayed()
            )
        )


    }

    @Test
    fun addTrackToAlbumOK() {

        val userBtn: ViewInteraction =
            onView(
                allOf(
                    withId(R.id.button_coleccionista),
                    withText(R.string.label_button_coleccionista),
                    isDisplayed()
                )
            )
        userBtn.perform(click())

        onView(withId(R.id.drawerLayoutCollector))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.associate_track)).perform(click());
        TimeUnit.SECONDS.sleep(1L)

        onView(withId(R.id.track_name)).perform(typeText("Cielito Lindo"))

        onView(withId(R.id.autoCompleteTextViewMinutes)).perform(click());
        onData(equalTo("3")).inRoot(RootMatchers.isPlatformPopup()).perform(click());

        onView(withId(R.id.autoCompleteTextViewSeconds)).perform(click());
        onData(equalTo("25")).inRoot(RootMatchers.isPlatformPopup()).perform(click());


        onView(withId(R.id.autoCompleteTextViewAlbum)).perform(click());
        onData(equalTo("Album Nuevo")).inRoot(RootMatchers.isPlatformPopup()).perform(click());

        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        TimeUnit.SECONDS.sleep(1L)
        onView(withId(R.id.button_save_track)).perform(click());
        TimeUnit.SECONDS.sleep(5L)


        onView(allOf(withId(R.id.track_name), withText(""), isDisplayed()))
    }

    @Test
    fun addAlbumTest() {
        val userBtn: ViewInteraction =
            onView(
                allOf(
                    withId(R.id.button_coleccionista),
                    withText(R.string.label_button_coleccionista),
                    isDisplayed()
                )
            )
        userBtn.perform(click())

        onView(withId(R.id.drawerLayoutCollector))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.create_album)).perform(click());
        TimeUnit.SECONDS.sleep(1L)
        val calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        onView(withId(R.id.editTextAlbumName)).perform(typeText("Homework"))
        onView(withId(R.id.editTextAlbumCoverage)).perform(typeText("Los trones"))
        onView(withId(R.id.editTextAlbumDescription)).perform(typeText("Album de daft punt"))
        onView(withId(R.id.albumReleaseDate)).perform(forceTypeText(formatter.format(calendar.timeInMillis)))
        val saveAlbumBtn: ViewInteraction =
            onView(
                allOf(
                    withId(R.id.button_save_album),
                    withText(R.string.label_button_save),
                    isDisplayed()
                )
            )
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        saveAlbumBtn.perform(click())
        TimeUnit.SECONDS.sleep(1L)
        onView(allOf(withId(R.id.editTextAlbumName), withText(""), isDisplayed()))
        onView(allOf(withId(R.id.editTextAlbumCoverage), withText(""), isDisplayed()))
        onView(allOf(withId(R.id.editTextAlbumDescription), withText(""), isDisplayed()))
    }

    @Test
    fun addAlbumEmptyFiledsTest() {
        val userBtn: ViewInteraction =
            onView(
                allOf(
                    withId(R.id.button_coleccionista),
                    withText(R.string.label_button_coleccionista),
                    isDisplayed()
                )
            )
        userBtn.perform(click())

        onView(withId(R.id.drawerLayoutCollector))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.create_album)).perform(click());
        TimeUnit.SECONDS.sleep(1L)
        val saveAlbumBtn: ViewInteraction =
            onView(
                allOf(
                    withId(R.id.button_save_album),
                    withText(R.string.label_button_save),
                    isDisplayed()
                )
            )
        saveAlbumBtn.perform(click())
        TimeUnit.SECONDS.sleep(1L)
        onView(withId(R.id.editTextAlbumName)).check(matches(hasErrorText("El campo debe diligenciado y cumplir con las condiciones")));
        onView(withId(R.id.editTextAlbumCoverage)).check(matches(hasErrorText("El campo debe diligenciado y cumplir con las condiciones")));
        onView(withId(R.id.editTextAlbumDescription)).check(matches(hasErrorText("El campo debe diligenciado y cumplir con las condiciones")));
    }

    @Test
    fun addPerformerToAlbumOK() {

        val userBtn: ViewInteraction =
            onView(
                allOf(
                    withId(R.id.button_coleccionista),
                    withText(R.string.label_button_coleccionista),
                    isDisplayed()
                )
            )
        userBtn.perform(click())

        onView(withId(R.id.drawerLayoutCollector))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.associate_performer_album)).perform(click());
        TimeUnit.SECONDS.sleep(1L)

        onView(withId(R.id.autoCompleteTextViewPerformer)).perform(click());
        onData(equalTo("Queen")).inRoot(RootMatchers.isPlatformPopup()).perform(click());

        onView(withId(R.id.autoCompleteTextViewAlbum)).perform(click());
        onData(equalTo("A Night at the Opera")).inRoot(RootMatchers.isPlatformPopup()).perform(click());

        onView(withId(R.id.button_save_performer_to_album)).perform(click());
        TimeUnit.SECONDS.sleep(5L)

    }


    @Test
    fun navigateDetailListPerformers() {
        val userBtn: ViewInteraction =
            onView(allOf(withId(R.id.button_usuario), withText("Soy un Usuario"), isDisplayed()))
        userBtn.perform(click())

        onView(withId(R.id.drawerLayoutUser))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.navViewUser))
            .perform(NavigationViewActions.navigateTo(R.id.performer));

        TimeUnit.SECONDS.sleep(2L)
        onView(withId(R.id.fragments_rv))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()))
    }

    @Test
    fun addFavPerformer() {

        val userBtn: ViewInteraction =
            onView(
                allOf(
                    withId(R.id.button_coleccionista),
                    withText(R.string.label_button_coleccionista),
                    isDisplayed()
                )
            )
        userBtn.perform(click())

        onView(withId(R.id.drawerLayoutCollector))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.fav_performers)).perform(click());

        TimeUnit.SECONDS.sleep(2L)
        onView(withId(R.id.fragments_rv))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                    0,
                    object : ViewAction {
                        override fun getConstraints(): Matcher<View>? {
                            return null
                        }

                        override fun getDescription(): String {
                            return "Click on specific button"
                        }

                        override fun perform(uiController: UiController, view: View) {
                            val tv = view.findViewById<TextView>(R.id.performer_name)
                            if (tv != null) //get focus so drawables are visible
                            {
                                val drawables = tv.compoundDrawables
                                val tvLocation = Rect()
                                tv.getHitRect(tvLocation)
                                val tvBounds: Array<Point?> = arrayOfNulls<Point>(4) //find textview bound locations
                                tvBounds[0] = Point(tvLocation.left, tvLocation.centerY())
                                tvBounds[1] = Point(tvLocation.centerX(), tvLocation.top)
                                tvBounds[2] = Point(tvLocation.right, tvLocation.centerY())
                                tvBounds[3] = Point(tvLocation.centerX(), tvLocation.bottom)
                                for (location in 0..3) if (drawables[location] != null) {
                                    val bounds: Rect = drawables[location]!!.bounds
                                    tvBounds[location]?.offset(
                                        bounds.width() / 2,
                                        bounds.height() / 2
                                    ) //get drawable click location for left, top, right, bottom
                                    if (tv.dispatchTouchEvent(
                                            tvBounds[location]?.x?.let {
                                                tvBounds[location]?.y?.let { it1 ->
                                                    MotionEvent.obtain(
                                                        SystemClock.uptimeMillis(),
                                                        SystemClock.uptimeMillis(),
                                                        MotionEvent.ACTION_DOWN,
                                                        it.toFloat(),
                                                        it1.toFloat(),
                                                        0
                                                    )
                                                }
                                            }
                                        )
                                    ) tv.dispatchTouchEvent(
                                        tvBounds[location]?.let {
                                            tvBounds[location]?.let { it1 ->
                                                MotionEvent.obtain(
                                                    SystemClock.uptimeMillis(),
                                                    SystemClock.uptimeMillis(),
                                                    MotionEvent.ACTION_UP,
                                                    it.x.toFloat(),
                                                    it1.y.toFloat(),
                                                    0
                                                )
                                            }
                                        }
                                    )
                                }
                            }
                        }

                    })
            )
    }


    private fun forceTypeText(text: String): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return "force type text"
            }

            override fun getConstraints(): Matcher<View> {
                return allOf(isEnabled())
            }

            override fun perform(uiController: UiController?, view: View?) {
                (view as? TextView)?.setText(text)
                uiController?.loopMainThreadUntilIdle()
            }
        }
    }
}

