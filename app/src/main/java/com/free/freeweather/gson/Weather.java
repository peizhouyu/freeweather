package com.free.freeweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 裴周宇 on 2017/2/8.
 */

public class Weather {

    public String status;

    public Basic basic;

    public  AQI aqi;

    public Now now;

    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecasts;
}
