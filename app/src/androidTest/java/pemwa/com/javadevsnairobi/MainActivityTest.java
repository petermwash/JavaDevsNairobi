package pemwa.com.javadevsnairobi;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pemwa.com.javadevsnairobi.view.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String TAG = "TestMainActivity";

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        try {
            Thread.sleep(4000);
        }catch (Exception e) {
            Log.e(TAG, "Test setUp: "+e.getMessage());
        }
    }

    @Test
    public void testMainActivityLayout() {
        onView(ViewMatchers.withId(R.id.main))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testRecyclerView(){
        onView(ViewMatchers.withId(R.id.customRecyclerView))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testRecyclerViewItemClicked() {
        onView(withId(R.id.customRecyclerView)).perform(RecyclerViewActions.scrollToPosition(4));

        onView(withId(R.id.customRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(4, click()));
    }

    @Test
    public void testSwipeRefresh() {
        onView(ViewMatchers.withId(R.id.swipeRefresh))
                .check(matches(isDisplayed()));
    }
}
