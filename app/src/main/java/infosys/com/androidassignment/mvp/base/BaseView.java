package infosys.com.androidassignment.mvp.base;

/**
 * Created by sandy on 4/27/2018.
 */

public interface BaseView {

    /**
     * Show progress dialog while fetching details
     */
    void showWait();

    /**
     * hide progress dialog when fetching complete or error occur
     */
    void hideWait();


}
