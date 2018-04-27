package infosys.com.androidassignment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import infosys.com.androidassignment.fragments.CountryDetailsFragment;
import infosys.com.androidassignment.mvp.base.BaseActivity;

public class CountryDetailsActivity extends BaseActivity {


    /**
     * Layout resource to be inflated
     *
     * @return layout resource
     */
    @Override
    protected int getContentResource() {
        return R.layout.activity_country_details;
    }

    /**
     * Initialisations
     *
     * @param savedState Bundle
     */
    @Override
    protected void init(Bundle savedState) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = new CountryDetailsFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }


}
