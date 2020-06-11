package com.example.routeplanner.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.routeplanner.model.MyRoute;
import com.example.routeplanner.repositories.RoutesRepository;

import java.util.List;

public class FavoriteFragmentViewModel extends AndroidViewModel {
    private RoutesRepository routesRepository;

    public FavoriteFragmentViewModel(@NonNull Application application) {
        super(application);
        routesRepository = RoutesRepository.getInstance(application);
    }

    public LiveData<List<MyRoute>> getRoutes() {
        return routesRepository.getFavRoutes();
    }

    public void removeRoute(int adapterPosition) {
        routesRepository.deleteRoute(adapterPosition);
    }
}
