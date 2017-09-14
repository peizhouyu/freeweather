package com.free.freeweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 项目名： freeweather
 * 包名：   com.free.freeweather.bean
 * 文件名： Forecast
 * 创建者： 裴周宇
 * 创建时间： 2017/9/13 14:51
 * 描述：   TODO
 */

public class Forecast {
    @SerializedName("date")
    public String date;
    @SerializedName("high")
    public String high;
    @SerializedName("fengli")
    public String fengli;
    @SerializedName("low")
    public String low;
    @SerializedName("fengxiang")
    public String fengxiang;
    @SerializedName("type")
    public String forecastType;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getFengli() {
        return fengli;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    public String getForecastType() {
        return forecastType;
    }

    public void setForecastType(String forecastType) {
        this.forecastType = forecastType;
    }
}
