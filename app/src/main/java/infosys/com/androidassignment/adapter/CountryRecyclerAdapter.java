package infosys.com.androidassignment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import infosys.com.androidassignment.R;
import infosys.com.androidassignment.mvp.model.data.Country;

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
        private Country mCountry;
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.description)
        TextView mDescription;
        @BindView(R.id.image)
        ImageView mImage;

        public CountryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,
                            mCountry.getTitle() + " clicked!", Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }

        public void bindData(Country country) {
            mCountry = country;
            if(country.getImageHref()!= null){
                mImage.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(country.getImageHref())
                        .into(mImage);
            } else {
                mImage.setVisibility(View.GONE);
            }

            mTitle.setText(country.getTitle());
            mDescription.setText(country.getDescription());
        }
    }
}