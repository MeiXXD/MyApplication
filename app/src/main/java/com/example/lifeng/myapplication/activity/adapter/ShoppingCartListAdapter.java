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
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.bean.ShoppingCartBean;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/25
 * @description 购物车列表adapter
 */
public class ShoppingCartListAdapter extends BaseAdapter {
    private int mSelectedIndex;
    private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private ArrayList<ShoppingCartBean> mShoppingCartBeanArrayList;

    public ShoppingCartListAdapter(Activity activity, ArrayList<ShoppingCartBean> shoppingCartBeanArrayList) {
        mActivity = activity;
        mShoppingCartBeanArrayList = shoppingCartBeanArrayList;
        mSelectedIndex = -1;
    }

    public void setSelectedIndex(int index) {
        mSelectedIndex = index;
    }

    @Override
    public int getCount() {
        return mShoppingCartBeanArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mShoppingCartBeanArrayList.get(position);
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
            convertView = mLayoutInflater.inflate(R.layout.shopping_cart_listitem, null);
        }

        RadioButton shoppingCartRadBtn = (RadioButton) convertView.findViewById(R.id.radbtn_shopping_cart);
        ImageView goodsImg = (ImageView) convertView.findViewById(R.id.img_shopping_cart_goods_image);
        TextView goodsNameTxt = (TextView) convertView.findViewById(R.id.txt_shopping_cart_goods_name);
        TextView goodsPriceTxt = (TextView) convertView.findViewById(R.id.txt_shopping_cart_goods_price);
        TextView goodsAmountsTxt = (TextView) convertView.findViewById(R.id.txt_shopping_cart_amounts);

        ShoppingCartBean shoppingCartBean = mShoppingCartBeanArrayList.get(position);
        GoodsBean goodsBean = shoppingCartBean.getGoodsBean();

        String mGoodsImage = goodsBean.getImage();
        if (null == mGoodsImage || mGoodsImage.isEmpty()) {
            goodsImg.setImageResource(R.drawable.goods);
        } else {
            Uri uri = Uri.parse(mGoodsImage);
            ContentResolver cr = mActivity.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                goodsImg.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                goodsImg.setImageResource(R.drawable.goods);
                e.printStackTrace();
            }
        }

        goodsNameTxt.setText(goodsBean.getName());
        goodsPriceTxt.setText("价格(元): " + Double.toString(goodsBean.getPrice()));
        goodsAmountsTxt.setText("x " + Integer.toString(shoppingCartBean.getAmounts()));

        if (mSelectedIndex == position) {
            shoppingCartRadBtn.setChecked(true);
        } else {
            shoppingCartRadBtn.setChecked(false);
        }

        return convertView;
    }

}
