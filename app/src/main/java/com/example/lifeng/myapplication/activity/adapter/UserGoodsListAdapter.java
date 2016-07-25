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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.presenter.UserGoodsViewPresenter;

import java.util.ArrayList;

/**
 * @author txt_user_goods_goods_brief_description
 * @version 1.0 16/7/24
 * @description 用户登录后, 商品界面的Adapter
 */
public class UserGoodsListAdapter extends BaseAdapter {
    private Activity mActivity;
    private ArrayList<GoodsBean> mGoodsBeanArrayList;
    private LayoutInflater mLayoutInflater;
    private UserGoodsViewPresenter mUserGoodsViewPresenter;

    public UserGoodsListAdapter(Activity activity, UserBean userBean, ArrayList<GoodsBean> goodsBeanArrayList) {
        mActivity = activity;
        mGoodsBeanArrayList = goodsBeanArrayList;
        mUserGoodsViewPresenter = new UserGoodsViewPresenter(userBean);
    }

    @Override
    public int getCount() {
        return mGoodsBeanArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mGoodsBeanArrayList.get(position);
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
            convertView = mLayoutInflater.inflate(R.layout.user_goods_listitem, null);
        }

        ImageView goodsImg = (ImageView) convertView.findViewById(R.id.img_user_goods_goods_image);
        TextView goodsNameTxt = (TextView) convertView.findViewById(R.id.txt_user_goods_goods_name);
        TextView goodsBriefDescription = (TextView) convertView.findViewById(R.id.txt_user_goods_goods_brief_description);
        TextView goodsPirceTxt = (TextView) convertView.findViewById(R.id.txt_user_goods_goods_price);
        Button goodsAddToShoppingCartBtn = (Button) convertView.findViewById(R.id.btn_user_goods_add_to_shopping_cart);

        final GoodsBean goodsBean = mGoodsBeanArrayList.get(position);
        goodsImg.setImageResource(R.drawable.goods);
        goodsNameTxt.setText(goodsBean.getName());
        goodsBriefDescription.setText("简单描述: " + goodsBean.getBriefDescription());
        goodsPirceTxt.setText("价格(元): " + Double.toString(goodsBean.getPrice()));

        goodsAddToShoppingCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserGoodsViewPresenter.addToShoppingCart(goodsBean);
                Toast.makeText(mActivity, "已加入购物车", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
