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
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.presenter.UserLoginViewPresenter;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 普通用户登录Acitivity
 */
public class UserLoginActivity extends AppCompatActivity implements IUserLoginView, View.OnClickListener {
    private EditText mUserNameEdt;
    private EditText mUserPasswordEdt;
    private Button mUserLoginBtn;

    private UserBean mUserBean;
    private UserLoginViewPresenter mUserLoginViewPresenter;

    private boolean mIsSuccess;
    private boolean mIsInputValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_activity);
        setTitle("普通用户登录");
        Toast.makeText(this, "请输入用户名和密码", Toast.LENGTH_LONG).show();
        init();
    }

    void init() {
        mIsSuccess = false;
        mIsInputValid = false;
        mUserNameEdt = (EditText) findViewById(R.id.edt_user_name);
        mUserPasswordEdt = (EditText) findViewById(R.id.edt_user_password);
        mUserLoginBtn = (Button) findViewById(R.id.btn_user_login_login);

        mUserLoginBtn.setOnClickListener(this);

        mUserBean = new UserBean();
        mUserLoginViewPresenter = new UserLoginViewPresenter();
    }

    @Override
    public boolean getUserInput() {
        String mUserName = mUserNameEdt.getText().toString().trim();
        String mUserPassword = mUserPasswordEdt.getText().toString().trim();
        if (mUserPassword.contains("\'") || mUserPassword.contains("\"") || mUserPassword.isEmpty()) {
            return false;
        }
        mUserBean.setName(mUserName);
        mUserBean.setPassword(mUserPassword);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_user_login_login:
                mIsInputValid = getUserInput();
                if (!mIsInputValid) {
                    Toast.makeText(this, "输入不合法!", Toast.LENGTH_SHORT).show();
                } else {
                    mIsSuccess = mUserLoginViewPresenter.userLogin(mUserBean);
                    if (mIsSuccess) {
                        Toast.makeText(this, "普通用户登录成功!", Toast.LENGTH_SHORT).show();
                        mUserBean.setStatus(1);
                        mUserLoginViewPresenter.updateUserStatus(mUserBean);
                        Intent intent = new Intent();
                        // TODO: 16/7/19 登录成功启动的界面待补
                        intent.setClass(UserLoginActivity.this, null);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "用户名或密码错误!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}