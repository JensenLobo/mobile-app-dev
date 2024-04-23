package edu.uncc.weatherapp.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class City implements Serializable {
    private String name;
    private String state;
    private double lat;
    private double lng;

    public City() {
    }

    public City(String name, String state, double lat, double lng) {
        this.name = name;
        this.state = state;
        this.lat = lat;
        this.lng = lng;
    }

    public City(String name, String state, Double lng, Double lat){

        this.name = name;
        this.state = state;
        this.lng = lng;
        this.lat = lat;
    }

    public City(JSONObject jsonObject) throws JSONException {
        this.name = jsonObject.getString("name");
        this.state = jsonObject.getString("state");
        this.lat = jsonObject.getDouble("latitude");
        this.lng = jsonObject.getDouble("longitude");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString(){
        return name + ", " +state;
    }


}
