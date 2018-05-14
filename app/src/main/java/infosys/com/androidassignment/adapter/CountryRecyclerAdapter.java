
package infosys.com.androidassignment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import infosys.com.androidassignment.R;
import infosys.com.androidassignment.retrofit.data.Country;

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
 *         | Sandeep Armal  | Replace Glide with Picasso
 *  1.1    | 28-04-2018     | Added Progress Bar while loading images
 *---------|--------------- |---------------------------------------------------
 **/
public class CountryRecyclerAdapter extends RecyclerView.Adapter<CountryRecyclerAdapter.CountryHolder> {
    private ArrayList<Country> countryDetailsList;
    private Context context;

    public CountryRecyclerAdapter(Context context, ArrayList<Country> countryDetailsList) {
        this.countryDetailsList = countryDetailsList;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // inflate view
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.country_list_item, parent, false);
        return new CountryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {
        // get country by current position
        Country country = countryDetailsList.get(position);

        /* calculate middle element position */
        int middlePosition = countryDetailsList.size() / 2;
        if (position == middlePosition ) {
            holder.setIsInTheMiddle(true);
        } else {
            holder.setIsInTheMiddle(false);
        }
        holder.bindData(country);
    }

    @Override
    public int getItemCount() {
        return countryDetailsList.size();
    }

    public class CountryHolder extends RecyclerView.ViewHolder {

        // bind views using ButterKnife
        @BindView(R.id.title)
        TextView mTvTitle;
        @BindView(R.id.description)
        TextView mTvDescription;
        @BindView(R.id.image)
        ImageView mImage;
        @BindView(R.id.loading)
        ProgressBar loadingProgressBar;
        private View itemView;

        // We'll use this field to showcase matching the holder from the test.
        private boolean mIsInTheMiddle = false;
        private String middleItemText;

        private CountryHolder(View itemView) {
            super(itemView);

            // initiate ButterKnife
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }

        public boolean getIsInTheMiddle() {
            return mIsInTheMiddle;
        }

        void setIsInTheMiddle(boolean isInTheMiddle) {
            mIsInTheMiddle = isInTheMiddle;
        }


        private void bindData(Country country) {

            // check if title, description and image url is null
            if (TextUtils.isEmpty(country.getTitle()) && TextUtils.isEmpty(country.getDescription())
                    && TextUtils.isEmpty(country.getTitle())) {
                itemView.setVisibility(View.GONE);
            } else {
                itemView.setVisibility(View.VISIBLE);
            }

            // show loading progress bar while loading image
            loadingProgressBar.setVisibility(View.VISIBLE);
            if (country.getImageHref() != null) {

                // initiate picasso to load image
                Picasso.get()
                        .load(country.getImageHref())
                        .into(mImage, new Callback() {
                            @Override
                            public void onSuccess() {
                                loadingProgressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {
                                loadingProgressBar.setVisibility(View.GONE);
                                //mImage
                                //      .setBackgroundResource(R.drawable.small_logo);
                            }

                        });
            } else {
                // image url is null, hide progress bar and image view
                loadingProgressBar.setVisibility(View.GONE);
                mImage.setVisibility(View.GONE);

            }

            // check if title is null
            if (TextUtils.isEmpty(country.getTitle())) {
                mTvTitle.setVisibility(View.GONE);
            } else {
                mTvTitle.setText(country.getTitle());
            }

            // check if description is null
            if (TextUtils.isEmpty(country.getDescription())) {
                mTvDescription.setVisibility(View.GONE);
            } else {
                mTvDescription.setText(country.getDescription());
            }

        }
    }
}
