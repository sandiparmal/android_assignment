package infosys.com.androidassignment.mvp.base;

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
 *         | Sandeep Armal  | Modified Method signatures
 *  1.1    | 28-04-2018     |
 *---------|--------------- |---------------------------------------------------
 **/
public interface BaseView {

    /**
     * Show progress dialog while fetching details
     */
    void showWait();

    /**
     * hide progress dialog when fetching complete or error occur
     */
    void hideWait();


}
