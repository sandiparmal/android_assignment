package infosys.com.androidassignment.mvp.view;

import java.util.ArrayList;
import java.util.List;

import infosys.com.androidassignment.mvp.base.BasePresenter;
import infosys.com.androidassignment.mvp.base.BaseView;
import infosys.com.androidassignment.retrofit.data.Country;

/**
 * Created by Sandeep Armal on 4/27/2018.
 */

public class CountryContract {

    public interface CountryView extends BaseView {

        /**
         * Update the list items in list view through adapter
         */
        void onGetDataSuccess(ArrayList<Country> countryList);
        void onGetDataFailure(String message);
    }

    public interface CountryPresenter extends BasePresenter {

        void fetchCountryDetails(String extendedURL);
    }
}
