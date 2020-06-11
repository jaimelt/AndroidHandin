package com.example.routeplanner.model;

import java.util.ArrayList;

public class Country {
    private String name;
    private String code;
    private ArrayList states;

    public Country(String name, String code, ArrayList states) {
        this.name = name;
        this.code = code;
        this.states = states;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList getStates() {
        return states;
    }

    public void setStates(ArrayList states) {
        this.states = states;
    }
}
