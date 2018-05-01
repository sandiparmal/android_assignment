package infosys.com.androidassignment;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import infosys.com.androidassignment.base.AbstractTest;
import infosys.com.androidassignment.mvp.model.CountryInteractorImpl;
import infosys.com.androidassignment.mvp.presenter.CountryPresenterImpl;
import infosys.com.androidassignment.mvp.view.CountryContract;
import infosys.com.androidassignment.retrofit.data.Country;
import infosys.com.androidassignment.retrofit.data.CountryResponse;
import infosys.com.androidassignment.retrofit.service.NetworkService;
import io.reactivex.Observable;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Copyright 2018 (C) <Infosys Limited>
 * <p>
 * Created on : 01-05-2018
 * Author     : Sandeep Armal
 * <p>
 * -----------------------------------------------------------------------------
 * Revision History
 * -----------------------------------------------------------------------------
 * <p>
 * VERSION     AUTHOR/      DESCRIPTION OF CHANGE
 *              DATE                RFC NO
 * -----------------------------------------------------------------------------
 * 1.0     | Sandeep Armal  | Initial Create.
 *         | 01-05-2018     |
 * --------|----------------|---------------------------------------------------
 **/
@RunWith(AndroidJUnit4.class)
public class MainActivityPresenterTest extends AbstractTest {

    @Test
    public void testAttach() {
        CountryPresenterImpl objCountryPresenter = new CountryPresenterImpl(new CountryInteractorImpl());
        assertNull(objCountryPresenter.countryView);

        objCountryPresenter.attach(mock(CountryContract.CountryView.class));
        assertNotNull(objCountryPresenter.countryView);
    }

    @Test
    public void testDetach() {
        CountryPresenterImpl objCountryPresenter = new CountryPresenterImpl(new CountryInteractorImpl());
        objCountryPresenter.attach(mock(CountryContract.CountryView.class));
        assertNotNull(objCountryPresenter.countryView);

        objCountryPresenter.detach();
        assertNull(objCountryPresenter.countryView);
    }

    @Test
    public void testFetchCountry() {
        CountryPresenterImpl objCountryPresenter = new CountryPresenterImpl(new CountryInteractorImpl());
        CountryContract.CountryView countryView = mock(CountryContract.CountryView.class);

        //Test null view is not crashing at least
        //objCountryPresenter.fetchCountryDetails("https://dl.dropboxusercontent.com");

        /// attach view to presenter
        objCountryPresenter.attach(countryView);

        //Test null text
        objCountryPresenter.fetchCountryDetails(null);

        // test with url
        objCountryPresenter.fetchCountryDetails("https://dl.dropboxusercontent.com");

        CountryResponse searchResults = getFakeSearchResults();

        //Test ok response
        NetworkService networkService = Mockito.mock(NetworkService.class);
        when(networkService.getCountryDetails()).thenReturn(Observable.just(searchResults));
        objCountryPresenter.fetchCountryDetails("https://dl.dropboxusercontent.com");
        waitFor(50);

    }

    private CountryResponse getFakeSearchResults() {

        CountryResponse objCountryResponse = new CountryResponse();
        objCountryResponse.title = "About India";

        ArrayList<Country> list = new ArrayList<>();
        Country objCountry = new Country();
        objCountry.setTitle("Languages");
        objCountry.setDescription("As per the 2011 Census, there are about 122 languages");
        objCountry.setImageHref("https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/South_Asian_Language_Families.jpg/450px-South_Asian_Language_Families.jpg");
        list.add(objCountry);

        objCountry = new Country();
        objCountry.setTitle("States");
        objCountry.setDescription("Total Number of States in India are 29, The India has 7 Union Territories which includes the National Capital Territory of Delhi.");
        objCountry.setImageHref("http://static.ibnlive.in.com/ibnlive/pix/ibnhome/animated-map-of-creation-of-indian-states-010614.gif");
        list.add(objCountry);

        objCountry = new Country();
        objCountry.setTitle("");
        objCountry.setDescription("");
        objCountry.setImageHref("");
        list.add(objCountry);

        objCountry = new Country();
        objCountry.setTitle("Geography");
        objCountry.setDescription("");
        objCountry.setImageHref("");
        list.add(objCountry);

        objCountryResponse.rows = list;

        return objCountryResponse;

    }

}
