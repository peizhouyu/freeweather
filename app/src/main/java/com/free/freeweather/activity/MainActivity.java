package com.free.freeweather.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.free.freeweather.R;

public class MainActivity extends AppCompatActivity {
    //是否允许自动定位标志变量
    private  boolean userGPS = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserPermission();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getString("weather", null) != null) {
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
            finish();
        }

        //自动获取位置信息
        if (userGPS){

        }


    }

    //动态申请定位权限  wifi移动网络 GPS
    private void getUserPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission_group.LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission_group.LOCATION}, 1);
        }

    }

    //申请结果回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    userGPS = true;
                }else {
                    Toast.makeText(this, "您拒绝了自动定位权限！建议您去设置打开",Toast.LENGTH_LONG).show();
                }
                break;

        }
    }




}