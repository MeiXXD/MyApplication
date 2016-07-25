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
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.activity.adapter.KindsSpinnerAdapter;
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
public class UserGoodsActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener, ListView.OnItemClickListener {
    private Spinner mGoodsKindSpinner;
    private ListView mUserGoodsLv;

    private ArrayList<String> mStringArrayList;
    private ArrayList<GoodsBean> mUserGoodsBeanArrayList;
    private UserGoodsListAdapter mUserGoodsListAdapter;

    private UserGoodsViewPresenter mUserGoodsViewPresenter;
    private UserBean mUserBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_goods_activity);
        setTitle("商品");

        init();
    }

    private void init() {
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
        mUserGoodsViewPresenter.getGoods(mUserGoodsBeanArrayList);
        mUserGoodsListAdapter.notifyDataSetChanged();


        //spinner逻辑
        mGoodsKindSpinner = (Spinner) findViewById(R.id.spinner_user_goods_kind);
        mUserGoodsViewPresenter.getGoodsKinds(mStringArrayList);
        KindsSpinnerAdapter kindsSpinnerAdapter = new KindsSpinnerAdapter(this, android.R.layout.simple_spinner_item, mStringArrayList.toArray(new String[mStringArrayList.size()]));
        mGoodsKindSpinner.setAdapter(kindsSpinnerAdapter);
        mGoodsKindSpinner.setOnItemSelectedListener(this);
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
        mUserGoodsViewPresenter.getGoods(mUserGoodsBeanArrayList);
        mUserGoodsListAdapter.notifyDataSetChanged();
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
            new AlertDialog.Builder(UserGoodsActivity.this).setTitle("警告").setMessage("确定要退出登录?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mUserBean.setStatus(0);
                    mUserGoodsViewPresenter.logout(mUserBean);
                    Toast.makeText(UserGoodsActivity.this, "退出登录成功", Toast.LENGTH_SHORT).show();
                    //返回登录页面
                    Intent intent = new Intent();
                    intent.setClass(UserGoodsActivity.this, UserLoginActivity.class);
                    startActivity(intent);
                    //关闭当前页面
                    finish();
                }
            }).setNegativeButton("取消", null).show();
        }

        return super.onKeyDown(keyCode, event);
    }
}
