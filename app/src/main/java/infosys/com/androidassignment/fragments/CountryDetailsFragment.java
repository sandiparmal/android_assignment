package infosys.com.androidassignment.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import infosys.com.androidassignment.R;
import infosys.com.androidassignment.adapter.CountryRecyclerAdapter;
import infosys.com.androidassignment.retrofit.data.Country;
import infosys.com.androidassignment.mvp.view.CountryContract;
import infosys.com.androidassignment.mvp.model.CountryInteractorImpl;
import infosys.com.androidassignment.mvp.presenter.CountryPresenterImpl;
import infosys.com.androidassignment.utils.SimpleDividerItemDecoration;

/**
 * Created by sandy on 4/27/2018.
 */

public class CountryDetailsFragment extends Fragment implements CountryContract.CountryView{

    private static final String EXTENDED_URL = "https://dl.dropboxusercontent.com";
    private CountryPresenterImpl mCountryPresenter;
    private Unbinder mUnbinder;

    @BindView(R.id.country_details_recycler_view) RecyclerView mCountryDetailsRecyclerView;
    @BindView(R.id.progress) ProgressBar mProgressBar;
    @BindView(R.id.simpleSwipeRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;

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

        initiateFetchRequest();
        return view;
    }


    private void initiateFetchRequest() {
        mCountryPresenter.fetchCountryDetails(EXTENDED_URL);
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

    }

    /**
     * Update the list items in list view through adapter after successfully
     * fetching data
     */
    @Override
    public void onGetDataSuccess(ArrayList<Country> countryList) {

        mCountryDetailsRecyclerView.setItemViewCacheSize(countryList.size());
        CountryRecyclerAdapter adapter = new CountryRecyclerAdapter(getActivity(), countryList);
        mCountryDetailsRecyclerView.setAdapter(adapter);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();

        // Butter Knife returns an Unbinder instance when we call bind to do this for you. Call its unbind method in onDestroy
        mUnbinder.unbind();

        // Destroy presenter which holding current view
        mCountryPresenter.onDestroy();
    }
}
