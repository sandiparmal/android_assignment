package infosys.com.androidassignment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import infosys.com.androidassignment.R;
import infosys.com.androidassignment.retrofit.data.Country;

/**
 * Created by sandy on 4/27/2018.
 */

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
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.country_list_item, parent, false);
        return new CountryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {
        Country s = countryDetailsList.get(position);
        holder.bindData(s);
    }

    @Override
    public int getItemCount() {
        return countryDetailsList.size();
    }

    public class CountryHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView mTvTitle;
        @BindView(R.id.description)
        TextView mTvDescription;
        @BindView(R.id.image)
        ImageView mImage;
        @BindView(R.id.loading)
        ProgressBar loadingProgressBar;
        private View itemView;

        public CountryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }


        public void bindData(Country country) {

            if (TextUtils.isEmpty(country.getTitle()) && TextUtils.isEmpty(country.getDescription())
                    && TextUtils.isEmpty(country.getTitle())) {
                itemView.setVisibility(View.GONE);
            } else {
                itemView.setVisibility(View.VISIBLE);
            }
            loadingProgressBar.setVisibility(View.VISIBLE);
            if (country.getImageHref() != null) {

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
                loadingProgressBar.setVisibility(View.GONE);
                mImage.setVisibility(View.GONE);

            }

            if (TextUtils.isEmpty(country.getTitle())) {
                mTvTitle.setVisibility(View.GONE);
            } else {
                mTvTitle.setText(country.getTitle());
            }

            if (TextUtils.isEmpty(country.getDescription())) {
                mTvDescription.setVisibility(View.GONE);
            } else {
                mTvDescription.setText(country.getDescription());
            }

        }
    }
}
