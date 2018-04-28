package infosys.com.androidassignment.retrofit.service;

/**
 * Created by sandy on 4/28/2018.
 */

import infosys.com.androidassignment.retrofit.data.Country;
import infosys.com.androidassignment.retrofit.data.CountryResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NetworkService {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    Observable<CountryResponse> getCountryDetails();


}

