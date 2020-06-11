package com.example.routeplanner.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_routes_table")
public class MyRoute {
    @PrimaryKey
    @NonNull
    private String routeID;
    private String from;
    private String to;
    private boolean isFavorite;

    public MyRoute(String routeID, String from, String to, boolean isFavorite) {
        this.routeID = routeID;
        this.from = from;
        this.to = to;
        this.isFavorite = isFavorite;
    }

    public MyRoute() {
    }

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
