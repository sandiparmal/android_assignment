package infosys.com.androidassignment.mvp.model;

import java.util.ArrayList;
import java.util.List;

import infosys.com.androidassignment.retrofit.data.Country;

/**
 * Created by sandy on 4/27/2018.
 */

public interface CountryInteractor {

    /**
     * OnFetchListener will trigger onFetchingSuccess and onFetchingFailure depends on result
     */
    interface OnFetchFinishListener {
        /**
         * Trigger when country details fetching success
         * @param countryDetails Country
         */
        void onFetchingSuccess(ArrayList<Country> countryDetails);

        /**
         *  Trigger when country details fetching failure
         * @param message String
         */
        void onFetchingFailure(String message);
    }

    /**
     * Request network call and get data from REST url
     * @param URL String
     */
    void fetchCountryDetails(String URL, CountryInteractor.OnFetchFinishListener onFetchListener);
}
