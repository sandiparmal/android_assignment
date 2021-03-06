package infosys.com.androidassignment.retrofit.service;

import infosys.com.androidassignment.retrofit.data.CountryResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Copyright 2018 (C) <Infosys Limited>
 *
 * Created on : 28-04-2018
 * Author     : Sandeep Armal
 *
 *-----------------------------------------------------------------------------
 * Revision History (Release 1.0.0.0)
 *-----------------------------------------------------------------------------
 *
 * VERSION     AUTHOR/      DESCRIPTION OF CHANGE
 *               DATE                RFC NO
 *-----------------------------------------------------------------------------
 * 1.0     | Sandeep Armal  | Initial Create.
 *         | 28-04-2018     |
 *---------|----------------|---------------------------------------------------
 **/
public interface NetworkService {

    // retrofit get call
    @GET("/s/2iodh4vg0eortkl/facts.json")
    Observable<CountryResponse> getCountryDetails();


}

