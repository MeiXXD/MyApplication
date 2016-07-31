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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.activity.adapter.MySpinnerAdapter;
import com.example.lifeng.myapplication.activity.adapter.SellerOrdersManagementAdapter;
import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.bean.OrderBean;
import com.example.lifeng.myapplication.bean.SellerBean;
import com.example.lifeng.myapplication.presenter.SellerOrdersManagementViewPresenter;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/21
 * @description 销售商订单管理Activity
 */
public class SellerOrdersManagementActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, ListView.OnItemClickListener {
    private SellerBean mSellerBean;

    private final String[] mStatusStrings = {"全部", "等待卖家确认", "订单驳回", "确认发货"};

    private Spinner mOrderStatusSpinner;
    private ListView mOrdersLv;
    private Button mOrderRejectBtn;
    private Button mOrderConfirmBtn;

    private ArrayList<OrderBean> mOrderBeanArrayList;
    private SellerOrdersManagementAdapter mSellerOrdersManagementAdapter;
    private SellerOrdersManagementViewPresenter mSellerOrdersManagementViewPresenter;

    private MySpinnerAdapter mMySpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_orders_management_activity);

        init();
    }

    void init() {
        mSellerBean = new SellerBean();

        Intent intent = getIntent();
        mSellerBean.setId(intent.getIntExtra("sellerid", 0));
        mSellerBean.setName(intent.getStringExtra("sellername"));
        mSellerBean.setPassword(intent.getStringExtra("sellerpassword"));

        mSellerOrdersManagementViewPresenter = new SellerOrdersManagementViewPresenter();

        mOrderStatusSpinner = (Spinner) findViewById(R.id.spinner_seller_orders_management_order_status);
        mOrdersLv = (ListView) findViewById(R.id.lv_seller_orders_management_orders);
        mOrderRejectBtn = (Button) findViewById(R.id.btn_seller_orders_management_order_reject);
        mOrderConfirmBtn = (Button) findViewById(R.id.btn_seller_orders_management_order_confirm);

        mOrderBeanArrayList = new ArrayList<>();
        mSellerOrdersManagementAdapter = new SellerOrdersManagementAdapter(this, mOrderBeanArrayList);
        mOrdersLv.setAdapter(mSellerOrdersManagementAdapter);

        mMySpinnerAdapter = new MySpinnerAdapter(this, android.R.layout.simple_spinner_item, mStatusStrings);
        mOrderStatusSpinner.setAdapter(mMySpinnerAdapter);
        mOrderStatusSpinner.setOnItemSelectedListener(this);
        mMySpinnerAdapter.notifyDataSetChanged();

        mOrderRejectBtn.setOnClickListener(this);
        mOrderConfirmBtn.setOnClickListener(this);
        mOrdersLv.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mOrderBeanArrayList.clear();
        mSellerOrdersManagementViewPresenter.getAllOrders(mOrderBeanArrayList, mSellerBean);
        mSellerOrdersManagementAdapter.notifyDataSetChanged();
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
        mSellerOrdersManagementViewPresenter.getOrdersByStatus(mOrderBeanArrayList, mSellerBean, status);
        mSellerOrdersManagementAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_seller_orders_management_order_reject:
                for (int i = mOrderBeanArrayList.size() - 1; i >= 0; i--) {
                    if (SellerOrdersManagementAdapter.mSparseBooleanArray.get(i)) {
                        OrderBean orderBean = mOrderBeanArrayList.get(i);
                        orderBean.setStatus(-1);
                        mSellerOrdersManagementViewPresenter.updateOrderStatus(orderBean);
                        GoodsBean goodsBean = new GoodsBean();
                        goodsBean.setId(orderBean.getGoodsId());
                        goodsBean.setAmounts(orderBean.getAmounts());
                        mSellerOrdersManagementViewPresenter.updateGoodsAmounts(goodsBean);
                    }
                }
                SellerOrdersManagementAdapter.resetCheckStatus();
                mOrderBeanArrayList.clear();
                mSellerOrdersManagementViewPresenter.getOrdersByStatus(mOrderBeanArrayList, mSellerBean, mOrderStatusSpinner.getSelectedItem().toString());
                mSellerOrdersManagementAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_seller_orders_management_order_confirm:
                for (int i = mOrderBeanArrayList.size() - 1; i >= 0; i--) {
                    if (SellerOrdersManagementAdapter.mSparseBooleanArray.get(i)) {
                        OrderBean orderBean = mOrderBeanArrayList.get(i);
                        orderBean.setStatus(1);
                        mSellerOrdersManagementViewPresenter.updateOrderStatus(orderBean);
                    }
                }
                SellerOrdersManagementAdapter.resetCheckStatus();
                mOrderBeanArrayList.clear();
                mSellerOrdersManagementViewPresenter.getOrdersByStatus(mOrderBeanArrayList, mSellerBean, mOrderStatusSpinner.getSelectedItem().toString());
                mSellerOrdersManagementAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CheckBox cb = (CheckBox) view.findViewById(R.id.cbx_seller_orders_management_order);
        cb.toggle();
        SellerOrdersManagementAdapter.mSparseBooleanArray.put(position, cb.isChecked());
    }
}
