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
import android.util.Log;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.bean.UserBean;

/**
 * @author lifeng
 * @version 1.0 16/7/24
 * @description 商品详情页面
 */
public class GoodsDetailsActivity extends AppCompatActivity {
    private UserBean mUserBean;
    private int mGoodsId;
    private GoodsBean mGoodsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_details_activity);

        init();
    }

    void init() {
        mUserBean = new UserBean();
        mGoodsBean = new GoodsBean();

        Intent intent = getIntent();
        mGoodsId = intent.getIntExtra("goodsid", 0);
        mUserBean.setId(intent.getIntExtra("userid", 0));
        mUserBean.setName(intent.getStringExtra("username"));
        mUserBean.setPassword(intent.getStringExtra("userpassword"));

        Log.e(">>>>>", Integer.toString(mGoodsId));
        Log.e(">>>>>", Integer.toString(mUserBean.getId()));
        Log.e(">>>>>", mUserBean.getName());
        Log.e(">>>>>", mUserBean.getPassword());
    }
}
