package infosys.com.androidassignment;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
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
 * 1.1     | Sandeep Armal  | Verified some more methods
 *         | 03-05-2018     |
 * --------|----------------|---------------------------------------------------
 **/
@RunWith(AndroidJUnit4.class)
public class MainActivityPresenterTest extends AbstractTest {

    private CountryPresenterImpl objCountryPresenter;
    private CountryContract.CountryView countryView;

    @Before
    public void setUp() {
        this.objCountryPresenter = spy(new CountryPresenterImpl(new CountryInteractorImpl()));
        countryView = mock(CountryContract.CountryView.class);
    }

    @Test
    public void testAttach() {
        assertNull(objCountryPresenter.countryView);

        objCountryPresenter.attach(countryView);
        assertNotNull(objCountryPresenter.countryView);
    }

    @Test
    public void testDetach() {
        objCountryPresenter.attach(countryView);
        assertNotNull(objCountryPresenter.countryView);

        objCountryPresenter.detach();
        assertNull(objCountryPresenter.countryView);
    }

    @Test
    public void testFetchCountry() {

        // attach view to presenter
        objCountryPresenter.attach(countryView);

        // Mock Network Services
        NetworkService networkService = Mockito.mock(NetworkService.class);

        // get mock results
        CountryResponse countryResponse = getMockCountryResults();

        //Test ok response
        when(networkService.getCountryDetails()).thenReturn(Observable.just(countryResponse));
        objCountryPresenter.fetchCountryDetails("https://dl.dropboxusercontent.com");
        waitFor(50);
        verify(countryView, atLeastOnce()).showWait();
        waitFor(2500);
        ArgumentCaptor<CountryResponse> argument = ArgumentCaptor.forClass(CountryResponse.class);
        verify(objCountryPresenter).onFetchingSuccess(argument.capture());
        waitFor(50);
        verify(countryView, atLeastOnce()).hideWait();
        waitFor(50);
        verify(countryView).onGetDataSuccess(argument.capture());
        //assertEquals(countryResponse, argument.getValue());

    }

    private CountryResponse getMockCountryResults() {

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
