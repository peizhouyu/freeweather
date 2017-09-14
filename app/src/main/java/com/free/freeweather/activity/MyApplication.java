package com.free.freeweather.activity;

import android.content.Intent;
import android.util.Log;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.common.UmLog;

import org.litepal.LitePalApplication;





/**
 * Created by 裴周宇 on 2017/2/21.
 */

public class MyApplication extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.d("mytoken", deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.d("mytoken", "faild");
            }
        });
    }
}
