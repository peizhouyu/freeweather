package com.free.freeweather;

import com.free.freeweather.db.WeatherCityCode;
import com.free.freeweather.util.HttpUtil;
import com.free.freeweather.util.queryAPI;

import org.junit.Test;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private List<WeatherCityCode> weatherCityCodeList;
    @Test
    public void test() throws Exception {
        weatherCityCodeList  = DataSupport.findAll(WeatherCityCode.class);
        for (int i = 0;i < weatherCityCodeList.size(); i++){
            System.out.println(weatherCityCodeList.get(i).getCityZh());
        }

    }

}