package infosys.com.androidassignment.mvp;

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
     * @param message String
     */
    @Override
    public void onFetchingSuccess(String message) {
        countryView.hideProgress();
    }

    /**
     * Trigger when country details fetching failure
     *
     * @param message String
     */
    @Override
    public void onFetchingFailure(String message) {
        countryView.hideProgress();
        countryView.showErrorMessage(message);
    }
}
