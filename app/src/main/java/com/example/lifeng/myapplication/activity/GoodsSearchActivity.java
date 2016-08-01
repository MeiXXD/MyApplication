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
import com.example.lifeng.myapplication.activity.adapter.UserGoodsListAdapter;
import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.presenter.GoodsSearchViewPresenter;
import com.example.lifeng.myapplication.utils.InputJudge;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/28
 * @description 商品搜索Activity
 */
public class GoodsSearchActivity extends AppCompatActivity implements View.OnClickListener, IGoodsSearchView, ListView.OnItemClickListener {
    private UserBean mUserBean;
    private GoodsSearchViewPresenter mGoodsSearchViewPresenter;

    private String mKeywordStr;
    private ArrayList<GoodsBean> mGoodsBeanArrayList;

    private EditText mKeywordEdt;
    private Button mSearchBtn;
    private ListView mGoodsLv;

    private UserGoodsListAdapter mUserGoodsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_search_activity);

        init();
    }

    void init() {
        mKeywordEdt = (EditText) findViewById(R.id.edt_search_keyword);
        mSearchBtn = (Button) findViewById(R.id.btn_search);
        mGoodsLv = (ListView) findViewById(R.id.lv_goods_search_goods);

        mUserBean = new UserBean();
        mGoodsSearchViewPresenter = new GoodsSearchViewPresenter();

        Intent intent = getIntent();
        mUserBean.setId(intent.getIntExtra("userid", 0));
        mUserBean.setName(intent.getStringExtra("username"));
        mUserBean.setPassword(intent.getStringExtra("userpassword"));

        mSearchBtn.setOnClickListener(this);

        mGoodsBeanArrayList = new ArrayList<>();
        mUserGoodsListAdapter = new UserGoodsListAdapter(this, mUserBean, mGoodsBeanArrayList);
        mGoodsLv.setAdapter(mUserGoodsListAdapter);

        mGoodsLv.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                if (getUserInput()) {
                    mGoodsBeanArrayList.clear();
                    mGoodsSearchViewPresenter.searchGoods(mGoodsBeanArrayList, mKeywordStr);
                    mUserGoodsListAdapter.notifyDataSetChanged();
                    if (mGoodsBeanArrayList.size() == 0) {
                        Toast.makeText(this, "无相关商品", Toast.LENGTH_SHORT).show();
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
        GoodsBean goodsBean = mGoodsBeanArrayList.get(position);
        int goodsId = goodsBean.getId();
        Intent intent = new Intent();
        intent.putExtra("goodsid", goodsId);
        intent.putExtra("userid", mUserBean.getId());
        intent.putExtra("username", mUserBean.getName());
        intent.putExtra("userpassword", mUserBean.getPassword());
        intent.setClass(GoodsSearchActivity.this, GoodsDetailsActivity.class);
        startActivity(intent);
    }
}
