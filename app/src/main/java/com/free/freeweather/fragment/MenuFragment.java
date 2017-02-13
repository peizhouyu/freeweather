package com.free.freeweather.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.free.freeweather.R;
import com.free.freeweather.activity.AboutDeveloperActivity;
import com.free.freeweather.activity.ChooseWeatherActivity;
import com.free.freeweather.activity.SattingActivity;
import com.free.freeweather.adapter.SatAdapter;
import com.free.freeweather.bean.SatBean;

import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends Fragment {

    private TextView titleText;
    private Button backButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<SatBean> dataList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu,container,false);
        titleText = (TextView) view.findViewById(R.id.title_text);
        backButton = (Button) view.findViewById(R.id.back_button);
        listView = (ListView) view.findViewById(R.id.list_view);
        initSatBean();
        titleText.setText("控制中心");
        SatAdapter satAdapter = new SatAdapter(getActivity(),R.layout.one_menu,dataList);
        listView.setAdapter(satAdapter);
        //设置半透明效果
        view.getBackground().setAlpha(150);
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (dataList.get(position).getName()){
                    case "手动选择城市":
                        Intent intentChoose = new Intent(getContext(), ChooseWeatherActivity.class);
                        getActivity().startActivityForResult(intentChoose, 1);
                        break;
                    case "设置":
                        Intent intentSat = new Intent(getContext(), SattingActivity.class);
                        getActivity().startActivity(intentSat);
                        break;
                    case "关于开发者":
                        Intent aboutDeveloperIntent = new Intent(getContext(), AboutDeveloperActivity.class);
                        getActivity().startActivity(aboutDeveloperIntent);
                        break;
                }
            }
        });
    }

    //添加侧滑菜单 具体内容
    private void initSatBean() {

            SatBean chooseCity = new SatBean("手动选择城市", R.drawable.location);
            dataList.add(chooseCity);
            SatBean sating = new SatBean("设置", R.drawable.sat);
            dataList.add(sating);
            SatBean developerInfo = new SatBean("关于开发者", R.drawable.developer);
            dataList.add(developerInfo);
    }





}
