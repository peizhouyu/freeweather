package com.free.freeweather.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Switch;
import android.widget.Toast;

import com.free.freeweather.R;

public class SattingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private Switch aSwitch1;
    private Switch aSwitch2;
    private Switch aSwitch3;
    private Switch aSwitch4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satting);

        initView();
        setListener();

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.switch1:
                if (!isChecked){
                    Toast.makeText(this,"自动定位服务已关闭",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"自动定位服务已打开",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.switch2:
                if (!isChecked){
                    Toast.makeText(this,"后台服务已关闭",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"后台服务已启动",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.switch3:
                if (!isChecked){
                    Toast.makeText(this,"开机启动已关闭",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"开机启动已打开",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.switch4:
                if (!isChecked){
                    Toast.makeText(this,"卧槽，什么鬼",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"洗洗睡吧",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    private void setListener(){
        aSwitch1.setOnCheckedChangeListener(this);
        aSwitch2.setOnCheckedChangeListener(this);
        aSwitch3.setOnCheckedChangeListener(this);
        aSwitch4.setOnCheckedChangeListener(this);
    };

    private void initView(){
        aSwitch1 = (Switch) findViewById(R.id.switch1);
        aSwitch2 = (Switch) findViewById(R.id.switch2);
        aSwitch3 = (Switch) findViewById(R.id.switch3);
        aSwitch4 = (Switch) findViewById(R.id.switch4);
    }


}
