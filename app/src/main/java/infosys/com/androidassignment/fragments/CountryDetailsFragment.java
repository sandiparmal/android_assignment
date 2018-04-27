package infosys.com.androidassignment.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import infosys.com.androidassignment.R;
import infosys.com.androidassignment.mvp.view.CountryContract;
import infosys.com.androidassignment.mvp.model.CountryInteractorImpl;
import infosys.com.androidassignment.mvp.presenter.CountryPresenterImpl;
import infosys.com.androidassignment.utils.SimpleDividerItemDecoration;

/**
 * Created by sandy on 4/27/2018.
 */

public class CountryDetailsFragment extends Fragment implements CountryContract.CountryView{

    public static final String EXTENDED_URL = "s/2iodh4vg0eortkl/facts.json";
    @BindView(R.id.title) RecyclerView mCountryDetailsRecyclerView;
    private CountryPresenterImpl countryPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.country_details_list_fragment, container, false);

        // binding ButterKnife
        ButterKnife.bind(this, view);

        // Initialize Presenter
        countryPresenter = new CountryPresenterImpl(this, new CountryInteractorImpl());

        // add item Decoration for divider
        mCountryDetailsRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mCountryDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        initiateFetchRequest();
        return view;
    }

    private void initiateFetchRequest() {
        countryPresenter.fetchCountryDetails(EXTENDED_URL);
    }

    /**
     * Show progress dialog while fetching details
     */
    @Override
    public void showProgress() {

    }

    /**
     * hide progress dialog when fetching complete or error occur
     */
    @Override
    public void hideProgress() {

    }

    /**
     * Show error message if any exception occur
     *
     * @param errorMessage String Message
     */
    @Override
    public void showErrorMessage(String errorMessage) {

    }

    /**
     * Update the list items in list view through adapter
     */
    @Override
    public void updateListView() {

    }
}
