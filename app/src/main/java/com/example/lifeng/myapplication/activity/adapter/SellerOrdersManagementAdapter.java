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
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.OrderBean;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/28
 * @description 销售商订单管理Adapter
 */
public class SellerOrdersManagementAdapter extends BaseAdapter {
    private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private ArrayList<OrderBean> mOrderBeanArrayList;

    public static SparseBooleanArray mSparseBooleanArray;

    public SellerOrdersManagementAdapter(Activity activity, ArrayList<OrderBean> orderBeanArrayList) {
        mActivity = activity;
        mOrderBeanArrayList = orderBeanArrayList;
        mSparseBooleanArray = new SparseBooleanArray();
        for (int i = 0; i < mSparseBooleanArray.size(); i++) {
            mSparseBooleanArray.put(i, false);
        }
    }

    public static void resetCheckStatus() {
        for (int i = 0; i < mSparseBooleanArray.size(); i++) {
            mSparseBooleanArray.put(i, false);
        }
    }

    public void setItemChecked(int position) {
        if (!mSparseBooleanArray.get(position)) {
            mSparseBooleanArray.put(position, true);
        } else {
            mSparseBooleanArray.put(position, false);
        }
    }

    @Override
    public int getCount() {
        return mOrderBeanArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mOrderBeanArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (null == mLayoutInflater) {
            mLayoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (null == convertView) {
            convertView = mLayoutInflater.inflate(R.layout.seller_orders_management_listitem, null);
        }

        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.cbx_seller_orders_management_order);
        checkBox.setChecked(mSparseBooleanArray.get(position));

        TextView orderId = (TextView) convertView.findViewById(R.id.txt_seller_orders_management_order_id);
        ImageView orderGoodsImg = (ImageView) convertView.findViewById(R.id.img_seller_orders_management_goods_image);
        TextView orderGoodsName = (TextView) convertView.findViewById(R.id.txt_seller_orders_management_goods_name);
        TextView orderGoodsPrice = (TextView) convertView.findViewById(R.id.txt_seller_orders_management_goods_price);
        TextView orderGoodsAmounts = (TextView) convertView.findViewById(R.id.txt_seller_orders_management_goods_amounts);
        TextView orderUserId = (TextView) convertView.findViewById(R.id.txt_seller_orders_management_user_id);
        TextView orderAccount = (TextView) convertView.findViewById(R.id.txt_seller_orders_management_order_account);
        TextView orderDateTime = (TextView) convertView.findViewById(R.id.txt_seller_orders_management_order_datetime);
        TextView orderStatus = (TextView) convertView.findViewById(R.id.txt_seller_orders_management_order_status);

        OrderBean orderBean = mOrderBeanArrayList.get(position);
        orderId.setText(Integer.toString(orderBean.getId()));
        orderGoodsImg.setImageResource(R.drawable.goods);
        orderGoodsName.setText(orderBean.getGoodsName());
        orderGoodsPrice.setText(Double.toString(orderBean.getGoodsPrice()));
        orderGoodsAmounts.setText(Integer.toString(orderBean.getAmounts()));
        orderUserId.setText(Integer.toString(orderBean.getUserId()));
        orderAccount.setText(Double.toString(orderBean.getAccount()));
        orderDateTime.setText(orderBean.getDateTime());

        int orderstatus = orderBean.getStatus();
        if (0 == orderstatus) {
            orderStatus.setText("等待卖家确认");
        } else if (1 == orderstatus) {
            orderStatus.setText("确认发货");
        } else {
            orderStatus.setText("订单驳回");
        }


        return convertView;
    }
}
