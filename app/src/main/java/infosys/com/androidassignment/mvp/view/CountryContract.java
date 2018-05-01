package infosys.com.androidassignment.mvp.view;

import infosys.com.androidassignment.mvp.base.BasePresenter;
import infosys.com.androidassignment.mvp.base.BaseView;
import infosys.com.androidassignment.retrofit.data.Country;
import infosys.com.androidassignment.retrofit.data.CountryResponse;

/**
 * Copyright 2018 (C) <Infosys Limited>
 *
 * Created on : 27-04-2018
 * Author     : Sandeep Armal
 *
 *-----------------------------------------------------------------------------
 * Revision History
 *-----------------------------------------------------------------------------
 *
 * VERSION     AUTHOR/      DESCRIPTION OF CHANGE
 *               DATE                RFC NO
 *-----------------------------------------------------------------------------
 * 1.0     | Sandeep Armal  | Initial Create.
 *         | 27-04-2018     |
 *---------|----------------|---------------------------------------------------
 *         | Sandeep Armal  | Modified Methods Signature
 *  1.1    | 28-04-2018     |
 *---------|--------------- |---------------------------------------------------
 **/
public class CountryContract {

    public interface CountryView extends BaseView {

        /**
         * Update the list items in list view through adapter
         */
        void onGetDataSuccess(CountryResponse countryResponse);

        /**
         * Show toast on data fetching failure
         */
        void onGetDataFailure(String message);
    }

    public interface CountryPresenter extends BasePresenter {

        /**
         * Request network call and get data from REST url
         *
         * @param extendedURL String
         */
        void fetchCountryDetails(String extendedURL);
    }
}
