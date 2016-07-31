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
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.bean.ShoppingCartBean;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.presenter.GoodsDetaisViewPresenter;
import com.example.lifeng.myapplication.utils.InputJudge;

import java.io.FileNotFoundException;

/**
 * @author lifeng
 * @version 1.0 16/7/24
 * @description 商品详情页面
 */
public class GoodsDetailsActivity extends AppCompatActivity implements View.OnClickListener, IGoodsDetailsView {
    private UserBean mUserBean;
    private int mGoodsId;
    private GoodsBean mGoodsBean;
    private ShoppingCartBean mShoppingCartBean;

    private ImageView mGoodsDetailsGoodsImg;
    private TextView mGoodsDetailsGoodsNameTxt;
    private TextView mGoodsDetailsGoodsPriceTxt;
    private TextView mGoodsDetailsGoodsAmountsTxt;
    private TextView mGoodsDetailsGoodsDescriptionTxt;
    private EditText mGoodsDetailsGoodsAmountsEdt;
    private Button mGoodsDetailsAddShoppingCartBtn;

    private GoodsDetaisViewPresenter mGoodsDetaisViewPresenter;

    private boolean mIsInputValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_details_activity);
        setTitle("商品详情");

        init();
    }

    void init() {
        mIsInputValid = false;
        mGoodsDetaisViewPresenter = new GoodsDetaisViewPresenter(this);

        Intent intent = getIntent();
        mUserBean = new UserBean();
        mUserBean.setId(intent.getIntExtra("userid", 0));
        mUserBean.setName(intent.getStringExtra("username"));
        mUserBean.setPassword(intent.getStringExtra("userpassword"));

        mGoodsBean = new GoodsBean();
        mGoodsId = intent.getIntExtra("goodsid", 0);
        mGoodsBean.setId(mGoodsId);

        mShoppingCartBean = new ShoppingCartBean(mUserBean, mGoodsBean);

        mGoodsDetailsGoodsImg = (ImageView) findViewById(R.id.img_goods_details_goods_image);
        mGoodsDetailsGoodsNameTxt = (TextView) findViewById(R.id.txt_goods_details_goods_name);
        mGoodsDetailsGoodsPriceTxt = (TextView) findViewById(R.id.txt_goods_details_goods_price);
        mGoodsDetailsGoodsAmountsTxt = (TextView) findViewById(R.id.txt_goods_details_goods_amounts);
        mGoodsDetailsGoodsDescriptionTxt = (TextView) findViewById(R.id.txt_goods_details_goods_description);
        mGoodsDetailsGoodsAmountsEdt = (EditText) findViewById(R.id.edt_goods_details_goods_amounts);
        mGoodsDetailsAddShoppingCartBtn = (Button) findViewById(R.id.btn_goods_details_add_shopping_cart);
        mGoodsDetailsAddShoppingCartBtn.setOnClickListener(this);

        mGoodsDetaisViewPresenter.getGoodsDetails(mGoodsBean);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_goods_details_add_shopping_cart:
                mIsInputValid = getUserInput();
                if (!mIsInputValid) {
                    Toast.makeText(this, "输入不合法", Toast.LENGTH_SHORT).show();
                } else {
                    String[] temp = mGoodsDetailsGoodsAmountsTxt.getText().toString().trim().split(":");

                    if (Integer.valueOf(temp[1].replace(" ", "")) < Integer.valueOf(mGoodsDetailsGoodsAmountsEdt.getText().toString().trim())) {
                        Toast.makeText(this, "库存不足", Toast.LENGTH_SHORT).show();
                    } else {
                        mGoodsDetaisViewPresenter.addToShoppingCart(mShoppingCartBean);
                        Toast.makeText(this, "已添加到购物车", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }


    @Override
    public boolean getUserInput() {
        String amounts = mGoodsDetailsGoodsAmountsEdt.getText().toString().trim();
        if (!amounts.isEmpty() && InputJudge.isPositiveInteger(amounts)) {
            mShoppingCartBean.setAmounts(Integer.valueOf(amounts));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setOutput(GoodsBean goodsBean) {
        String mGoodsImage = goodsBean.getImage();
        if (null == mGoodsImage || mGoodsImage.isEmpty()) {
            mGoodsDetailsGoodsImg.setImageResource(R.drawable.goods);
        } else {
            Uri uri = Uri.parse(mGoodsImage);
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                mGoodsDetailsGoodsImg.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                mGoodsDetailsGoodsImg.setImageResource(R.drawable.goods);
                e.printStackTrace();
            }
        }

        mGoodsDetailsGoodsNameTxt.setText(goodsBean.getName());
        mGoodsDetailsGoodsPriceTxt.setText("价格(元): " + Double.toString(goodsBean.getPrice()));
        mGoodsDetailsGoodsAmountsTxt.setText("库存(件): " + Integer.toString(goodsBean.getAmounts()));
        mGoodsDetailsGoodsDescriptionTxt.setText(goodsBean.getDescription());
    }
}
