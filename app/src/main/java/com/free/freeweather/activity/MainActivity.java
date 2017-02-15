package com.free.freeweather.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.free.freeweather.R;
import com.free.freeweather.db.WeatherCityCode;

import com.free.freeweather.util.HttpUtil;
import com.free.freeweather.util.LocationAssist;
import com.free.freeweather.util.MyProgressDialog;
import com.free.freeweather.util.Utility;
import com.free.freeweather.util.gaoDeSet;
import com.free.freeweather.util.queryAPI;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    //是否允许自动定位标志变量
    private  boolean userGPS = true;



    private ProgressDialog progressDialog;


    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();

    private List<WeatherCityCode> weatherCityCodeList;

    public  String district;

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    private static final int PERMISSON_REQUESTCODE = 0;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置背景图覆盖状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_main);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean isCheckPermission = pref.getBoolean("isCheck_Permission",false);
        if (!isCheckPermission){
            Log.d("pei","1111111111");
            editor = pref.edit();
            editor.putBoolean("isCheck_Permission",true);
            editor.apply();
            checkPermissions(needPermissions);
        }else {
            Log.d("pei","2222222222");
            //调用高德API实现定位服务
            Toast.makeText(this, "正在自动定位，请稍后...", Toast.LENGTH_LONG).show();
            initLocation();
            startLocation();


            //显示环形进度条
            showProgressDialog();
        }






     }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSON_REQUESTCODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //调用高德API实现定位服务
                    Toast.makeText(this, "正在自动定位，请稍后...", Toast.LENGTH_LONG).show();
                    initLocation();
                    startLocation();


                    //显示环形进度条
                    showProgressDialog();
                }else {
                    Toast.makeText(this,"权限获取失败",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }

    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        //设置定位参数
        locationClient.setLocationOption(gaoDeSet.getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }



    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc) {
                //得到结果后即关闭定位刷新
                stopLocation();
                System.out.println("得到位置信息");

                //解析定位结果
                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
                //获取SDK返回的区名（eg.  兴县）

                 district= loc.getDistrict();
                Log.d("pei","SDK返回的区域信息："+district);

                queryWeatherCodeByDistrict(district);
                //queryWeatherCodeByDistrict(district);
            } else {
                Toast.makeText(MainActivity.this,"fail",Toast.LENGTH_LONG).show();
                Log.d("pei","定位失败");
            }
        }
    };

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

            Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
            intent.putExtra("weather_id", weatherId);
            startActivity(intent);
            finish();
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(MainActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            queryWeatherCodeByDistrict(district);

                        }
                    });
                }
            }
        });
    }


    /**
     * 开始定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private void startLocation(){

        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private void stopLocation(){
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private void destroyLocation(){
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }




    /**
     * 显示进度对话框
     */
    private void showProgressDialog() {

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在获取您的位置信息...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }


    /**
     * 关闭进度对话框
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }






}