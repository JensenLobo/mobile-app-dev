package edu.uncc.weatherapp.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


public class Forecast {
    String startTime, shortForecast, windSpeed, icon;
    Double temp;
    int  humidity;

    public Forecast(){
        //empty constructor
    }

    public Forecast(JSONObject jsonObject) throws JSONException {

        //this.humidity = jsonObject.getString("humidity");
        this.temp = jsonObject.getDouble("temperature");
        this.startTime = jsonObject.getString("startTime");
        this.shortForecast = jsonObject.getString("shortForecast");
        this.windSpeed = jsonObject.getString("windSpeed");

        JSONArray weather = jsonObject.getJSONArray("periods");
        if(weather.length() > 0){
            JSONObject firstWeather = weather.getJSONObject(0);
            this.icon = firstWeather.getString("icon");
        }
    }

    public Forecast(String startTime, String windSpeed, Double temperature, String shortForecast, int humidity, String icon){
        this.startTime = startTime;
        this.windSpeed = windSpeed;
        this.temp = temperature;
        this.shortForecast = shortForecast;
        this.humidity = humidity;
        this.icon = icon;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getShortForecast() {
        return shortForecast;
    }

    public void setShortForecast(String shortForecast) {
        this.shortForecast = shortForecast;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }


    public String toString(){
        return temp + "F";
    }

}
