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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.activity.adapter.MyOrderManagementAdapter;
import com.example.lifeng.myapplication.activity.adapter.MySpinnerAdapter;
import com.example.lifeng.myapplication.bean.OrderBean;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.presenter.MyOrderManagementViewPresenter;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/27
 * @description 我的订单管理界面
 */
public class MyOrderManagementActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ListView mMyOrdersLv;
    private Spinner mOrderStatusSpinner;

    private UserBean mUserBean;
    private MyOrderManagementViewPresenter mMyOrderManagementViewPresenter;

    private ArrayList<String> mStringArrayList;
    private ArrayList<OrderBean> mOrderBeanArrayList;
    private MyOrderManagementAdapter mMyOrderManagementAdapter;
    private MySpinnerAdapter mMySpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_management);
        setTitle("我的订单管理");

        init();
    }

    void init() {
        mOrderStatusSpinner = (Spinner) findViewById(R.id.spinner_my_order_management_order_status);
        mMyOrdersLv = (ListView) findViewById(R.id.lv_my_order_management_my_orders);

        mUserBean = new UserBean();
        mMyOrderManagementViewPresenter = new MyOrderManagementViewPresenter();
        mOrderBeanArrayList = new ArrayList<>();
        Intent intent = getIntent();
        mUserBean.setId(intent.getIntExtra("userid", 0));
        mUserBean.setName(intent.getStringExtra("username"));
        mUserBean.setPassword(intent.getStringExtra("userpassword"));

        mMyOrderManagementAdapter = new MyOrderManagementAdapter(this, mOrderBeanArrayList);
        mMyOrdersLv.setAdapter(mMyOrderManagementAdapter);

        mStringArrayList = new ArrayList<>();
        mMyOrderManagementViewPresenter.getOrderStatusKinds(mStringArrayList);
        mMySpinnerAdapter = new MySpinnerAdapter(this, android.R.layout.simple_spinner_item, mStringArrayList.toArray(new String[mStringArrayList.size()]));
        mOrderStatusSpinner.setAdapter(mMySpinnerAdapter);
        mOrderStatusSpinner.setOnItemSelectedListener(this);
        mMyOrderManagementAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mOrderBeanArrayList.clear();
        mMyOrderManagementViewPresenter.getUserAllOrders(mOrderBeanArrayList, mUserBean);
        mMyOrderManagementAdapter.notifyDataSetChanged();

        mStringArrayList.clear();
        mMyOrderManagementViewPresenter.getOrderStatusKinds(mStringArrayList);
        mMySpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String status = parent.getItemAtPosition(position).toString();
        mOrderBeanArrayList.clear();
        mMyOrderManagementViewPresenter.getOrdersByStatus(mOrderBeanArrayList, mUserBean, status);
        mMyOrderManagementAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
