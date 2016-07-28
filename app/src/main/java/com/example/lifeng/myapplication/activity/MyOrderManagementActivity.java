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
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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
public class MyOrderManagementActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ListView.OnItemClickListener, View.OnClickListener {
    private ListView mMyOrdersLv;
    private Spinner mOrderStatusSpinner;
    private EditText mOrderSearchEdt;

    private UserBean mUserBean;
    private MyOrderManagementViewPresenter mMyOrderManagementViewPresenter;

    private ArrayList<String> mStringArrayList;
    private ArrayList<OrderBean> mOrderBeanArrayList;
    private MyOrderManagementAdapter mMyOrderManagementAdapter;
    private MySpinnerAdapter mMySpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_order_management_activity);
        setTitle("我的订单管理");

        init();
    }

    void init() {
        mOrderStatusSpinner = (Spinner) findViewById(R.id.spinner_my_order_management_order_status);
        mMyOrdersLv = (ListView) findViewById(R.id.lv_my_order_management_my_orders);
        mOrderSearchEdt = (EditText) findViewById(R.id.edt_user_goods_goods_search);
        mOrderSearchEdt.setInputType(InputType.TYPE_NULL);
        mOrderSearchEdt.setOnClickListener(this);

        mUserBean = new UserBean();
        mMyOrderManagementViewPresenter = new MyOrderManagementViewPresenter();
        mOrderBeanArrayList = new ArrayList<>();
        Intent intent = getIntent();
        mUserBean.setId(intent.getIntExtra("userid", 0));
        mUserBean.setName(intent.getStringExtra("username"));
        mUserBean.setPassword(intent.getStringExtra("userpassword"));

        mMyOrderManagementAdapter = new MyOrderManagementAdapter(this, mOrderBeanArrayList);
        mMyOrdersLv.setAdapter(mMyOrderManagementAdapter);
        mMyOrdersLv.setOnItemClickListener(this);

        mStringArrayList = new ArrayList<>();
        mMyOrderManagementViewPresenter.getOrderStatusKinds(mStringArrayList, mUserBean);
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
        mMyOrderManagementViewPresenter.getOrderStatusKinds(mStringArrayList, mUserBean);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        OrderBean orderBean = mOrderBeanArrayList.get(position);
        Intent intent = new Intent();
        intent.setClass(this, OrderDetails.class);
        intent.putExtra("orderid", orderBean.getId());
        intent.putExtra("orderusername", mUserBean.getName());
        intent.putExtra("orderuserphone", orderBean.getPhone());
        intent.putExtra("orderuseraddress", orderBean.getAddress());
        intent.putExtra("ordergoodsname", orderBean.getGoodsName());
        intent.putExtra("ordergoodsprice", orderBean.getGoodsPrice());
        intent.putExtra("ordergoodsamounts", orderBean.getAmounts());
        intent.putExtra("orderaccount", orderBean.getAccount());
        intent.putExtra("orderdatetime", orderBean.getDateTime());
        intent.putExtra("orderstatus", orderBean.getStatus());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edt_user_goods_goods_search:
                Intent intent = new Intent();
                intent.putExtra("userid", mUserBean.getId());
                intent.putExtra("username", mUserBean.getName());
                intent.putExtra("userpassword", mUserBean.getPassword());
                intent.setClass(MyOrderManagementActivity.this, OrdersSearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
