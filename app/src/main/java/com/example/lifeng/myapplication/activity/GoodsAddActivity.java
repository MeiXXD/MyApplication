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

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.bean.SellerBean;
import com.example.lifeng.myapplication.presenter.GoodsAddViewPresenter;
import com.example.lifeng.myapplication.utils.InputJudge;

import java.io.FileNotFoundException;

/**
 * @author lifeng
 * @version 1.0 16/7/22
 * @description 添加商品页面
 */
public class GoodsAddActivity extends AppCompatActivity implements IGoodsAddView, View.OnClickListener {
    private ImageView mGoodsImg;
    private EditText mGoodsNameEdt;
    private EditText mGoodsAmountsEdt;
    private EditText mGoodsPirceEdt;
    private EditText mGoodsKindEdt;
    private EditText mGoodsBriefDescriptionEdt;
    private EditText mGoodsDescriptionEdt;
    private Button mGoodsAddBtn;

    private GoodsBean mGoodsBean;
    private SellerBean mSellerBean;
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
        mSellerBean = new SellerBean();
        Intent intent = getIntent();
        mSellerBean.setId(intent.getIntExtra("sellerid", 0));
        mSellerBean.setName(intent.getStringExtra("sellername"));
        mSellerBean.setPassword(intent.getStringExtra("sellerpassword"));

        mGoodsImg = (ImageView) findViewById(R.id.img_goods_add_goods_image);
        mGoodsNameEdt = (EditText) findViewById(R.id.edt_goods_add_goods_name);
        mGoodsAmountsEdt = (EditText) findViewById(R.id.edt_goods_add_goods_amounts);
        mGoodsPirceEdt = (EditText) findViewById(R.id.edt_goods_add_goods_price);
        mGoodsKindEdt = (EditText) findViewById(R.id.edt_goods_add_goods_kind);
        mGoodsBriefDescriptionEdt = (EditText) findViewById(R.id.edt_goods_add_goods_brief_description);
        mGoodsDescriptionEdt = (EditText) findViewById(R.id.edt_goods_add_goods_description);
        mGoodsAddBtn = (Button) findViewById(R.id.btn_goods_add_add);
        mGoodsAddBtn.setOnClickListener(this);
        mGoodsImg.setOnClickListener(this);

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
                    mGoodsBean.setSellerBean(mSellerBean);
                    mIsSuccess = mGoodsAddViewPresenter.addGoods(mGoodsBean);
                    if (mIsSuccess) {
                        Toast.makeText(GoodsAddActivity.this, "商品添加成功!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(GoodsAddActivity.this, "商品添加失败!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.img_goods_add_goods_image:
                Intent intent = new Intent();
                //开启Pictures画面Type设定为image
                intent.setType("image/*");
                //使用Intent.ACTION_GET_CONTENT这个Action
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //取得相片后返回本画面
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                mGoodsBean.setImage(uri.toString());
                mGoodsImg.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}