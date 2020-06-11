package com.example.routeplanner.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.routeplanner.model.Countries;
import com.example.routeplanner.model.Country;
import com.example.routeplanner.request.CountriesAPI;
import com.example.routeplanner.request.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountriesRepository {
    private static CountriesRepository instance;
    private MutableLiveData<ArrayList<Country>> countries = new MutableLiveData<>();
    private ArrayList<String> countriesNames = new ArrayList<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public static CountriesRepository getInstance() {
        if (instance == null) {
            instance = new CountriesRepository();
        }
        return instance;
    }

    public LiveData<ArrayList<Country>> getCountriesData() {
        isLoading.setValue(true);
        CountriesAPI endpoints = RetrofitClientInstance.getRetrofitInstance().create(CountriesAPI.class);

        Call<Countries> call = endpoints.getCountries();

        call.enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                Countries countriesAPI = response.body();
                countries.setValue(countriesAPI.getResult());
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                System.out.println("FAIL");
            }
        });

        return countries;
    }

    public ArrayList<String> getCountriesNames() {
        CountriesAPI endpoints = RetrofitClientInstance.getRetrofitInstance().create(CountriesAPI.class);

        Call<Countries> call = endpoints.getCountries();

        call.enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                Countries countriesAPI = response.body();
                if (countriesAPI != null) {
                    countries.setValue(countriesAPI.getResult());
                    for (Country country : countries.getValue()) {
                        countriesNames.add(country.getName());
                    }

                }
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                System.out.println("FAIL");
            }
        });


        return countriesNames;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }
}
