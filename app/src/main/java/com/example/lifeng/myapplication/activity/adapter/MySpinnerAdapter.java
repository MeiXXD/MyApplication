/*
 * ------------------------------------------------------------------------------------
 * CMB Confidential
 *
 * Copyright (C) 2013 China Merchants Bank Co., Ltd. All rights reserved.
 *
 * No part of this file may be reproduced or transmitted in any form or by any means,
 * electronic, mechanical, photocopying, recording, or otherwise, without prior
 * written permission of China Merchants Bank Co., Ltd.
 * ------------------------------------------------------------------------------------
 */

package com.example.lifeng.myapplication.activity.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/25
 * @description 自定义spinner adapter
 */
public class MySpinnerAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private ArrayList<String> mStringArrayList;

    public MySpinnerAdapter(Context context, int textViewResourceId, String[] objects) {
        super(context, textViewResourceId, objects);
        mContext = context;
        mStringArrayList = new ArrayList<>();
        for (int i = 0; i < objects.length; i++) {
            mStringArrayList.add(objects[i]);
        }
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(mStringArrayList.get(position));
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER);
        tv.setHeight(150);
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(mStringArrayList.get(position));
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER);
        tv.setHeight(150);
        return convertView;
    }
}
