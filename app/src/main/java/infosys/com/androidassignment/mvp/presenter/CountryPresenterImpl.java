package infosys.com.androidassignment.mvp.presenter;

import java.util.ArrayList;
import java.util.List;

import infosys.com.androidassignment.mvp.view.CountryContract;
import infosys.com.androidassignment.mvp.model.CountryInteractor;
import infosys.com.androidassignment.retrofit.data.Country;

/**
 * Created by sandy on 4/27/2018.
 */

public class CountryPresenterImpl implements CountryContract.CountryPresenter, CountryInteractor.OnFetchFinishListener {

    private CountryContract.CountryView countryView;
    private final CountryInteractor countryInteractor;

    public CountryPresenterImpl(CountryContract.CountryView countryView, CountryInteractor countryInteractor){

        this.countryView = countryView;
        this.countryInteractor = countryInteractor;
    }
    /**
     * Destroy presenter which holding connection between view and model
     */
    @Override
    public void onDestroy() {

        if(countryView != null){
            countryView = null;
        }
    }

    @Override
    public void fetchCountryDetails(String extendedURL) {
        countryView.showProgress();
        countryInteractor.fetchCountryDetails(extendedURL, this);
    }

    /**
     * Trigger when country details fetching success
     *
     * @param countryList Country
     */
    @Override
    public void onFetchingSuccess(ArrayList<Country> countryList) {
        countryView.hideProgress();
        countryView.onGetDataSuccess(countryList);
    }

    /**
     * Trigger when country details fetching failure
     *
     * @param message String
     */
    @Override
    public void onFetchingFailure(String message) {
        countryView.hideProgress();
        countryView.onGetDataFailure(message);
    }
}
