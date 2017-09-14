package com.free.freeweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名： FreeWeather
 * 包名：   com.free.freeweather.gson
 * 文件名： Weather
 * 创建者： 裴周宇
 * 创建时间： 2017/9/13 15:23
 * 描述：   TODO
 */

public class Weather {
    @SerializedName("date")
    public Data data;
    public Integer status;
    public String desc;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
