package infosys.com.androidassignment.mvp.base;

/**
 * Created by sandy on 4/27/2018.
 */

public interface BaseView {

    /**
     * Show progress dialog while fetching details
     */
    void showProgress();

    /**
     * hide progress dialog when fetching complete or error occur
     */
    void hideProgress();

    /**
     * Show error message if any exception occur
     * @param errorMessage String Message
     */
    void showErrorMessage(String errorMessage);
}
