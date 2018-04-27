package infosys.com.androidassignment.mvp.model;

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
         * @param message String
         */
        void onFetchingSuccess(String message);

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
