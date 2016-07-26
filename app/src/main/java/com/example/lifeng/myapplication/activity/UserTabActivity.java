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

import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.presenter.UserTabActivityViewPresenter;

/**
 * @author lifeng
 * @version 1.0 16/7/24
 * @description 用户登录后进入的Tab页面
 */
public class UserTabActivity extends TabActivity implements View.OnClickListener {
    private final String[] mtitles = {"商品目录", "购物车", "我的"};

    private TabHost mTabHost;
    private UserBean mUserBean;
    private TextView mTitleTxt;
    private Button mUserLogoutBtn;

    private UserTabActivityViewPresenter mUserTabActivityViewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_tab_activity);

        init();
    }

    void init() {
        mTitleTxt = (TextView) findViewById(R.id.txt_user_tab_activity_title);
        mUserLogoutBtn = (Button) findViewById(R.id.btn_user_logout);
        mUserLogoutBtn.setOnClickListener(this);

        mUserBean = new UserBean();
        mUserTabActivityViewPresenter = new UserTabActivityViewPresenter();

        Intent intent = getIntent();
        mUserBean.setId(intent.getIntExtra("userid", 0));
        mUserBean.setName(intent.getStringExtra("username"));
        mUserBean.setPassword(intent.getStringExtra("userpassword"));

        tabHostInit();
    }

    void tabHostInit() {
        mTabHost = getTabHost();
        /* 去除标签下方的白线 */
        mTabHost.setPadding(mTabHost.getLeft(), mTabHost.getTop(), mTabHost.getRight(), mTabHost.getBottom() - 5);

        Intent goodsIntent = new Intent(this, UserGoodsActivity.class);
        goodsIntent.putExtra("userid", mUserBean.getId());
        goodsIntent.putExtra("username", mUserBean.getName());
        goodsIntent.putExtra("userpassword", mUserBean.getPassword());
        mTabHost.addTab(mTabHost.newTabSpec("usergoods").setIndicator("商品").setContent(goodsIntent));

        Intent shoppingCartIntent = new Intent(this, UserShoppingCartActivity.class);
        shoppingCartIntent.putExtra("userid", mUserBean.getId());
        shoppingCartIntent.putExtra("username", mUserBean.getName());
        shoppingCartIntent.putExtra("userpassword", mUserBean.getPassword());
        mTabHost.addTab(mTabHost.newTabSpec("usershoppingcart").setIndicator("购物车").setContent(shoppingCartIntent));

        Intent myIntent = new Intent(this, UserMyActivity.class);
        myIntent.putExtra("userid", mUserBean.getId());
        myIntent.putExtra("username", mUserBean.getName());
        myIntent.putExtra("userpassword", mUserBean.getPassword());
        mTabHost.addTab(mTabHost.newTabSpec("usermy").setIndicator("我的").setContent(myIntent));

        mTabHost.getTabWidget().setBackgroundResource(R.color.colorPrimary);
        updateTab(mTabHost);
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                updateTab(mTabHost);
            }
        });
    }

    private void updateTab(TabHost tabHost) {
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).getLayoutParams().height = 150;
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(20);
            if (tabHost.getCurrentTab() == i) {//选中
                tv.setTextColor(this.getResources().getColorStateList(android.R.color.holo_orange_light));
                mTitleTxt.setText(mtitles[i]);
            } else {//不选中
                tv.setTextColor(this.getResources().getColorStateList(android.R.color.holo_orange_dark));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_user_logout:
                new AlertDialog.Builder(UserTabActivity.this).setTitle("警告").setMessage("确定要退出登录?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mUserBean.setStatus(0);
                        mUserTabActivityViewPresenter.userLogout(mUserBean);
                        Toast.makeText(UserTabActivity.this, "退出登录成功", Toast.LENGTH_SHORT).show();
                        //返回登录页面
                        Intent intent = new Intent();
                        intent.setClass(UserTabActivity.this, UserLoginActivity.class);
                        startActivity(intent);
                        //关闭当前页面
                        finish();
                    }
                }).setNegativeButton("取消", null).show();
                break;
        }
    }
}
