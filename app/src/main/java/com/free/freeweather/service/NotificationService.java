package com.free.freeweather.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.free.freeweather.R;
import com.free.freeweather.activity.WeatherActivity;
import com.free.freeweather.gson.Weather;

public class NotificationService extends Service {

    private Weather weather;
    private Context context;

    public NotificationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context.getApplicationContext();

    }

    @Override
    public void onStart(Intent intent, int startId) {
        weather = (Weather) intent.getSerializableExtra("weather");
        CreateInform(weather);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void CreateInform(Weather weather) {
        String cityName = weather.basic.cityName;
        String updateTime = weather.basic.update.updateTime.split(" ")[1];
        String degree = weather.now.temperature + "℃";
        String weatherInfo = weather.now.more.info;

        Intent intent = new Intent(context,WeatherActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(context)
                .setContentText(cityName)
                .setContentText(degree)
                .setSmallIcon(R.mipmap.logo)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1,notification);
    }




    }
