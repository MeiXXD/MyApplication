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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lifeng.myapplication.R;

/**
 * @author lifeng
 * @version 1.0 16/7/28
 * @description 订单详情Activity
 */
public class OrderDetails extends AppCompatActivity {
    private TextView mOrderIdTxt;
    private TextView mOrderUserNameTxt;
    private TextView mOrderUserPhoneTxt;
    private TextView mOrderUserAddressTxt;
    private ImageView mOrderGoodsImg;
    private TextView mOrderGoodsNameTxt;
    private TextView mOrderGoodsPriceTxt;
    private TextView mOrderGoodsAmountsTxt;
    private TextView mOrderAccountTxt;
    private TextView mOrderDateTimeTxt;
    private TextView mOrderStatusTxt;
    private Button mBackBtn;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details_activity);
        setTitle("订单详情");

        init();
    }

    void init() {
        mOrderIdTxt = (TextView) findViewById(R.id.txt_order_details_order_id);
        mOrderUserNameTxt = (TextView) findViewById(R.id.txt_order_details_user_name);
        mOrderUserPhoneTxt = (TextView) findViewById(R.id.txt_order_details_user_phone);
        mOrderUserAddressTxt = (TextView) findViewById(R.id.txt_order_details_user_address);
        mOrderGoodsImg = (ImageView) findViewById(R.id.img_order_details_goods_image);
        mOrderGoodsNameTxt = (TextView) findViewById(R.id.txt_order_details_goods_name);
        mOrderGoodsPriceTxt = (TextView) findViewById(R.id.txt_order_detais_goods_price);
        mOrderGoodsAmountsTxt = (TextView) findViewById(R.id.txt_order_details_goods_amounts);
        mOrderAccountTxt = (TextView) findViewById(R.id.txt_order_details_order_account);
        mOrderDateTimeTxt = (TextView) findViewById(R.id.txt_order_details_order_datetime);
        mOrderStatusTxt = (TextView) findViewById(R.id.txt_order_details_order_status);
        mBackBtn = (Button) findViewById(R.id.btn_order_details_back);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        mOrderIdTxt.setText(Integer.toString(intent.getIntExtra("orderid", 0)));
        mOrderUserNameTxt.setText(intent.getStringExtra("orderusername"));
        mOrderUserPhoneTxt.setText(intent.getStringExtra("orderuserphone"));
        mOrderUserAddressTxt.setText(intent.getStringExtra("orderuseraddress"));
        mOrderGoodsImg.setImageResource(R.drawable.goods);
        mOrderGoodsNameTxt.setText(intent.getStringExtra("ordergoodsname"));
        mOrderGoodsPriceTxt.setText(Double.toString(intent.getDoubleExtra("ordergoodsprice", 0)));
        mOrderGoodsAmountsTxt.setText(Integer.toString(intent.getIntExtra("ordergoodsamounts", 0)));
        mOrderAccountTxt.setText(Double.toString(intent.getDoubleExtra("orderaccount", 0)));
        mOrderDateTimeTxt.setText(intent.getStringExtra("orderdatetime"));

        int orderstatus = intent.getIntExtra("orderstatus", 0);
        if (0 == orderstatus) {
            mOrderStatusTxt.setText("等待卖家确认");
        } else if (1 == orderstatus) {
            mOrderStatusTxt.setText("确认发货");
        } else {
            mOrderStatusTxt.setText("订单驳回");
        }
    }
}
