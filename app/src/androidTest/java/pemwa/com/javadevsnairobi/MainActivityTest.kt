package pemwa.com.javadevsnairobi

import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.util.Log

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import pemwa.com.javadevsnairobi.view.MainActivity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @JvmField
    @Rule
    var activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        try {
            Thread.sleep(4000)
        } catch (e: Exception) {
            Log.e(TAG, "Test setUp: " + e.message)
        }

    }

    @Test
    fun testMainActivityLayout() {
        onView(ViewMatchers.withId(R.id.main))
                .check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerView() {
        onView(ViewMatchers.withId(R.id.customRecyclerView))
                .check(matches(isDisplayed()))
    }

//    @Test
//    fun testRecyclerViewItemClicked() {
//        onView(withId(R.id.customRecyclerView)).perform(RecyclerViewActions.scrollToPosition(4))
//
//        onView(withId(R.id.customRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(4, click()))
//    }

    @Test
    fun testSwipeRefresh() {
        onView(ViewMatchers.withId(R.id.swipeRefresh))
                .check(matches(isDisplayed()))
    }

    companion object {

        private val TAG = "TestMainActivity"
    }
}
