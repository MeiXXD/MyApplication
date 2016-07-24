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
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.presenter.GoodsAddViewPresenter;
import com.example.lifeng.myapplication.utils.InputJudge;

/**
 * @author lifeng
 * @version 1.0 16/7/22
 * @description 添加商品页面
 */
public class GoodsAddActivity extends AppCompatActivity implements IGoodsAddView, View.OnClickListener {
    private EditText mGoodsNameEdt;
    private EditText mGoodsAmountsEdt;
    private EditText mGoodsPirceEdt;
    private EditText mGoodsKindEdt;
    private EditText mGoodsBriefDescriptionEdt;
    private EditText mGoodsDescriptionEdt;
    private Button mGoodsAddBtn;

    private GoodsBean mGoodsBean;
    private GoodsAddViewPresenter mGoodsAddViewPresenter;

    private boolean mIsInputValid;
    private boolean mIsSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_add_activity);
        setTitle("商品添加");

        init();
    }

    void init() {
        mGoodsNameEdt = (EditText) findViewById(R.id.edt_goods_add_goods_name);
        mGoodsAmountsEdt = (EditText) findViewById(R.id.edt_goods_add_goods_amounts);
        mGoodsPirceEdt = (EditText) findViewById(R.id.edt_goods_add_goods_price);
        mGoodsKindEdt = (EditText) findViewById(R.id.edt_goods_add_goods_kind);
        mGoodsBriefDescriptionEdt = (EditText) findViewById(R.id.edt_goods_add_goods_brief_description);
        mGoodsDescriptionEdt = (EditText) findViewById(R.id.edt_goods_add_goods_description);
        mGoodsAddBtn = (Button) findViewById(R.id.btn_goods_add_add);
        mGoodsAddBtn.setOnClickListener(this);

        mGoodsBean = new GoodsBean();
        mGoodsAddViewPresenter = new GoodsAddViewPresenter();

        mIsInputValid = false;
        mIsSuccess = false;
    }

    @Override
    public boolean getSellerInput() {
        String name = mGoodsNameEdt.getText().toString().trim();
        String amounts = mGoodsAmountsEdt.getText().toString().trim();
        String price = mGoodsPirceEdt.getText().toString().trim();
        String kind = mGoodsKindEdt.getText().toString().trim();
        String briefDescription = mGoodsBriefDescriptionEdt.getText().toString().trim();
        String description = mGoodsDescriptionEdt.getText().toString().trim();

        if (!name.isEmpty() && !amounts.isEmpty() && !price.isEmpty() && !kind.isEmpty() && !briefDescription.isEmpty() && !description.isEmpty()) {
            boolean temp = InputJudge.isPositiveInteger(amounts) && (InputJudge.isPositiveInteger(price) || InputJudge.isPositiveDoubleNumber(price));
            if (temp) {
                mGoodsBean.setName(name);
                mGoodsBean.setAmounts(Integer.valueOf(amounts));
                mGoodsBean.setPrice(Double.valueOf(price));
                mGoodsBean.setKind(kind);
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
            case R.id.btn_goods_add_add:
                mIsInputValid = getSellerInput();
                if (!mIsInputValid) {
                    Toast.makeText(GoodsAddActivity.this, "输入不合法!", Toast.LENGTH_SHORT).show();
                } else {
                    mIsSuccess = mGoodsAddViewPresenter.addGoods(mGoodsBean);
                    if (mIsSuccess) {
                        Toast.makeText(GoodsAddActivity.this, "商品添加成功!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(GoodsAddActivity.this, "商品添加失败!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
