package infosys.com.androidassignment;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import infosys.com.androidassignment.adapter.CountryRecyclerAdapter;
import infosys.com.androidassignment.base.AbstractTest;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Copyright 2018 (C) <Infosys Limited>
 * <p>
 * Created on : 03-05-2018
 * Author     : Sandeep Armal
 * <p>
 * -----------------------------------------------------------------------------
 * Revision History
 * -----------------------------------------------------------------------------
 * <p>
 * VERSION     AUTHOR/      DESCRIPTION OF CHANGE
 * DATE                RFC NO
 * -----------------------------------------------------------------------------
 * 1.0     | Sandeep Armal  | Initial Create.
 * | 03-05-2018     |
 * --------|----------------|---------------------------------------------------
 **/

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CountryDetailsActivityTest extends AbstractTest {

    private static final int ITEM_BELOW_THE_FOLD = 4;

    @Rule
    public ActivityTestRule<CountryDetailsActivity> mActivityRule = new ActivityTestRule<>(CountryDetailsActivity.class);

    @Test
    public void testRecyclerWithAdapter() throws InterruptedException {

        // Check that list adapter is set and views populated
        RecyclerView recyclerView = (RecyclerView) mActivityRule.getActivity().findViewById(R.id.country_details_recycler_view);
        //One improvement would be not to rely on the real network query, but mock the response (Mockito etc...) to avoid depending on network related stuff.
        waitForCondition(() -> recyclerView != null && recyclerView.getAdapter() != null, 3000);
        assertNotNull(recyclerView);
        assertNotNull(recyclerView.getAdapter());
        assertNotSame(0, recyclerView.getAdapter().getItemCount());
    }


    @Test
    public void scrollToItemBelowFold_checkItsText() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.country_details_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_BELOW_THE_FOLD, click()));

        // Match the text in an item below the fold and check that it's displayed.
        String itemElementText = mActivityRule.getActivity().getResources().getString(
                R.string.item_element_text);
        onView(withText(itemElementText)).check(matches(isDisplayed()));
    }

    @Test
    public void itemInMiddleOfList_hasSpecialText() {
        // First, scroll to the view holder using the isInTheMiddle matcher.
        onView(ViewMatchers.withId(R.id.country_details_recycler_view))
                .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()));

        // Check that the item has the special text.
        String middleElementText =
                mActivityRule.getActivity().getResources().getString(R.string.middle);
        onView(withText(middleElementText)).check(matches(isDisplayed()));
    }

    /**
     * Matches the {@link CountryRecyclerAdapter.CountryHolder}s in the middle of the list.
     */
    private static Matcher<CountryRecyclerAdapter.CountryHolder> isInTheMiddle() {
        return new TypeSafeMatcher<CountryRecyclerAdapter.CountryHolder>() {
            @Override
            protected boolean matchesSafely(CountryRecyclerAdapter.CountryHolder customHolder) {
                return customHolder.getIsInTheMiddle();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item in the middle");
            }
        };
    }
}

