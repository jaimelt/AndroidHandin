package com.example.routeplanner.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.routeplanner.R;
import com.example.routeplanner.adapters.CountriesAdapter;
import com.example.routeplanner.model.Country;
import com.example.routeplanner.viewmodel.RoutesFragmentViewModel;

import java.util.ArrayList;

public class RoutesFragment extends Fragment {
    private RoutesFragmentViewModel viewModel;
    private CountriesAdapter adapter;
    private ProgressBar progressBar;

    public RoutesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(RoutesFragmentViewModel.class);

        viewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                } else progressBar.setVisibility(View.GONE);
            }
        });
        viewModel.getCountries().observe(getViewLifecycleOwner(), new Observer<ArrayList<Country>>() {

            @Override
            public void onChanged(ArrayList<Country> countries) {
                adapter.setCountries(countries);
                adapter.notifyDataSetChanged();
            }
        });

        return inflater.inflate(R.layout.routes_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new CountriesAdapter();
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

        progressBar = view.findViewById(R.id.progress_bar_routes);
    }


    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
