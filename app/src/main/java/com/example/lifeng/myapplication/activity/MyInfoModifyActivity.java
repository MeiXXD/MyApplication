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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.presenter.UserInfoModifyViewPresenter;
import com.example.lifeng.myapplication.utils.InputJudge;

/**
 * @author lifeng
 * @version 1.0 16/7/27
 * @description 我的信息修改界面
 */
public class MyInfoModifyActivity extends AppCompatActivity implements View.OnClickListener, IUserInfoModifyView {
    private ImageView mUserImg;
    private TextView mUserNameTxt;
    private EditText mUserPasswordEdt;
    private EditText mUserIsVipEdt;
    private EditText mUserPhoneEdt;
    private EditText mUserEmailEdt;
    private EditText mUserAddressEdt;
    private Button mCancelModifyBtn;
    private Button mSubmitModiyfBtn;

    private UserInfoModifyViewPresenter mUserInfoModifyViewPresenter;
    private UserBean mUserBean;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_info_modify_activity);
        setTitle("我的信息修改");

        init();
    }

    void init() {
        mUserImg = (ImageView) findViewById(R.id.img_user_info_image);
        mUserNameTxt = (TextView) findViewById(R.id.txt_user_info_name);
        mUserPasswordEdt = (EditText) findViewById(R.id.edt_user_info_password);
        mUserIsVipEdt = (EditText) findViewById(R.id.edt_user_info_isvip);
        mUserIsVipEdt.setEnabled(false);
        mUserPhoneEdt = (EditText) findViewById(R.id.edt_user_info_phone);
        mUserEmailEdt = (EditText) findViewById(R.id.edt_user_info_email);
        mUserAddressEdt = (EditText) findViewById(R.id.edt_user_info_address);
        mCancelModifyBtn = (Button) findViewById(R.id.btn_user_info_modify_cancel_modify);
        mSubmitModiyfBtn = (Button) findViewById(R.id.btn_user_info_modify_submit_modify);

        mCancelModifyBtn.setOnClickListener(this);
        mSubmitModiyfBtn.setOnClickListener(this);

        Intent intent = getIntent();
        mUserBean = new UserBean();
        mUserInfoModifyViewPresenter = new UserInfoModifyViewPresenter(this);

        mUserBean.setId(intent.getIntExtra("userid", 0));
        mUserBean.setName(intent.getStringExtra("username"));
        mUserBean.setPassword(intent.getStringExtra("userpassword"));

        mUserInfoModifyViewPresenter.getUserInfo(mUserBean);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_user_info_modify_cancel_modify:
                finish();
                break;
            case R.id.btn_user_info_modify_submit_modify:
                if (getInput()) {
                    mUserInfoModifyViewPresenter.modifyUserInfo(mUserBean);
                    Toast.makeText(this, "用户更新更新成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    @Override
    public boolean getInput() {
        boolean result = false;
        String password = mUserPasswordEdt.getText().toString().trim();
        String phone = mUserPhoneEdt.getText().toString().trim();
        String email = mUserEmailEdt.getText().toString().trim();
        String address = mUserAddressEdt.getText().toString().trim();

        if (InputJudge.isInputValid(password) && InputJudge.isInputValid(phone) && InputJudge.isInputValid(email) && InputJudge.isInputValid(address)) {
            if (InputJudge.isEmailValid(email)) {
                mUserBean.setPassword(password);
                mUserBean.setPhone(phone);
                mUserBean.setEmail(email);
                mUserBean.setAddress(address);
                result = true;
            } else {
                Toast.makeText(this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "请正确填写各种信息", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    @Override
    public void setOutput(UserBean userBean) {
        mUserImg.setImageResource(R.drawable.goods);
        mUserNameTxt.setText(userBean.getName());
        mUserPasswordEdt.setText(userBean.getPassword());
        mUserIsVipEdt.setText(userBean.getIsVip() == 1 ? "是" : "否");
        mUserPhoneEdt.setText(userBean.getPhone());
        mUserEmailEdt.setText(userBean.getEmail());
        mUserAddressEdt.setText(userBean.getAddress());
    }
}
