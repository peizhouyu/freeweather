package com.free.freeweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 项目名： freeweather
 * 包名：   com.free.freeweather.bean
 * 文件名： Yesterday
 * 创建者： 裴周宇
 * 创建时间： 2017/9/13 14:49
 * 描述：   TODO
 */

public class Yesterday {

    @SerializedName("date")
    public String date;
    @SerializedName("high")
    public String high;
    @SerializedName("fx")
    public String fx;
    @SerializedName("low")
    public String low;
    @SerializedName("fl")
    public String fl;
    @SerializedName("type")
    public String yesterdayType;

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

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getYesterdayType() {
        return yesterdayType;
    }

    public void setYesterdayType(String yesterdayType) {
        this.yesterdayType = yesterdayType;
    }
}
