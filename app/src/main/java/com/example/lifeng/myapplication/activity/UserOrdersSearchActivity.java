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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.activity.adapter.MyOrdersManagementAdapter;
import com.example.lifeng.myapplication.bean.OrderBean;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.presenter.OrdersSearchViewPresenter;
import com.example.lifeng.myapplication.utils.InputJudge;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/28
 * @description 订单搜索Activity
 */
public class UserOrdersSearchActivity extends AppCompatActivity implements View.OnClickListener, IUserOrdersSearchView, ListView.OnItemClickListener {
    private String mKeywordStr;

    private EditText mKeywordEdt;
    private Button mSearchBtn;
    private ListView mOrdersLv;

    private UserBean mUserBean;
    private OrdersSearchViewPresenter mOrdersSearchViewPresenter;
    private ArrayList<OrderBean> mOrderBeanArrayList;

    private MyOrdersManagementAdapter mMyOrdersManagementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_search_activity);

        init();
    }

    void init() {
        mKeywordEdt = (EditText) findViewById(R.id.edt_search_keyword);
        mSearchBtn = (Button) findViewById(R.id.btn_search);
        mOrdersLv = (ListView) findViewById(R.id.lv_orders_search_orders);

        mUserBean = new UserBean();
        mOrdersSearchViewPresenter = new OrdersSearchViewPresenter();

        Intent intent = getIntent();
        mUserBean.setId(intent.getIntExtra("userid", 0));
        mUserBean.setName(intent.getStringExtra("username"));
        mUserBean.setPassword(intent.getStringExtra("userpassword"));

        mSearchBtn.setOnClickListener(this);

        mOrderBeanArrayList = new ArrayList<>();
        mMyOrdersManagementAdapter = new MyOrdersManagementAdapter(this, mOrderBeanArrayList);
        mOrdersLv.setAdapter(mMyOrdersManagementAdapter);

        mOrdersLv.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                if (getUserInput()) {
                    mOrderBeanArrayList.clear();
                    mOrdersSearchViewPresenter.searchOrders(mOrderBeanArrayList, mUserBean, mKeywordStr);
                    mMyOrdersManagementAdapter.notifyDataSetChanged();
                    if (mOrderBeanArrayList.size() == 0) {
                        Toast.makeText(this, "无相关订单", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    public boolean getUserInput() {
        mKeywordStr = mKeywordEdt.getText().toString().trim();
        if (!InputJudge.isInputValid(mKeywordStr)) {
            return false;
        }
        return true;
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
        intent.putExtra("ordergoodsimage", orderBean.getGoodsImage());
        intent.putExtra("ordergoodsprice", orderBean.getGoodsPrice());
        intent.putExtra("ordergoodsamounts", orderBean.getAmounts());
        intent.putExtra("orderaccount", orderBean.getAccount());
        intent.putExtra("orderdatetime", orderBean.getDateTime());
        intent.putExtra("orderstatus", orderBean.getStatus());
        startActivity(intent);
    }
}
