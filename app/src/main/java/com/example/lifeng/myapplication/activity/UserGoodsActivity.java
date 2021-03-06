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
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.activity.adapter.MySpinnerAdapter;
import com.example.lifeng.myapplication.activity.adapter.UserGoodsListAdapter;
import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.presenter.UserGoodsViewPresenter;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/24
 * @description 商品界面
 */
public class UserGoodsActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener, ListView.OnItemClickListener, View.OnClickListener {
    private EditText mGoodsSearchEdt;
    private Spinner mGoodsKindSpinner;
    private ListView mUserGoodsLv;

    private ArrayList<String> mStringArrayList;
    private ArrayList<GoodsBean> mUserGoodsBeanArrayList;
    private UserGoodsListAdapter mUserGoodsListAdapter;

    private MySpinnerAdapter mMySpinnerAdapter;

    private UserGoodsViewPresenter mUserGoodsViewPresenter;
    private UserBean mUserBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_goods_activity);

        init();
    }

    private void init() {
        mGoodsSearchEdt = (EditText) findViewById(R.id.edt_user_goods_goods_search);
        mGoodsSearchEdt.setInputType(InputType.TYPE_NULL);
        mGoodsSearchEdt.setOnClickListener(this);
        mStringArrayList = new ArrayList<>();

        //intent获取数据
        mUserBean = new UserBean();
        Intent intent = getIntent();
        mUserBean.setId(intent.getIntExtra("userid", 0));
        mUserBean.setName(intent.getStringExtra("username"));
        mUserBean.setPassword(intent.getStringExtra("userpassword"));

        mUserGoodsViewPresenter = new UserGoodsViewPresenter(mUserBean);

        //listview逻辑
        mUserGoodsBeanArrayList = new ArrayList<>();
        mUserGoodsListAdapter = new UserGoodsListAdapter(this, mUserBean, mUserGoodsBeanArrayList);
        mUserGoodsLv = (ListView) findViewById(R.id.lv_user_goods_goods);
        mUserGoodsLv.setAdapter(mUserGoodsListAdapter);
        mUserGoodsLv.setOnItemClickListener(this);


        //spinner逻辑
        mGoodsKindSpinner = (Spinner) findViewById(R.id.spinner_user_goods_kind);
        mUserGoodsViewPresenter.getGoodsKinds(mStringArrayList);
        mMySpinnerAdapter = new MySpinnerAdapter(this, android.R.layout.simple_spinner_item, mStringArrayList.toArray(new String[mStringArrayList.size()]));
        mGoodsKindSpinner.setAdapter(mMySpinnerAdapter);
        mGoodsKindSpinner.setOnItemSelectedListener(this);
        mMySpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //根据类别获得该类别下的所有商品
        String kind = parent.getItemAtPosition(position).toString();
        mUserGoodsBeanArrayList.clear();
        mUserGoodsViewPresenter.getGoodsByKind(mUserGoodsBeanArrayList, kind);
        mUserGoodsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GoodsBean goodsBean = mUserGoodsBeanArrayList.get(position);
        int goodsId = goodsBean.getId();
        Intent intent = new Intent();
        intent.putExtra("goodsid", goodsId);
        intent.putExtra("userid", mUserBean.getId());
        intent.putExtra("username", mUserBean.getName());
        intent.putExtra("userpassword", mUserBean.getPassword());
        intent.setClass(UserGoodsActivity.this, GoodsDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(UserGoodsActivity.this).setTitle("注意").setMessage("请退出登录").setPositiveButton("确定", null).show();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mUserGoodsBeanArrayList.clear();
        mUserGoodsViewPresenter.getGoods(mUserGoodsBeanArrayList);
        mUserGoodsListAdapter.notifyDataSetChanged();

        mStringArrayList.clear();
        mUserGoodsViewPresenter.getGoodsKinds(mStringArrayList);
        mMySpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edt_user_goods_goods_search:
                Intent intent = new Intent();
                intent.putExtra("userid", mUserBean.getId());
                intent.putExtra("username", mUserBean.getName());
                intent.putExtra("userpassword", mUserBean.getPassword());
                intent.setClass(UserGoodsActivity.this, GoodsSearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
