package com.example.routeplanner.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.routeplanner.model.MyRoute;

import java.util.List;

@Dao
public interface FavoriteRoutesDAO {

    @Insert
    void insert(MyRoute route);

    @Delete
    void delete(MyRoute route);

    @Query("SELECT * FROM favorite_routes_table")
    LiveData<List<MyRoute>> getFavoriteRoutes();
}
