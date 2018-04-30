package infosys.com.androidassignment.mvp.model;

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

public interface CountryInteractor {

    /**
     * OnFetchListener will trigger onFetchingSuccess and onFetchingFailure depends on result
     */
    interface OnFetchFinishListener {
        /**
         * Trigger when country details fetching success
         *
         * @param countryDetails Country
         */
        void onFetchingSuccess(CountryResponse countryDetails);

        /**
         * Trigger when country details fetching failure
         *
         * @param message String
         */
        void onFetchingFailure(String message);
    }

    /**
     * Request network call and get data from REST url
     *
     * @param URL String
     */
    void fetchCountryDetails(String URL, CountryInteractor.OnFetchFinishListener onFetchListener);
}
