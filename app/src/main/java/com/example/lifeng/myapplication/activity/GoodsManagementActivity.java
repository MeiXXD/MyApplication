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
import android.widget.Button;
import android.widget.ListView;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.activity.adapter.GoodsManagementListAdapter;
import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.presenter.GoodsManagementViewPresenter;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/21
 * @description 商品管理Activity
 */
public class GoodsManagementActivity extends AppCompatActivity implements View.OnClickListener, ListView.OnItemLongClickListener, ListView.OnItemClickListener {
    private GoodsManagementListAdapter mGoodsManagementListAdapter;
    private ArrayList<GoodsBean> mGoodsBeanArrayList;

    private ListView mGoodsLv;
    private Button mGoodsAddBtn;

    private GoodsManagementViewPresenter mGoodsManagementViewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_management_activity);

        init();
    }

    void init() {
        mGoodsManagementViewPresenter = new GoodsManagementViewPresenter();
        mGoodsBeanArrayList = new ArrayList<>();
        mGoodsManagementListAdapter = new GoodsManagementListAdapter(this, mGoodsBeanArrayList);

        mGoodsAddBtn = (Button) findViewById(R.id.btn_goods_management_add_goods);
        mGoodsLv = (ListView) findViewById(R.id.lv_goods_management_goods);
        mGoodsLv.setAdapter(mGoodsManagementListAdapter);

        mGoodsAddBtn.setOnClickListener(this);
        mGoodsLv.setOnItemClickListener(this);
        mGoodsLv.setOnItemLongClickListener(this);

        mGoodsManagementViewPresenter.getGoods(mGoodsBeanArrayList);
        mGoodsManagementListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //进入商品详情修改页面
        GoodsBean goodsBean = mGoodsBeanArrayList.get(position);
        Intent intent = new Intent();
        intent.setClass(this, GoodsInfoModifyActivity.class);
        intent.putExtra("goodsid", goodsBean.getId());
        intent.putExtra("goodsname", goodsBean.getName());
        intent.putExtra("goodsamounts", goodsBean.getAmounts());
        intent.putExtra("goodsprice", goodsBean.getPrice());
        intent.putExtra("goodsbriefdescription", goodsBean.getBriefDescription());
        intent.putExtra("goodsdescription", goodsBean.getDescription());
        intent.putExtra("goodsimage", goodsBean.getImage());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        //删除该商品
        final GoodsBean goodsBean = mGoodsBeanArrayList.get(position);
        new AlertDialog.Builder(this).setTitle("警告").setMessage("确定删除该商品?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mGoodsManagementViewPresenter.delGoods(goodsBean);
                mGoodsBeanArrayList.clear();
                mGoodsManagementViewPresenter.getGoods(mGoodsBeanArrayList);
                mGoodsManagementListAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton("取消", null).show();
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_goods_management_add_goods:
                Intent intent = new Intent();
                intent.setClass(GoodsManagementActivity.this, GoodsAddActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoodsBeanArrayList.clear();
        mGoodsManagementViewPresenter.getGoods(mGoodsBeanArrayList);
        mGoodsManagementListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(GoodsManagementActivity.this).setTitle("注意").setMessage("请退出登录!").setPositiveButton("确定", null).show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
