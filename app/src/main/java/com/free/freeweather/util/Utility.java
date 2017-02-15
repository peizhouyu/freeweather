package com.free.freeweather.util;

import android.text.TextUtils;
import android.util.Log;

import com.free.freeweather.db.City;
import com.free.freeweather.db.County;
import com.free.freeweather.db.Province;
import com.free.freeweather.db.WeatherCityCode;
import com.free.freeweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 裴周宇 on 2017/2/5.
 */

public class Utility {
    /*
     *  解析和处理服务器发回的天气预报城市码
     */

    public static boolean handleWeatherCodeResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allCity = new JSONArray(response);
                Log.d("pei","数据长度："+allCity.length());
                for (int i = 0; i < allCity.length(); i++){
                    JSONObject city = allCity.getJSONObject(i);
                    WeatherCityCode weatherCityCode = new WeatherCityCode();
                    weatherCityCode.setWeatherCityId(city.getString("id"));
                    //Log.d("pei",city.getString("id"));
//                    weatherCityCode.setCityEn(city.getString("cityEn"));
                    weatherCityCode.setCityZh(city.getString("cityZh"));
                    Log.d("zhouyu","城市名称："+weatherCityCode.getCityZh());
//                    weatherCityCode.setCountryCode(city.getString("countryCode"));
//                    weatherCityCode.setCountryEn(city.getString("countryEn"));
//                    weatherCityCode.setCountryZh(city.getString("countryZn"));
//                    weatherCityCode.setProvinceEn(city.getString("provinceEn"));
//                    weatherCityCode.setProvinceZh(city.getString("provinceZn"));
//                    weatherCityCode.setLeaderEn(city.getString("leaderEn"));
//                    weatherCityCode.setLeaderZh(city.getString("leaderZn"));
//                    weatherCityCode.setLat(city.getDouble("lat"));
//                    weatherCityCode.setLon(city.getDouble("lon"));
                    weatherCityCode.save();
                    Log.d("pei","第"+i+"条数据存储完成");
                }
                Log.d("pei","数据库存储完成");
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /*
     *  解析和处理服务器发回的省级数据
     */

    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
     *  解析和处理服务器返回的市级数据
     */

    public static boolean handleCityResponse(String response, int proviinceId){
            if (!TextUtils.isEmpty(response)){
                try {
                    JSONArray allCities = new JSONArray(response);
                    for (int i = 0; i < allCities.length(); i++){
                        JSONObject cityObject = allCities.getJSONObject(i);
                        City city = new City();
                        city.setCityName(cityObject.getString("name"));
                        city.setCityCode(cityObject.getInt("id"));
                        city.setProvinceId(proviinceId);
                        city.save();
                    }
                    return true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        return false;
    }

    /*
     *  解析和处理服务器返回的县级数据
     */

    public static boolean handleCountyResponse(String response, int cityId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    //将返回的JSON数据解析成Weather实体类

    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }



}
