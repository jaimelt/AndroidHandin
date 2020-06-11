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
import com.example.routeplanner.model.Route;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class RoutesAdapter extends RecyclerView.Adapter<RoutesAdapter.ViewHolder> {
    private ArrayList<Route> routes;

    public RoutesAdapter() {
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
        if (routes != null) {
            holder.country_from.setText(routes.get(position).getFrom());
            holder.country_to.setText(routes.get(position).getTo());
        }
    }

    @Override
    public int getItemCount() {
        if (routes != null) {
            return routes.size();
        }
        return 0;
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
