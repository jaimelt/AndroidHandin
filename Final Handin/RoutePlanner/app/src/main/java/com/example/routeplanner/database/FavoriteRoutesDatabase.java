package com.example.routeplanner.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.routeplanner.model.MyRoute;

@Database(entities = {MyRoute.class}, version = 1)
public abstract class FavoriteRoutesDatabase extends RoomDatabase {
    private static FavoriteRoutesDatabase instance;

    public static synchronized FavoriteRoutesDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FavoriteRoutesDatabase.class, "routes_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract FavoriteRoutesDAO getDAO();
}
