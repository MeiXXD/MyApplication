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
import android.widget.ListView;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.activity.adapter.MyOrderManagementAdapter;
import com.example.lifeng.myapplication.bean.OrderBean;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.presenter.UserOrderManagementViewPresenter;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/27
 * @description 我的订单管理界面
 */
public class MyOrderManagement extends AppCompatActivity {
    private ListView mMyOrdersLv;

    private UserBean mUserBean;
    private UserOrderManagementViewPresenter mUserOrderManagementViewPresenter;

    private ArrayList<OrderBean> mOrderBeanArrayList;
    private MyOrderManagementAdapter mMyOrderManagementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_management);
        setTitle("我的订单管理");

        init();
    }

    void init() {
        mMyOrdersLv = (ListView) findViewById(R.id.lv_my_order_management_my_orders);

        mUserBean = new UserBean();
        mUserOrderManagementViewPresenter = new UserOrderManagementViewPresenter();
        mOrderBeanArrayList = new ArrayList<>();
        Intent intent = getIntent();
        mUserBean.setId(intent.getIntExtra("userid", 0));
        mUserBean.setName(intent.getStringExtra("username"));
        mUserBean.setPassword(intent.getStringExtra("userpassword"));

        mMyOrderManagementAdapter = new MyOrderManagementAdapter(this, mOrderBeanArrayList);
        mMyOrdersLv.setAdapter(mMyOrderManagementAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mOrderBeanArrayList.clear();
        mUserOrderManagementViewPresenter.getUserAllOrders(mOrderBeanArrayList, mUserBean);
        mMyOrderManagementAdapter.notifyDataSetChanged();
    }
}
