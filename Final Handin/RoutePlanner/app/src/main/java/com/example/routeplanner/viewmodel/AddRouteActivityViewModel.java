package com.example.routeplanner.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.routeplanner.model.MyRoute;
import com.example.routeplanner.repositories.CountriesRepository;
import com.example.routeplanner.repositories.RoutesRepository;

import java.util.ArrayList;

public class AddRouteActivityViewModel extends AndroidViewModel {
    private CountriesRepository countriesRepository;
    private RoutesRepository routesRepository;

    public AddRouteActivityViewModel(@NonNull Application application) {
        super(application);
        routesRepository = RoutesRepository.getInstance(application);
        countriesRepository = CountriesRepository.getInstance();
    }

    public ArrayList<String> getCountries() {
        return countriesRepository.getCountriesNames();
    }

    public void addRoute(String routeID, String from, String to, boolean isFavorite) {
        MyRoute route = new MyRoute(routeID, from, to, isFavorite);
        routesRepository.addRoute(route);
    }

}
