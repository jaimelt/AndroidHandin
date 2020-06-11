package com.example.routeplanner.request;

import com.example.routeplanner.model.Countries;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountriesAPI {

    @GET("/countries")
    Call<Countries> getCountries();

}
