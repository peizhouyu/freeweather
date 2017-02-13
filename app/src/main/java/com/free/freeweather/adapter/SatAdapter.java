package com.free.freeweather.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.free.freeweather.R;
import com.free.freeweather.bean.SatBean;

import java.util.List;

/**
 * Created by 裴周宇 on 2017/2/13.
 */

public class SatAdapter extends ArrayAdapter<SatBean> {

    private int resourceId;

    public SatAdapter(Context context, int textViewResourceId, List<SatBean> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SatBean setBean = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView menuImg = (ImageView) view.findViewById(R.id.menu_img);
        TextView menuText = (TextView) view.findViewById(R.id.menu_text);
        menuImg.setImageResource(setBean.getImageId());
        menuText.setText(setBean.getName());
        return view;
    }


}
