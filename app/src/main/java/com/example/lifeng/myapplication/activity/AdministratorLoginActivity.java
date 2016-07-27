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
import com.example.lifeng.myapplication.bean.AdministratorBean;
import com.example.lifeng.myapplication.presenter.AdministratorLoginViewPresenter;
import com.example.lifeng.myapplication.utils.InputJudge;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 管理员登录Activity
 */
public class AdministratorLoginActivity extends AppCompatActivity implements IAdministratorLoginView, View.OnClickListener {
    private EditText mAdminNameEdt;
    private EditText mAdminPasswordEdt;
    private Button mAdminLoginBtn;

    private AdministratorLoginViewPresenter mAdministratorLoginViewPresenter;
    private AdministratorBean mAdministratorBean;

    private boolean mIsSuccess;
    private boolean mIsInputValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login_activity);
        setTitle("系统管理员登录");
        init();
    }

    private void init() {
        mIsInputValid = false;
        mIsSuccess = false;
        mAdminNameEdt = (EditText) findViewById(R.id.edt_admin_name);
        mAdminPasswordEdt = (EditText) findViewById(R.id.edt_admin_password);
        mAdminLoginBtn = (Button) findViewById(R.id.btn_admin_login_login);

        mAdminLoginBtn.setOnClickListener(this);

        mAdministratorBean = new AdministratorBean();
        mAdministratorLoginViewPresenter = new AdministratorLoginViewPresenter();
    }

    @Override
    public boolean getAdministratorInput() {
        String mAdminName = mAdminNameEdt.getText().toString().trim();
        String mAdminPassword = mAdminPasswordEdt.getText().toString().trim();
        if (!InputJudge.isInputValid(mAdminName) || !InputJudge.isInputValid(mAdminPassword)) {
            return false;
        }
        mAdministratorBean.setName(mAdminName);
        mAdministratorBean.setPassword(mAdminPassword);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_admin_login_login:
                //得到管理员输入
                mIsInputValid = getAdministratorInput();
                //密码不合法
                if (!mIsInputValid) {
                    Toast.makeText(this, "输入不合法!", Toast.LENGTH_SHORT).show();
                } else {
                    //管理员登录
                    mIsSuccess = mAdministratorLoginViewPresenter.adminLogin(mAdministratorBean);
                    if (mIsSuccess) {
                        //验证通过进入普通用户管理与销售商管理界面
                        //更新状态信息
                        mAdministratorBean.setStatus(1);
                        mAdministratorLoginViewPresenter.updateAdminStatus(mAdministratorBean);
                        Toast.makeText(this, "管理员登录成功!", Toast.LENGTH_SHORT).show();
                        //打开管理界面并传递数据
                        Intent intent = new Intent();
                        intent.setClass(AdministratorLoginActivity.this, SellerUserManagementTabActivity.class);
                        intent.putExtra("adminname", mAdministratorBean.getName());
                        intent.putExtra("adminpassword", mAdministratorBean.getPassword());
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