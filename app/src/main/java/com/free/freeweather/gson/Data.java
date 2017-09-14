package com.free.freeweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名： freeweather
 * 包名：   com.free.freeweather.bean
 * 文件名： Data
 * 创建者： 裴周宇
 * 创建时间： 2017/9/13 14:54
 * 描述：   TODO
 */

public class Data {
    @SerializedName("yesterday")
    public Yesterday yesterday;
    @SerializedName("city")
    public String city;
    @SerializedName("aqi")
    public String aqi;
    @SerializedName("forecast")
    public List<Forecast> forecastList = new ArrayList<>();
    @SerializedName("ganmao")
    public String ganmao;
    @SerializedName("wendu")
    public String wendu;

    public Yesterday getYesterday() {
        return yesterday;
    }

    public void setYesterday(Yesterday yesterday) {
        this.yesterday = yesterday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public List<Forecast> getForecastList() {
        return forecastList;
    }

    public void setForecastList(List<Forecast> forecastList) {
        this.forecastList = forecastList;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }
}
