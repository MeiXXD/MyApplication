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

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.activity.adapter.MySpinnerAdapter;
import com.example.lifeng.myapplication.activity.adapter.SellerOrdersManagementAdapter;
import com.example.lifeng.myapplication.bean.OrderBean;
import com.example.lifeng.myapplication.presenter.SellerOrdersManagementViewPresenter;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/21
 * @description 销售商订单管理Activity
 */
public class SellerOrdersManagementActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner mOrderStatusSpinner;
    private ListView mOrdersLv;
    private ArrayList<OrderBean> mOrderBeanArrayList;
    private SellerOrdersManagementAdapter mSellerOrdersManagementAdapter;
    private SellerOrdersManagementViewPresenter mSellerOrdersManagementViewPresenter;

    private ArrayList<String> mStringArrayList;
    private MySpinnerAdapter mMySpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_orders_management_activity);

        init();
    }

    void init() {
        mSellerOrdersManagementViewPresenter = new SellerOrdersManagementViewPresenter();

        mOrderStatusSpinner = (Spinner) findViewById(R.id.spinner_seller_orders_management_order_status);
        mOrdersLv = (ListView) findViewById(R.id.lv_seller_orders_management_orders);

        mOrderBeanArrayList = new ArrayList<>();
        mSellerOrdersManagementAdapter = new SellerOrdersManagementAdapter(this, mOrderBeanArrayList);
        mOrdersLv.setAdapter(mSellerOrdersManagementAdapter);

        mStringArrayList = new ArrayList<>();
        mSellerOrdersManagementViewPresenter.getOrderStatusKinds(mStringArrayList);
        mMySpinnerAdapter = new MySpinnerAdapter(this, android.R.layout.simple_spinner_item, mStringArrayList.toArray(new String[mStringArrayList.size()]));
        mOrderStatusSpinner.setAdapter(mMySpinnerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mOrderBeanArrayList.clear();
        mSellerOrdersManagementViewPresenter.getAllOrders(mOrderBeanArrayList);
        mSellerOrdersManagementAdapter.notifyDataSetChanged();

        mStringArrayList.clear();
        mSellerOrdersManagementViewPresenter.getOrderStatusKinds(mStringArrayList);
        mMySpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(SellerOrdersManagementActivity.this).setTitle("注意").setMessage("请退出登录!").setPositiveButton("确定", null).show();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String status = parent.getItemAtPosition(position).toString();
        mOrderBeanArrayList.clear();
        mSellerOrdersManagementViewPresenter.getOrdersByStatus(mOrderBeanArrayList, status);
        mSellerOrdersManagementAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
