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

package com.example.lifeng.myapplication.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.bean.OrderBean;
import com.example.lifeng.myapplication.bean.ShoppingCartBean;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.presenter.UserSubmitOrderViewPresenter;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lifeng
 * @version 1.0 16/7/26
 * @description 用户提交订单activity
 */
public class UserSubmitOrderActivity extends AppCompatActivity implements View.OnClickListener, IUserSubmitOrderView {
    private TextView mUserNameTxt;
    private TextView mUserPhoneTxt;
    private TextView mUserAddressTxt;

    private ImageView mGoodsImg;
    private TextView mGoodsNameTxt;
    private TextView mGoodsPriceTxt;
    private TextView mGoodsAmounts;

    private TextView mAccountTxt;
    private TextView mUserDiscountTxt;
    private TextView mFreightTxt;
    private TextView mDiscountAccountTxt;

    private Button mCancelOrderBtn;
    private Button mSubmitOrderBtn;

    private ShoppingCartBean mShoppingCartBean;
    private UserBean mUserBean;
    private GoodsBean mGoodsBean;
    private OrderBean mOrderBean;

    private UserSubmitOrderViewPresenter mUserSubmitOrderViewPresenter;

    private double mAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_submit_order_activity);
        setTitle("下订单");

        init();
    }

    void init() {
        mUserNameTxt = (TextView) findViewById(R.id.txt_submit_order_user_name);
        mUserPhoneTxt = (TextView) findViewById(R.id.txt_submit_order_user_phone);
        mUserAddressTxt = (TextView) findViewById(R.id.txt_submit_order_user_address);

        mGoodsImg = (ImageView) findViewById(R.id.img_submit_order_goods_image);
        mGoodsNameTxt = (TextView) findViewById(R.id.txt_submit_order_goods_name);
        mGoodsPriceTxt = (TextView) findViewById(R.id.txt_submit_order_goods_price);
        mGoodsAmounts = (TextView) findViewById(R.id.txt_submit_order_amounts);

        mAccountTxt = (TextView) findViewById(R.id.txt_submit_order_account);
        mUserDiscountTxt = (TextView) findViewById(R.id.txt_submit_order_discount);
        mFreightTxt = (TextView) findViewById(R.id.txt_submit_order_freight);
        mDiscountAccountTxt = (TextView) findViewById(R.id.txt_submit_order_discount_account);

        mCancelOrderBtn = (Button) findViewById(R.id.btn_cancel_order);
        mSubmitOrderBtn = (Button) findViewById(R.id.btn_submit_order);
        mCancelOrderBtn.setOnClickListener(this);
        mSubmitOrderBtn.setOnClickListener(this);

        mUserBean = new UserBean();
        mGoodsBean = new GoodsBean();
        mOrderBean = new OrderBean();
        mShoppingCartBean = new ShoppingCartBean(mUserBean, mGoodsBean);
        mUserSubmitOrderViewPresenter = new UserSubmitOrderViewPresenter(this);

        getIntentInfo();
        mUserSubmitOrderViewPresenter.getShoppingCartOrder(mShoppingCartBean);
    }

    void getIntentInfo() {
        Intent intent = getIntent();
        int shoppingcartid = intent.getIntExtra("shoppingcartid", 0);
        mShoppingCartBean.setId(shoppingcartid);
    }

    @Override
    public void setOutput(ShoppingCartBean shoppingCartBean) {
        //从shoppingCartBean中获取信息填界面
        UserBean userBean = shoppingCartBean.getUserBean();
        GoodsBean goodsBean = shoppingCartBean.getGoodsBean();
        mUserNameTxt.setText(userBean.getName());
        mUserPhoneTxt.setText(userBean.getPhone());
        mUserAddressTxt.setText(userBean.getAddress());

        String mGoodsImage = goodsBean.getImage();
        if (null == mGoodsImage || mGoodsImage.isEmpty()) {
            mGoodsImg.setImageResource(R.drawable.goods);
        } else {
            Uri uri = Uri.parse(mGoodsImage);
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                mGoodsImg.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                mGoodsImg.setImageResource(R.drawable.goods);
                e.printStackTrace();
            }
        }

        mGoodsNameTxt.setText(goodsBean.getName());
        mGoodsPriceTxt.setText("单价(元):" + Double.toString(goodsBean.getPrice()));
        mGoodsAmounts.setText("x" + Integer.toString(shoppingCartBean.getAmounts()));

        mAccountTxt.setText(Double.toString(goodsBean.getPrice() * shoppingCartBean.getAmounts()));

        if (userBean.getIsVip() == 1) {
            mUserDiscountTxt.setText("9折");
            mFreightTxt.setText("0");
            mAccount = new BigDecimal(goodsBean.getPrice() * shoppingCartBean.getAmounts() * 0.9).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            mDiscountAccountTxt.setText(Double.toString(mAccount));
        } else {
            mUserDiscountTxt.setText("无");
            mFreightTxt.setText("5");
            mAccount = new BigDecimal(goodsBean.getPrice() * shoppingCartBean.getAmounts() + 5).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            mDiscountAccountTxt.setText(Double.toString(mAccount));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel_order:
                finish();
                break;
            case R.id.btn_submit_order:
                int goodsAmounts = mGoodsBean.getAmounts();
                int orderAmounts = mShoppingCartBean.getAmounts();
                if (goodsAmounts >= orderAmounts) {
                    if (null == mShoppingCartBean.getUserBean().getPhone() || mShoppingCartBean.getUserBean().getPhone().isEmpty() || null == mShoppingCartBean.getUserBean().getAddress() || mShoppingCartBean.getUserBean().getAddress().isEmpty()) {
                        new AlertDialog.Builder(this).setTitle("警告").setMessage("请前往\"资料修改\"页面,完善电话和收货地址等信息").setPositiveButton("确定", null).show();
                    } else {
                        mOrderBean.setUserId(mShoppingCartBean.getUserBean().getId());
                        mOrderBean.setPhone(mShoppingCartBean.getUserBean().getPhone());
                        mOrderBean.setAddress(mShoppingCartBean.getUserBean().getAddress());

                        mOrderBean.setSellerId(mShoppingCartBean.getGoodsBean().getSellerBean().getId());
                        mOrderBean.setSellerName(mShoppingCartBean.getGoodsBean().getSellerBean().getName());

                        mOrderBean.setGoodsId(mShoppingCartBean.getGoodsBean().getId());
                        mOrderBean.setGoodsName(mShoppingCartBean.getGoodsBean().getName());
                        mOrderBean.setGoodsPrice(mShoppingCartBean.getGoodsBean().getPrice());
                        mOrderBean.setGoodsImage(mShoppingCartBean.getGoodsBean().getImage());

                        mOrderBean.setAmounts(orderAmounts);

                        mOrderBean.setAccount(mAccount);
                        mOrderBean.setStatus(0);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        mOrderBean.setDateTime(dateFormat.format(new Date()));
                        mUserSubmitOrderViewPresenter.submitOrder(mOrderBean);
                        mUserSubmitOrderViewPresenter.updateShoppingCart(mShoppingCartBean);
                        mGoodsBean.setAmounts(-orderAmounts);
                        mUserSubmitOrderViewPresenter.updateGoodsAmounts(mGoodsBean);
                        finish();
                        Toast.makeText(this, "添加订单成功", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UserSubmitOrderActivity.this, "库存不足", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
