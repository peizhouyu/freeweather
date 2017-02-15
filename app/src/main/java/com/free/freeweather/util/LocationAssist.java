package com.free.freeweather.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.free.freeweather.activity.MainActivity;
import com.free.freeweather.activity.WeatherActivity;
import com.free.freeweather.db.WeatherCityCode;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 裴周宇 on 2017/2/15.
 *
 * 定位辅助类
 */

public class LocationAssist {

    private String district;

    private List<WeatherCityCode> weatherCityCodeList;
    private Activity activity;

    public LocationAssist(Activity activity) {
        this.activity = activity;
    }

    /**
     * 根据GPS获得的区名去查询相应的天气预报码。
     *
     */
    public void queryWeatherCodeByDistrict(String result){
        Log.d("pei","根据GPS获得的区名去查询相应的天气预报码");
        Log.d("pei","result的值为："+result);
        district = result;
        // System.out.println(weatherCityCodeList.size());
        weatherCityCodeList  = DataSupport.where("cityZh=?",district).find(WeatherCityCode.class);
        Log.d("pei","根据名称查询出来的数据大小"+weatherCityCodeList.size());
        System.out.println("sadasdasd");
//        System.out.println(weatherCityCodeList.get(0).getCityZh());
        // Log.d("zhouyu","根据名称查询出来的id"+weatherCityCodeList.get(1).getCityZh());
        //String address = queryAPI.getWeatherCityCodeUrl;
        //queryFromServer(address);
        if (weatherCityCodeList.size() > 0){
            String weatherId = weatherCityCodeList.get(0).getWeatherCityId();

            Intent intent = new Intent(activity, WeatherActivity.class);
            intent.putExtra("weather_id", weatherId);
            activity.startActivity(intent);
            activity.finish();
        }else {
            //数据库无缓存 从服务获取json对应码
            String address = queryAPI.getWeatherCityCodeUrl;
            queryFromServer(address);
        }
    }

    //从服务器查询json对应码
    private void queryFromServer(final String address){
        Log.d("pei","从服务器查询json对应码");
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(activity, "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                boolean resultOk = false;
                String responseText = response.body().string();
                resultOk = Utility.handleWeatherCodeResponse(responseText);
                Log.d("pei","执行到了handleWeatherCodeResponse");
                if (resultOk){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            queryWeatherCodeByDistrict(district);

                        }
                    });
                }
            }
        });
    }
}
