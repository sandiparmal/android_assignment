package infosys.com.androidassignment.mvp.model;

import android.support.annotation.NonNull;

import infosys.com.androidassignment.App;
import infosys.com.androidassignment.R;
import infosys.com.androidassignment.retrofit.data.CountryResponse;
import infosys.com.androidassignment.retrofit.service.NetworkService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
 *         | Sandeep Armal  | Added Retrofit
 *  1.1    | 28-04-2018     | Added RxAndroid
 *---------|--------------- |---------------------------------------------------
 **/
public class CountryInteractorImpl implements CountryInteractor {

    /**
     * Collects all subscriptions to un-subscribe later
     */
    @NonNull
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * Request network call and get data from REST url
     *
     * @param URL                   String
     * @param onFetchFinishListener OnFetchFinishListener
     */
    @Override
    public void fetchCountryDetails(String URL, OnFetchFinishListener onFetchFinishListener) {

        try{
            //configure Retrofit using Retrofit Builder
            NetworkService networkService = App.getClient(URL).create(NetworkService.class);

            mCompositeDisposable.add(networkService.getCountryDetails()
                    .subscribeOn(Schedulers.io()) // “work” on io thread
                    .observeOn(AndroidSchedulers.mainThread()) // “listen” on UIThread
                    .map(new Function<CountryResponse, CountryResponse>() {
                        @Override
                        public CountryResponse apply(
                                @io.reactivex.annotations.NonNull final CountryResponse countryResponse) throws Exception {
                            // we want to have the country and not the wrapper object
                            return countryResponse;
                        }
                    })
                    .subscribe(new Consumer<CountryResponse>() {
                        @Override
                        public void accept(
                                @io.reactivex.annotations.NonNull final CountryResponse countryDetails)
                                throws Exception {
                            onFetchFinishListener.onFetchingSuccess(countryDetails);
                        }
                    })
            );
        } catch (Exception e){
            onFetchFinishListener.onFetchingFailure(App.getContext().getString(R.string.fetch_failed_message));
        }


    }

}
