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
import android.widget.EditText;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.SellerBean;
import com.example.lifeng.myapplication.presenter.SellerLoginViewPresenter;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 销售商登录Activity
 */
public class SellerLoginActivity extends AppCompatActivity implements ISellerLoginView, View.OnClickListener {
    private EditText mSellerNameEdt;
    private EditText mSellerPasswordEdt;
    private Button mSellerLoginBtn;

    private SellerBean mSellerBean;
    private SellerLoginViewPresenter mSellerLoginViewPresenter;

    private boolean mIsSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_login_activity);
        setTitle("销售商登录");

        init();
    }

    void init() {
        mSellerNameEdt = (EditText) findViewById(R.id.edt_seller_name);
        mSellerPasswordEdt = (EditText) findViewById(R.id.edt_seller_password);
        mSellerLoginBtn = (Button) findViewById(R.id.btn_seller_login_login);
        mSellerLoginBtn.setOnClickListener(this);

        mSellerBean = new SellerBean();
        mSellerLoginViewPresenter = new SellerLoginViewPresenter();

        mIsSuccess = false;
    }

    @Override
    public boolean getSellerInput() {
        String mSellerName = mSellerNameEdt.getText().toString().trim();
        String mSellerPassword = mSellerPasswordEdt.getText().toString().trim();
        // TODO: 16/7/19 密码是否合法逻辑
        mSellerBean.setName(mSellerName);
        mSellerBean.setPassword(mSellerPassword);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_seller_login_login:
                boolean temp = getSellerInput();
                if (temp) {
                    mIsSuccess = mSellerLoginViewPresenter.sellerLogin(mSellerBean);
                }
                if (mIsSuccess) {
                    Intent intent = new Intent();
                    // TODO: 16/7/19 登录成功启动的界面待补
                    intent.setClass(SellerLoginActivity.this, null);
                    startActivity(intent);
                }
                break;
        }
    }
}