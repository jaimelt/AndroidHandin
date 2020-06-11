package com.example.routeplanner.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.routeplanner.database.FavoriteRoutesDAO;
import com.example.routeplanner.database.FavoriteRoutesDatabase;
import com.example.routeplanner.model.MyRoute;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RoutesRepository {
    private static RoutesRepository instance;
    private MutableLiveData<ArrayList<MyRoute>> routes = new MutableLiveData<>();
    private ArrayList<MyRoute> myRoutes = new ArrayList<>();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("routes");
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private FavoriteRoutesDAO routesDAO;
    private LiveData<List<MyRoute>> favoriteRoutes;

    public RoutesRepository(Application application) {
        FavoriteRoutesDatabase database = FavoriteRoutesDatabase.getInstance(application);
        routesDAO = database.getDAO();
        favoriteRoutes = routesDAO.getFavoriteRoutes();
    }

    public static RoutesRepository getInstance(Application application) {
        if (instance == null) {
            instance = new RoutesRepository(application);
        }
        return instance;
    }

    public void addRoute(MyRoute route) {
        if (route != null) {
            String id = databaseReference.push().getKey();

            if (route.isFavorite()) {
                insert(route);
            }

            route.setRouteID(id);
            myRoutes.add(route);

            databaseReference.child(id).setValue(route);
        }
        routes.postValue(myRoutes);
    }

    public LiveData<ArrayList<MyRoute>> getRoutes() {
        isLoading.setValue(true);
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myRoutes.clear();

                for (DataSnapshot routesSnapshot : dataSnapshot.getChildren()) {
                    MyRoute route = routesSnapshot.getValue(MyRoute.class);
                    myRoutes.add(route);
                    routes.setValue(myRoutes);
                }
                isLoading.setValue(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return routes;
    }

    public void removeRoute(int adapterPosition) {
        String id = myRoutes.get(adapterPosition).getRouteID();
        myRoutes.remove(adapterPosition);

        DatabaseReference dRoute = FirebaseDatabase.getInstance().getReference("routes").child(id);

        dRoute.removeValue();
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<List<MyRoute>> getFavRoutes() {
        return favoriteRoutes;
    }

    public void deleteRoute(int adapterPosition) {
        MyRoute myRoute = favoriteRoutes.getValue().get(adapterPosition);
        delete(myRoute);
    }

    public void insert(MyRoute myRoute) {
        new InsertRouteAsync(routesDAO).execute(myRoute);
    }

    public void delete(MyRoute myRoute) {
        new DeleteRouteAsync(routesDAO).execute(myRoute);
    }

    private static class InsertRouteAsync extends AsyncTask<MyRoute, Void, Void> {
        private FavoriteRoutesDAO dao;

        private InsertRouteAsync(FavoriteRoutesDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MyRoute... myRoutes) {
            dao.insert(myRoutes[0]);
            return null;
        }
    }

    private static class DeleteRouteAsync extends AsyncTask<MyRoute, Void, Void> {
        private FavoriteRoutesDAO dao;

        private DeleteRouteAsync(FavoriteRoutesDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MyRoute... myRoutes) {
            dao.delete(myRoutes[0]);
            return null;
        }
    }

}