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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.presenter.GoodsInfoModifyViewPresenter;
import com.example.lifeng.myapplication.utils.InputJudge;

/**
 * @author lifeng
 * @version 1.0 16/7/22
 * @description 商品信息修改Activity
 */
public class GoodsInfoModifyActivity extends AppCompatActivity implements IGoodsInfoModifyView, View.OnClickListener {
    private EditText mGoodsNameEdt;
    private EditText mGoodsAmountsEdt;
    private EditText mGoodsPirceEdt;
    private EditText mGoodsBriefDescriptionEdt;
    private EditText mGoodsDescriptionEdt;
    private Button mGoodsInfoModifyBtn;

    private int mGoodsId;
    private String mGoodsName;
    private int mGoodsAmounts;
    private double mGoodsPrice;
    private String mGoodsBriefDescription;
    private String mGoodsDescription;
    private String mGoodsImage;

    private GoodsBean mGoodsBean;
    private GoodsInfoModifyViewPresenter mGoodsInfoModifyViewPresenter;

    private boolean mIsInputValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_info_modify_activity);
        setTitle("商品信息修改");

        init();
    }

    void init() {
        mGoodsNameEdt = (EditText) findViewById(R.id.edt_goods_info_modify_goods_name);
        mGoodsNameEdt.setEnabled(false);
        mGoodsAmountsEdt = (EditText) findViewById(R.id.edt_goods_info_modify_goods_amounts);
        mGoodsPirceEdt = (EditText) findViewById(R.id.edt_goods_info_modify_goods_price);
        mGoodsBriefDescriptionEdt = (EditText) findViewById(R.id.edt_goods_info_modify_goods_brief_description);
        mGoodsDescriptionEdt = (EditText) findViewById(R.id.edt_goods_info_modify_goods_description);
        mGoodsInfoModifyBtn = (Button) findViewById(R.id.btn_goods_info_modify_modify);

        Intent intent = getIntent();
        mGoodsId = intent.getIntExtra("goodsid", 0);
        mGoodsName = intent.getStringExtra("goodsname");
        mGoodsAmounts = intent.getIntExtra("goodsamounts", 0);
        mGoodsPrice = intent.getDoubleExtra("goodsprice", 0.0);
        mGoodsBriefDescription = intent.getStringExtra("goodsbriefdescription");
        mGoodsDescription = intent.getStringExtra("goodsdescription");
        mGoodsImage = intent.getStringExtra("goodsimage");

        mGoodsNameEdt.setText(mGoodsName);
        mGoodsAmountsEdt.setText(Integer.toString(mGoodsAmounts));
        mGoodsPirceEdt.setText(Double.toString(mGoodsPrice));
        mGoodsBriefDescriptionEdt.setText(mGoodsBriefDescription);
        mGoodsDescriptionEdt.setText(mGoodsDescription);

        mGoodsInfoModifyBtn.setOnClickListener(this);

        mGoodsBean = new GoodsBean();
        mGoodsInfoModifyViewPresenter = new GoodsInfoModifyViewPresenter();
    }

    @Override
    public boolean getSellerUpdatedInput() {
        String name = mGoodsNameEdt.getText().toString().trim();
        String amounts = mGoodsAmountsEdt.getText().toString().trim();
        String price = mGoodsPirceEdt.getText().toString().trim();
        String briefDescription = mGoodsBriefDescriptionEdt.getText().toString().trim();
        String description = mGoodsDescriptionEdt.getText().toString().trim();

        if (!name.isEmpty() && !amounts.isEmpty() && !price.isEmpty() && !briefDescription.isEmpty() && !description.isEmpty()) {
            boolean temp = InputJudge.isPositiveInteger(amounts) && (InputJudge.isPositiveInteger(price) || InputJudge.isPositiveDoubleNumber(amounts));
            if (temp) {
                mGoodsBean.setId(mGoodsId);
                mGoodsBean.setName(name);
                mGoodsBean.setAmounts(Integer.valueOf(amounts));
                mGoodsBean.setPrice(Double.valueOf(price));
                mGoodsBean.setBriefDescription(briefDescription);
                mGoodsBean.setDescription(description);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_goods_info_modify_modify:
                mIsInputValid = getSellerUpdatedInput();
                if (!mIsInputValid) {
                    Toast.makeText(GoodsInfoModifyActivity.this, "输入不合法!", Toast.LENGTH_SHORT).show();
                } else {
                    mGoodsInfoModifyViewPresenter.modifyGoodsInfo(mGoodsBean);
                    Toast.makeText(GoodsInfoModifyActivity.this, "商品信息修改成功!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
}
