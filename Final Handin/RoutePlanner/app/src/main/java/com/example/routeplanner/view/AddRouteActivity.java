package com.example.routeplanner.view;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.routeplanner.R;
import com.example.routeplanner.adapters.RoutesAdapter;
import com.example.routeplanner.viewmodel.AddRouteActivityViewModel;

public class AddRouteActivity extends AppCompatActivity {
    private AddRouteActivityViewModel viewModel;
    private AutoCompleteTextView countries_from;
    private AutoCompleteTextView countries_to;
    private CheckBox favorite;
    private RoutesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .4));

        viewModel = new ViewModelProvider(this).get(AddRouteActivityViewModel.class);

        adapter = new RoutesAdapter();

        bindViews();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, viewModel.getCountries());

        countries_from.setAdapter(arrayAdapter);
        countries_to.setAdapter(arrayAdapter);
    }

    public void bindViews() {
        countries_from = findViewById(R.id.select_starting_from);
        countries_to = findViewById(R.id.select_going_to);
        favorite = findViewById(R.id.favorite);
    }

    public void onCreateRoute(View view) {
        String country_from = countries_from.getText().toString();
        String country_to = countries_to.getText().toString();
        boolean favoriteRoute = false;
        if (favorite.isChecked()) {
            favoriteRoute = true;
        }
        if (countries_from.length() == 0 || countries_to.length() == 0) {
            countries_from.setError("Choose a country");
            countries_to.setError("Choose a country");
        } else {
            viewModel.addRoute("aaaa", country_from, country_to, favoriteRoute);
            System.out.println(favoriteRoute);
            finish();
        }
    }
}
