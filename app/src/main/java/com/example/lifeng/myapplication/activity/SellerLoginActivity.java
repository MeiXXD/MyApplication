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
import android.widget.Toast;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.SellerBean;
import com.example.lifeng.myapplication.presenter.SellerLoginViewPresenter;
import com.example.lifeng.myapplication.utils.InputJudge;

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
    private boolean mIsInputValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_login_activity);
        setTitle("销售商登录");
        Toast.makeText(this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
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
        mIsInputValid = false;
    }

    @Override
    public boolean getSellerInput() {
        String mSellerName = mSellerNameEdt.getText().toString().trim();
        String mSellerPassword = mSellerPasswordEdt.getText().toString().trim();
        if (!InputJudge.isInputValid(mSellerName) || !InputJudge.isInputValid(mSellerPassword)) {
            return false;
        }
        mSellerBean.setName(mSellerName);
        mSellerBean.setPassword(mSellerPassword);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_seller_login_login:
                mIsInputValid = getSellerInput();
                if (!mIsInputValid) {
                    Toast.makeText(this, "输入不合法!", Toast.LENGTH_SHORT).show();
                } else {
                    mIsSuccess = mSellerLoginViewPresenter.sellerLogin(mSellerBean);
                    if (mIsSuccess) {
                        Toast.makeText(this, "销售商登录成功!", Toast.LENGTH_SHORT).show();
                        mSellerBean.setStatus(1);
                        //更新登录状态
                        mSellerLoginViewPresenter.updateSellerStatus(mSellerBean);

                        Intent intent = new Intent();
                        intent.setClass(SellerLoginActivity.this, GoodsOrdersManagementTabActivity.class);
                        intent.putExtra("sellerid", mSellerBean.getId());
                        intent.putExtra("sellername", mSellerBean.getName());
                        intent.putExtra("sellerpassword", mSellerBean.getPassword());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "用户名或密码错误!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}