package com.example.routeplanner.model;

public class Route {
    private String routeID;
    private String from;
    private String to;

    public Route() {

    }

    public Route(String routeID, String from, String to) {
        this.routeID = routeID;
        this.from = from;
        this.to = to;
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
}
