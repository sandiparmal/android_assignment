package infosys.com.androidassignment.mvp.model;

import java.util.ArrayList;
import java.util.List;

import infosys.com.androidassignment.mvp.model.CountryInteractor;
import infosys.com.androidassignment.retrofit.data.Country;
import infosys.com.androidassignment.retrofit.data.CountryResponse;
import infosys.com.androidassignment.retrofit.service.NetworkService;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sandy on 4/27/2018.
 */

public class CountryInteractorImpl implements CountryInteractor {


    private Retrofit retrofit;
    private CompositeDisposable mCompositeDisposable;

    /**
     * Request network call and get data from REST url
     *
     * @param URL                   String
     * @param onFetchFinishListener OnFetchFinishListener
     */
    @Override
    public void fetchCountryDetails(String URL, OnFetchFinishListener onFetchFinishListener) {

        //configure Retrofit using Retrofit Builder
        NetworkService networkService = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(NetworkService.class);

        mCompositeDisposable.add(networkService.getCountryDetails()
                .subscribeOn(Schedulers.io()) // “work” on io thread
                .observeOn(AndroidSchedulers.mainThread()) // “listen” on UIThread
                .map(new Function<CountryResponse, ArrayList<Country>>() {
                    @Override
                    public ArrayList<Country> apply(
                            @io.reactivex.annotations.NonNull final CountryResponse cityResponse) throws Exception {
                        // we want to have the country and not the wrapper object
                        return cityResponse.rows;
                    }
                })
                .subscribe(new Consumer<ArrayList<Country>>() {
                    @Override
                    public void accept(
                            @io.reactivex.annotations.NonNull final ArrayList<Country> countryDetails)
                            throws Exception {
                        onFetchFinishListener.onFetchingSuccess(countryDetails);
                    }
                })
        );

    }


}
