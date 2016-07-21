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
import com.example.lifeng.myapplication.activity.adapter.SellersListAdapter;
import com.example.lifeng.myapplication.bean.SellerBean;
import com.example.lifeng.myapplication.presenter.SellerManagementViewPresenter;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 销售商管理Activity
 */
public class SellerManagementActivity extends AppCompatActivity implements ISellerManagementView, View.OnClickListener, AdapterView.OnItemLongClickListener {
    private ListView mSellersLv;
    private EditText mSellerNameEdt;
    private EditText mSellerPasswordEdt;
    private Button mAddSellerBtn;

    private SellersListAdapter mSellersListAdapter;
    private ArrayList<SellerBean> mSellerBeanArrayList;

    private SellerBean mSellerBean;
    private SellerManagementViewPresenter mSellerManagementViewPresenter;

    private boolean mIsInputValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_management_activity);

        init();
    }

    void init() {
        mSellerBean = new SellerBean();
        mSellerBeanArrayList = new ArrayList<SellerBean>();
        mSellerManagementViewPresenter = new SellerManagementViewPresenter();

        mSellersLv = (ListView) findViewById(R.id.lv_sellers);
        mSellerNameEdt = (EditText) findViewById(R.id.add_seller_listitem_seller_name);
        mSellerPasswordEdt = (EditText) findViewById(R.id.add_seller_listitem_seller_password);
        mAddSellerBtn = (Button) findViewById(R.id.add_seller_listitem_add_seller);
        mAddSellerBtn.setOnClickListener(this);

        mSellersListAdapter = new SellersListAdapter(this, mSellerBeanArrayList);
        mSellersLv.setAdapter(mSellersListAdapter);
        mSellersLv.setOnItemLongClickListener(this);

        mSellerManagementViewPresenter.getSellers(mSellerBeanArrayList);
        mSellersListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean getAdminInput() {
        String mSellerName = mSellerNameEdt.getText().toString().trim();
        String mSellerPassword = mSellerPasswordEdt.getText().toString().trim();
        if (mSellerName.isEmpty() || mSellerPassword.contains("\'") || mSellerPassword.contains("\"") || mSellerPassword.isEmpty()) {
            return false;
        }

        mSellerBean.setName(mSellerName);
        mSellerBean.setPassword(mSellerPassword);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_seller_listitem_add_seller:
                mIsInputValid = getAdminInput();
                if (mIsInputValid) {
                    boolean temp = mSellerManagementViewPresenter.addSeller(mSellerBean);
                    if (!temp) {
                        Toast.makeText(SellerManagementActivity.this, "用户名已存在!", Toast.LENGTH_SHORT).show();
                    } else {
                        mSellerBeanArrayList.clear();
                        mSellerManagementViewPresenter.getSellers(mSellerBeanArrayList);
                        mSellersListAdapter.notifyDataSetChanged();
                        Toast.makeText(SellerManagementActivity.this, "添加成功!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SellerManagementActivity.this, "输入不合法!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        final SellerBean sellerBean = mSellerBeanArrayList.get(position);
        new AlertDialog.Builder(SellerManagementActivity.this).setTitle("警告").setMessage("确定删除该销售商?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSellerManagementViewPresenter.delSeller(sellerBean);
                mSellerBeanArrayList.clear();
                mSellerManagementViewPresenter.getSellers(mSellerBeanArrayList);
                mSellersListAdapter.notifyDataSetChanged();
                Toast.makeText(SellerManagementActivity.this, "删除成功!", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("取消", null).show();
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(SellerManagementActivity.this).setTitle("注意").setMessage("请退出登录!").setPositiveButton("确定", null).show();
        }
        return super.onKeyDown(keyCode, event);
    }
}