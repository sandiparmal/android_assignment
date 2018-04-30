package infosys.com.androidassignment;

import android.app.Application;
import android.content.Context;

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

    // Fet application context
    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
