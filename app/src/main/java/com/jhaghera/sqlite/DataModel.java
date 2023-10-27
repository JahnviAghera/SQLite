package com.jhaghera.sqlite;

public class DataModel {
    private int id;
    private String f_name;
    private String l_name;

    // Constructors, getters, and setters

    public DataModel() {
        // Default constructor
    }

    public DataModel(int id, String name, String age) {
        this.id = id;
        this.f_name = name;
        this.l_name = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }
}

