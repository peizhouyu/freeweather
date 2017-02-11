package com.free.freeweather;

import com.free.freeweather.util.HttpUtil;
import com.free.freeweather.util.queryAPI;

import org.junit.Test;

import java.io.IOException;

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
    @Test
    public void addition_isCorrect() throws Exception {
        String weatherUrl = "http://www.baidu.com";
        System.out.println("123");
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.print("fail");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.print("success");
                String responseText = response.body().string();
                System.out.print(responseText);
            }
        });
    }
}