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

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.SellerBean;

import java.util.List;

/**
 * @author lifeng
 * @version 1.0 16/7/21
 * @description 销售商列表的Adapter
 */
public class SellersListAdapter extends BaseAdapter {
    private Activity mActivity;
    private List<SellerBean> mSellerBeanList;
    private LayoutInflater mLayoutInflater;

    public SellersListAdapter(Activity activity, List<SellerBean> sellerBeanList) {
        mActivity = activity;
        mSellerBeanList = sellerBeanList;
    }

    @Override
    public int getCount() {
        return mSellerBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSellerBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == mLayoutInflater) {
            mLayoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (null == convertView) {
            convertView = mLayoutInflater.inflate(R.layout.sellers_listitem, null);
        }

        TextView sellerIdTxt = (TextView) convertView.findViewById(R.id.txt_sellers_listitem_sellerid);
        TextView sellerNameTxt = (TextView) convertView.findViewById(R.id.txt_sellers_listitem_sellername);
        TextView sellerPasswordTxt = (TextView) convertView.findViewById(R.id.txt_sellers_listitem_sellepassword);
        TextView sellerStatusTxt = (TextView) convertView.findViewById(R.id.txt_sellers_listitem_sellerstatus);

        SellerBean sellerBean = mSellerBeanList.get(position);
        sellerIdTxt.setText(Integer.toString(sellerBean.getId()));
        sellerNameTxt.setText(sellerBean.getName());
        sellerPasswordTxt.setText(sellerBean.getPassword());
        sellerStatusTxt.setText(Integer.toString(sellerBean.getStatus()));

        return convertView;
    }
}
