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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.UserBean;

/**
 * @author lifeng
 * @version 1.0 16/7/24
 * @description 用户 "我的" 页面
 */
public class UserMyActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mUserInfoModifyBtn;
    private Button mOrderManagementBtn;

    private UserBean mUserBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_my_activity);
        setTitle("我的");

        init();
    }

    void init() {
        mUserInfoModifyBtn = (Button) findViewById(R.id.btn_my_userinfo_modify);
        mOrderManagementBtn = (Button) findViewById(R.id.btn_my_order_management);

        mUserInfoModifyBtn.setOnClickListener(this);
        mOrderManagementBtn.setOnClickListener(this);

        mUserBean = new UserBean();
        Intent intent = getIntent();
        mUserBean.setId(intent.getIntExtra("userid", 0));
        mUserBean.setName(intent.getStringExtra("username"));
        mUserBean.setPassword(intent.getStringExtra("userpassword"));
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_my_userinfo_modify:
                intent.putExtra("userid", mUserBean.getId());
                intent.putExtra("username", mUserBean.getName());
                intent.putExtra("userpassword", mUserBean.getPassword());
                intent.setClass(this, MyInfoModifyActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_my_order_management:
                intent.putExtra("userid", mUserBean.getId());
                intent.putExtra("username", mUserBean.getName());
                intent.putExtra("userpassword", mUserBean.getPassword());
                intent.setClass(this, MyOrderManagementActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(UserMyActivity.this).setTitle("注意").setMessage("请退出登录").setPositiveButton("确定", null).show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
