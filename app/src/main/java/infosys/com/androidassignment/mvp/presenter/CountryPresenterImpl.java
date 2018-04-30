package infosys.com.androidassignment.mvp.presenter;

import infosys.com.androidassignment.mvp.model.CountryInteractor;
import infosys.com.androidassignment.mvp.view.CountryContract;
import infosys.com.androidassignment.retrofit.data.CountryResponse;

/**
 * Copyright 2018 (C) <Infosys Limited>
 * <p>
 * Created on : 27-04-2018
 * Author     : Sandeep Armal
 * <p>
 * -----------------------------------------------------------------------------
 * Revision History
 * -----------------------------------------------------------------------------
 * <p>
 * VERSION     AUTHOR/      DESCRIPTION OF CHANGE
 *              DATE            RFC NO
 * -----------------------------------------------------------------------------
 * 1.0     | Sandeep Armal  | Initial Create.
 *         | 27-04-2018     |
 * --------|----------------|---------------------------------------------------
 *         | Sandeep Armal  | Modified Methods Signature
 * 1.1     | 28-04-2018     |
 * --------|--------------- |---------------------------------------------------
 **/
public class CountryPresenterImpl implements CountryContract.CountryPresenter, CountryInteractor.OnFetchFinishListener {

    // initialization
    private CountryContract.CountryView countryView;
    private final CountryInteractor countryInteractor;

    public CountryPresenterImpl(CountryContract.CountryView countryView, CountryInteractor countryInteractor) {

        // assigning objects
        this.countryView = countryView;
        this.countryInteractor = countryInteractor;
    }

    /**
     * Destroy presenter which holding connection between view and model
     */
    @Override
    public void onDestroy() {

        if (countryView != null) {
            countryView = null;
        }
    }

    /**
     * Request network call and get data from REST url
     *
     * @param extendedURL String
     */
    @Override
    public void fetchCountryDetails(String extendedURL) {
        // show progress bar
        countryView.showWait();
        // call interactor to fetch country details
        countryInteractor.fetchCountryDetails(extendedURL, this);
    }

    /**
     * Trigger when country details fetching success
     *
     * @param countryResponse countryResponse
     */
    @Override
    public void onFetchingSuccess(CountryResponse countryResponse) {
        countryView.hideWait();
        countryView.onGetDataSuccess(countryResponse);
    }

    /**
     * Trigger when country details fetching failure
     *
     * @param message String
     */
    @Override
    public void onFetchingFailure(String message) {
        countryView.hideWait();
        countryView.onGetDataFailure(message);
    }
}
