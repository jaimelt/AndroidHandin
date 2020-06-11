package com.example.routeplanner.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.routeplanner.R;
import com.example.routeplanner.model.Country;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {
    private ArrayList<Country> countries;

    public CountriesAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        if (countries != null) {
            try {
                holder.country_from.setText(countries.get(position).getName());
                holder.country_to.setText(countries.get(position + 1).getName());
            } catch (IndexOutOfBoundsException e) {
                holder.country_from.setText(countries.get(position).getName());
                holder.country_to.setText(countries.get(position).getName());
            }
        }
    }

    @Override
    public int getItemCount() {
        if (countries != null) {
            return countries.size();
        }
        return 0;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView country_to;
        TextView country_from;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_arrow);
            country_to = itemView.findViewById(R.id.country_to);
            country_from = itemView.findViewById(R.id.country_from);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
