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
import com.example.lifeng.myapplication.bean.GoodsBean;

import java.util.List;

/**
 * @author lifeng
 * @version 1.0 16/7/22
 * @description 商品管理列表的Adapter
 */
public class GoodsManagementListAdapter extends BaseAdapter {
    private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private List<GoodsBean> mGoodsBeanList;

    public GoodsManagementListAdapter(Activity activity, List<GoodsBean> goodsBeanList) {
        mActivity = activity;
        mGoodsBeanList = goodsBeanList;
    }

    @Override
    public int getCount() {
        return mGoodsBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mGoodsBeanList.get(position);
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
            convertView = mLayoutInflater.inflate(R.layout.goods_management_listitem, null);
        }

        ImageView goodsImageView = (ImageView) convertView.findViewById(R.id.img_goods_listitem_image);
        TextView goodsNameTxt = (TextView) convertView.findViewById(R.id.txt_goods_listview_name);
        TextView goodsPriceTxt = (TextView) convertView.findViewById(R.id.txt_goods_listview_price);
        TextView goodsAmountTxt = (TextView) convertView.findViewById(R.id.txt_goods_listview_amount);
        TextView goodsKindTxt = (TextView) convertView.findViewById(R.id.txt_goods_listview_kind);

        GoodsBean goodsBean = mGoodsBeanList.get(position);
        //显示默认图片
        goodsImageView.setImageResource(R.drawable.goods);
        goodsNameTxt.setText(goodsBean.getName());
        goodsPriceTxt.setText("价格(元) : " + Double.toString(goodsBean.getPrice()));
        goodsAmountTxt.setText("库存(件) : " + Integer.toString(goodsBean.getAmounts()));
        goodsKindTxt.setText(goodsBean.getKind());

        return convertView;
    }
}
