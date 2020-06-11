package com.example.routeplanner.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.routeplanner.model.Country;
import com.example.routeplanner.repositories.CountriesRepository;

import java.util.ArrayList;

public class RoutesFragmentViewModel extends AndroidViewModel {
    private CountriesRepository countriesRepository;

    public RoutesFragmentViewModel(Application application) {
        super(application);
        countriesRepository = CountriesRepository.getInstance();
    }

    public LiveData<ArrayList<Country>> getCountries() {
        return countriesRepository.getCountriesData();
    }

    public LiveData<Boolean> getIsLoading() {
        return countriesRepository.getIsLoading();
    }
}
