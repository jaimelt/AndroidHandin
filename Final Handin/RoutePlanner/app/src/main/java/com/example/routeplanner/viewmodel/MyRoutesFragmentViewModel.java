package com.example.routeplanner.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.routeplanner.model.MyRoute;
import com.example.routeplanner.repositories.CountriesRepository;
import com.example.routeplanner.repositories.RoutesRepository;

import java.util.ArrayList;

public class MyRoutesFragmentViewModel extends AndroidViewModel {
    private RoutesRepository routesRepository;

    public MyRoutesFragmentViewModel(@NonNull Application application) {
        super(application);
        routesRepository = RoutesRepository.getInstance(application);
    }

    public LiveData<ArrayList<MyRoute>> getRoutesData() {
        return routesRepository.getRoutes();
    }

    public LiveData<Boolean> getIsLoading() {
        return routesRepository.getIsLoading();
    }

    public void removeRoute(int adapterPosition) {
        routesRepository.removeRoute(adapterPosition);
    }
}
