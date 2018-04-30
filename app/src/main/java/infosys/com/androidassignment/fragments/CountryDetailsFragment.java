package infosys.com.androidassignment.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import infosys.com.androidassignment.R;
import infosys.com.androidassignment.adapter.CountryRecyclerAdapter;
import infosys.com.androidassignment.mvp.model.CountryInteractorImpl;
import infosys.com.androidassignment.mvp.presenter.CountryPresenterImpl;
import infosys.com.androidassignment.mvp.view.CountryContract;
import infosys.com.androidassignment.retrofit.data.CountryResponse;
import infosys.com.androidassignment.utils.ConnectivityUtils;
import infosys.com.androidassignment.utils.SimpleDividerItemDecoration;

/**
 * Copyright 2018 (C) <Infosys Limited>
 * <p>
 * Created on : 27-04-2018
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
 *         | 27-04-2018     |
 * --------|----------------|---------------------------------------------------
 *         | Sandeep Armal  | Added Progress Bar
 * 1.1     | 28-04-2018     | Modified Methods signature
 * --------|--------------- |---------------------------------------------------
 *         | Sandeep Armal  | Added Connectivity Check
 * 1.12    | 29-04-2018     |
 * --------|----------------|---------------------------------------------------
 **/
public class CountryDetailsFragment extends Fragment implements CountryContract.CountryView {

    private static final String EXTENDED_URL = "https://dl.dropboxusercontent.com";
    private CountryPresenterImpl mCountryPresenter;
    private Unbinder mUnbinder;

    // Bind view using ButterKnife
    @BindView(R.id.country_details_recycler_view)
    RecyclerView mCountryDetailsRecyclerView;
    @BindView(R.id.progress)
    ProgressBar mProgressBar;
    @BindView(R.id.simpleSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.country_details_list_fragment, container, false);

        // binding ButterKnife
        mUnbinder = ButterKnife.bind(this, view);

        // Initialize Presenter
        mCountryPresenter = new CountryPresenterImpl(this, new CountryInteractorImpl());

        // add item Decoration for divider
        mCountryDetailsRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mCountryDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCountryDetailsRecyclerView.setHasFixedSize(true);
        mCountryDetailsRecyclerView.setDrawingCacheEnabled(true);
        mCountryDetailsRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        // implement setOnRefreshListener event on SwipeRefreshLayout
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                mSwipeRefreshLayout.setRefreshing(false);
                initiateFetchRequest();
            }
        });

        // initiate data fetch request
        initiateFetchRequest();
        return view;
    }


    /**
     * initiate data fetch request if network is present else
     * show toast message
     */
    private void initiateFetchRequest() {
        ConnectivityUtils objConnectivityUtils = new ConnectivityUtils();

        // check if network is available
        if (objConnectivityUtils.isNetworkAvailable(getActivity())) {
            mCountryPresenter.fetchCountryDetails(EXTENDED_URL);
        } else {
            // network is not available
            Toast.makeText(getActivity(), R.string.network_error, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Show progress dialog while fetching details
     */
    @Override
    public void showWait() {

        mProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * hide progress dialog when fetching complete or error occur
     */
    @Override
    public void hideWait() {
        mProgressBar.setVisibility(View.GONE);
    }

    /**
     * Show toast if failed to fetched data
     *
     * @param errorMessage String Message
     */
    @Override
    public void onGetDataFailure(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    /**
     * Update the list items in list view through adapter after successfully
     * fetching data
     *
     * @param countryResponse countryResponse
     */
    @Override
    public void onGetDataSuccess(CountryResponse countryResponse) {

        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(countryResponse.title);

            mCountryDetailsRecyclerView.setItemViewCacheSize(countryResponse.rows.size());
            CountryRecyclerAdapter adapter = new CountryRecyclerAdapter(getActivity(), countryResponse.rows);
            mCountryDetailsRecyclerView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Butter Knife returns an Unbinder instance when we call bind to do this for you. Call its unbind method in onDestroy
        mUnbinder.unbind();

        // Destroy presenter which holding current view
        mCountryPresenter.onDestroy();
    }
}
