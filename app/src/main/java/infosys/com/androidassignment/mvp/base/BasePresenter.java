package infosys.com.androidassignment.mvp.base;

import infosys.com.androidassignment.mvp.view.CountryContract;

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
 **/
public interface BasePresenter<CountryView> {

    /**
     * Called when the view is created and wants to attach its presenter
     */
    void attach(CountryContract.CountryView view);

    /**
     * Called when the view is destroyed to get rid of its presenter
     */
    void detach();
}
