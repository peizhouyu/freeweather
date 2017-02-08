package com.free.freeweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 裴周宇 on 2017/2/8.
 */

public class Basic {

    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }

}
