package com.example.routeplanner.model;

import java.util.ArrayList;

public class Countries {
    private int statusCode;
    private ArrayList<Country> result;
    private ArrayList extra;

    public Countries(int statusCode, ArrayList<Country> result, ArrayList extra) {
        this.statusCode = statusCode;
        this.result = result;
        this.extra = extra;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ArrayList<Country> getResult() {
        return result;
    }

    public void setResult(ArrayList<Country> result) {
        this.result = result;
    }

    public ArrayList getExtra() {
        return extra;
    }

    public void setExtra(ArrayList extra) {
        this.extra = extra;
    }
}
