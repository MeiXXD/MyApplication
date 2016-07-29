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

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.activity.adapter.UsersListAdapter;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.presenter.UserManagementViewPresenter;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 用户管理Activity
 */
public class UserManagementActivity extends AppCompatActivity implements IUserManagementView, View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    private ListView mUsersLv;
    private EditText mUserNameEdt;
    private EditText mUserPasswordEdt;
    private Button mAddUserBtn;

    private UsersListAdapter mUsersListAdapter;
    private ArrayList<UserBean> mUserBeanArrayList;

    private UserBean mUserBean;
    private UserManagementViewPresenter mUserManagementViewPresenter;

    private boolean mIsInputValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_management_activity);

        init();
    }

    void init() {
        mUserBean = new UserBean();
        mUserBeanArrayList = new ArrayList<>();
        mUserManagementViewPresenter = new UserManagementViewPresenter(this);

        mUsersLv = (ListView) findViewById(R.id.lv_users);
        mUserNameEdt = (EditText) findViewById(R.id.edt_user_listitem_user_name);
        mUserPasswordEdt = (EditText) findViewById(R.id.edt_user_listitem_user_password);
        mAddUserBtn = (Button) findViewById(R.id.btn_user_listitem_add_user);
        mAddUserBtn.setOnClickListener(this);

        mUsersListAdapter = new UsersListAdapter(this, mUserBeanArrayList);
        mUsersLv.setAdapter(mUsersListAdapter);
        mUsersLv.setOnItemLongClickListener(this);
        mUsersLv.setOnItemClickListener(this);

        mUserManagementViewPresenter.getUsers(mUserBeanArrayList);
        mUsersListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean getAdminInput() {
        String mUserName = mUserNameEdt.getText().toString().trim();
        String mUserPassword = mUserPasswordEdt.getText().toString().trim();
        if (mUserName.isEmpty() || mUserPassword.contains("\'") || mUserPassword.contains("\"") || mUserPassword.isEmpty()) {
            return false;
        }

        mUserBean.setName(mUserName);
        mUserBean.setPassword(mUserPassword);
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_user_listitem_add_user:
                mIsInputValid = getAdminInput();
                if (mIsInputValid) {
                    mUserBean.setIsVip(0);
                    boolean temp = mUserManagementViewPresenter.addUser(mUserBean);
                    if (!temp) {
                        Toast.makeText(UserManagementActivity.this, "用户名已存在!", Toast.LENGTH_SHORT).show();
                    } else {
                        mUserBeanArrayList.clear();
                        mUserManagementViewPresenter.getUsers(mUserBeanArrayList);
                        mUsersListAdapter.notifyDataSetChanged();
                        Toast.makeText(UserManagementActivity.this, "添加成功!", Toast.LENGTH_SHORT).show();
                        mUserNameEdt.setText("");
                        mUserPasswordEdt.setText("");
                        mUserNameEdt.requestFocus();
                    }
                } else {
                    Toast.makeText(UserManagementActivity.this, "输入不合法", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        final UserBean userBean = mUserBeanArrayList.get(position);
        new AlertDialog.Builder(UserManagementActivity.this).setTitle("警告").setMessage("确定删除该用户?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mUserManagementViewPresenter.delUser(userBean);
                mUserBeanArrayList.clear();
                mUserManagementViewPresenter.getUsers(mUserBeanArrayList);
                mUsersListAdapter.notifyDataSetChanged();
                Toast.makeText(UserManagementActivity.this, "删除成功!", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("取消", null).show();
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(UserManagementActivity.this).setTitle("注意").setMessage("请退出登录!").setPositiveButton("确定", null).show();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final UserBean userBean = mUserBeanArrayList.get(position);
        new AlertDialog.Builder(UserManagementActivity.this).setTitle("提示").setMessage("设置为会员?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userBean.setIsVip(1);
                boolean isSuccess = mUserManagementViewPresenter.setUserIsVip(userBean);
                if (!isSuccess) {
                    Toast.makeText(UserManagementActivity.this, "设置失败", Toast.LENGTH_SHORT).show();
                } else {
                    mUserBeanArrayList.clear();
                    mUserManagementViewPresenter.getUsers(mUserBeanArrayList);
                    mUsersListAdapter.notifyDataSetChanged();
                    Toast.makeText(UserManagementActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                }
            }
        }).setNegativeButton("取消", null).show();
    }
}