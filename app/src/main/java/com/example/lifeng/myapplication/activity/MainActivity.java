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

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.model.MyDatabaseHelper;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 程序主界面入口
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mUserLoginBtn;
    private Button mSellerLoginBtn;
    private Button mAdminLoginBtn;

    private MyDatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //dbhelper实例化
        mDatabaseHelper = MyDatabaseHelper.getInstantce(this);
        init();
    }

    void init() {
        mUserLoginBtn = (Button) findViewById(R.id.btn_user_login);
        mSellerLoginBtn = (Button) findViewById(R.id.btn_seller_login);
        mAdminLoginBtn = (Button) findViewById(R.id.btn_admin_login);

        mUserLoginBtn.setOnClickListener(this);
        mSellerLoginBtn.setOnClickListener(this);
        mAdminLoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_user_login:
                intent.setClass(MainActivity.this, UserLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_seller_login:
                intent.setClass(MainActivity.this, SellerLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_admin_login:
                intent.setClass(MainActivity.this, AdministratorLoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
