package infosys.com.androidassignment;

import android.os.Bundle;

import infosys.com.androidassignment.mvp.CountryContract;
import infosys.com.androidassignment.mvp.base.BaseActivity;

public class CountryDetailsActivity extends BaseActivity implements CountryContract.CountryView {


    /**
     * Layout resource to be inflated
     *
     * @return layout resource
     */
    @Override
    protected int getContentResource() {
        return R.layout.activity_country_details;
    }

    /**
     * Initialisations
     *
     * @param savedState Bundle
     */
    @Override
    protected void init(Bundle savedState) {

    }

    /**
     * Show progress dialog while fetching details
     */
    @Override
    public void showProgress() {

    }

    /**
     * hide progress dialog when fetching complete or error occur
     */
    @Override
    public void hideProgress() {

    }

    /**
     * Show error message if any exception occur
     *
     * @param errorMessage String Message
     */
    @Override
    public void showErrorMessage(String errorMessage) {

    }

    /**
     * Update the list items in list view through adapter
     */
    @Override
    public void updateListView() {

    }
}
