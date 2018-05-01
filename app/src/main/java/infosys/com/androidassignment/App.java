package infosys.com.androidassignment;

import android.app.Application;
import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Copyright 2018 (C) <Infosys Limited>
 *
 * Created on : 30-04-2018
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
 *         | 30-04-2018     |
 *---------|----------------|---------------------------------------------------
 **/

public class App extends Application {

    private static Context context;

    // Get application context
    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    /**
     * Build Retrofit Client
     * @param url base url
     * @return retrofit
     */
    public static Retrofit getClient(String url){
        return new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
