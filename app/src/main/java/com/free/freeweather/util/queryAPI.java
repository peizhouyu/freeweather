package com.free.freeweather.util;

/**
 * Created by 裴周宇 on 2017/2/5.
 */


//天气查询API
public class queryAPI {
    public static final String query1 = "http://guolin.tech/api/china/";
    //获取必应每日一图
    public static final String getBingyingImg = "http://guolin.tech/api/bing_pic";

    //和风天气网获取城市名称和天气预报码对应关系的json
    //已经被弃用  测试时间 2017/9/13
    public static final String getWeatherCityCodeUrl = "http://files.heweather.com/china-city-list.json";


    //最新API
    public static final String queryRes = "http://wthrcdn.etouch.cn/weather_mini?city=";
}
