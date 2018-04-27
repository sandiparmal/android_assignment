package infosys.com.androidassignment.mvp.view;

import infosys.com.androidassignment.mvp.base.BasePresenter;
import infosys.com.androidassignment.mvp.base.BaseView;

/**
 * Created by Sandeep Armal on 4/27/2018.
 */

public class CountryContract {

    public interface CountryView extends BaseView {

        /**
         * Update the list items in list view through adapter
         */
        void updateListView();
    }

    public interface CountryPresenter extends BasePresenter {

        void fetchCountryDetails(String extendedURL);
    }
}
