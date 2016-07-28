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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.OrderBean;

import java.util.List;

/**
 * @author lifeng
 * @version 1.0 16/7/27
 * @description 用户订单界面的adapter
 */
public class MyOrdersManagementAdapter extends BaseAdapter {
    private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private List<OrderBean> mOrderBeanList;


    public MyOrdersManagementAdapter(Activity activity, List<OrderBean> orderBeanList) {
        mActivity = activity;
        mOrderBeanList = orderBeanList;
    }

    @Override
    public int getCount() {
        return mOrderBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mOrderBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            mLayoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (null == convertView) {
            convertView = mLayoutInflater.inflate(R.layout.my_orders_listitem, null);
        }

        TextView orderIdTxt = (TextView) convertView.findViewById(R.id.txt_my_order_management_order_id);
        ImageView goodsImg = (ImageView) convertView.findViewById(R.id.img_seller_orders_management_goods_image);
        TextView goodsNameTxt = (TextView) convertView.findViewById(R.id.txt_seller_orders_management_goods_name);
        TextView goodsPriceTxt = (TextView) convertView.findViewById(R.id.txt_seller_orders_management_goods_price);
        TextView amountsTxt = (TextView) convertView.findViewById(R.id.txt_seller_orders_management_goods_amounts);
        TextView accountsTxt = (TextView) convertView.findViewById(R.id.txt_seller_orders_management_order_account);
        TextView orderDateTimeTxt = (TextView) convertView.findViewById(R.id.txt_seller_orders_management_order_datetime);
        TextView orderStatusTxt = (TextView) convertView.findViewById(R.id.txt_seller_orders_management_order_status);

        OrderBean orderBean = mOrderBeanList.get(position);
        orderIdTxt.setText(Integer.toString(orderBean.getId()));
        goodsImg.setImageResource(R.drawable.goods);
        goodsNameTxt.setText(orderBean.getGoodsName());
        goodsPriceTxt.setText(Double.toString(orderBean.getGoodsPrice()));
        amountsTxt.setText(Integer.toString(orderBean.getAmounts()));
        orderDateTimeTxt.setText(orderBean.getDateTime());
        accountsTxt.setText(Double.toString(orderBean.getAccount()));
        int orderstatus = orderBean.getStatus();
        if (0 == orderstatus) {
            orderStatusTxt.setText("等待卖家确认");
        } else if (1 == orderstatus) {
            orderStatusTxt.setText("确认发货");
        } else {
            orderStatusTxt.setText("订单驳回");
        }

        return convertView;
    }
}
