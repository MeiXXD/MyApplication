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
import com.example.lifeng.myapplication.bean.SellerBean;
import com.example.lifeng.myapplication.presenter.SellerLoginViewPresenter;

/**
 * @author lifeng
 * @version 1.0 16/7/21
 * @description 商品、订单管理TabActivity
 */
public class GoodsOrdersManagementTabActivity extends TabActivity implements View.OnClickListener {
    private String mSellerName;
    private String mSellerPassword;
    private Button mSellerLogout;
    private TabHost mTabHost;

    private SellerBean mSellerBean;
    private SellerLoginViewPresenter mSellerLoginViewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_orders_management_tab_activity);
        init();
    }

    void init() {
        mSellerBean = new SellerBean();
        mSellerLoginViewPresenter = new SellerLoginViewPresenter();

        Intent intent = getIntent();
        mSellerName = intent.getStringExtra("sellername");
        mSellerPassword = intent.getStringExtra("sellerpassword");

        mSellerBean.setName(mSellerName);
        mSellerBean.setPassword(mSellerPassword);
        tabhostInit();

        mSellerLogout = (Button) findViewById(R.id.btn_seller_logout);
        mSellerLogout.setOnClickListener(this);
    }

    void tabhostInit() {
        mTabHost = getTabHost();
        /* 去除标签下方的白线 */
        mTabHost.setPadding(mTabHost.getLeft(), mTabHost.getTop(), mTabHost.getRight(), mTabHost.getBottom() - 5);

        mTabHost.addTab(mTabHost.newTabSpec("goodsmanagement").setIndicator("商品管理").setContent(new Intent(this, GoodsManagementActivity.class)));
        mTabHost.addTab(mTabHost.newTabSpec("ordersmanagement").setIndicator("订单管理").setContent(new Intent(this, OrdersManagementActivity.class)));

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
            } else {//不选中
                tv.setTextColor(this.getResources().getColorStateList(android.R.color.holo_orange_dark));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_seller_logout:
                new AlertDialog.Builder(this).setTitle("警告").setMessage("确定退出登录?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSellerBean.setStatus(0);
                        mSellerLoginViewPresenter.updateSellerStatus(mSellerBean);
                        Toast.makeText(GoodsOrdersManagementTabActivity.this, "退出登录成功", Toast.LENGTH_SHORT).show();
                        //返回登录页面
                        Intent intent = new Intent();
                        intent.setClass(GoodsOrdersManagementTabActivity.this, SellerLoginActivity.class);
                        startActivity(intent);
                        //关闭当前页面
                        finish();
                    }
                }).setNegativeButton("取消", null).show();
                break;
        }
    }
}
