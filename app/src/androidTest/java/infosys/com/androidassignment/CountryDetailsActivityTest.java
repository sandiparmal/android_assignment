package infosys.com.androidassignment;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import infosys.com.androidassignment.base.AbstractTest;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;

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
 *              DATE                RFC NO
 * -----------------------------------------------------------------------------
 * 1.0     | Sandeep Armal  | Initial Create.
 *         | 03-05-2018     |
 * --------|----------------|---------------------------------------------------
 **/

@RunWith(AndroidJUnit4.class)
public class CountryDetailsActivityTest extends AbstractTest {

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
}
