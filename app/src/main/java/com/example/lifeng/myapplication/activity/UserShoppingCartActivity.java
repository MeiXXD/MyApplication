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

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.activity.adapter.ShoppingCartListAdapter;
import com.example.lifeng.myapplication.bean.ShoppingCartBean;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.presenter.UserShoppingCartViewPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/24
 * @description 用户购物车页面
 */
public class UserShoppingCartActivity extends AppCompatActivity implements ListView.OnItemClickListener, View.OnClickListener, ListView.OnItemLongClickListener {
    private UserBean mUserBean;
    private ShoppingCartBean mShoppingCartBean = null;
    private UserShoppingCartViewPresenter mUserShoppingCartViewPresenter;
    private ArrayList<ShoppingCartBean> mShoppingCartBeanArrayList;
    private ShoppingCartListAdapter mShoppingCartListAdapter;

    private ListView mShoppingCartLv;
    private TextView mTotalTxt;
    private Button mSettleAccountsBtn;

    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_shopping_cart_activity);

        init();
    }

    void init() {
        mTotalTxt = (TextView) findViewById(R.id.txt_shopping_cart_total);
        mSettleAccountsBtn = (Button) findViewById(R.id.btn_settle_accounts);
        mSettleAccountsBtn.setOnClickListener(this);

        mUserBean = new UserBean();
        mUserShoppingCartViewPresenter = new UserShoppingCartViewPresenter();
        Intent intent = getIntent();
        mUserBean.setId(intent.getIntExtra("userid", 0));
        mUserBean.setName(intent.getStringExtra("username"));
        mUserBean.setPassword(intent.getStringExtra("userpassword"));

        mShoppingCartBeanArrayList = new ArrayList<>();
        mShoppingCartLv = (ListView) findViewById(R.id.lv_shopping_cart);
        mShoppingCartListAdapter = new ShoppingCartListAdapter(this, mShoppingCartBeanArrayList);
        mShoppingCartLv.setAdapter(mShoppingCartListAdapter);
        mShoppingCartLv.setOnItemClickListener(this);
        mShoppingCartLv.setOnItemLongClickListener(this);
    }

    @Override
    protected void onResume() {
        mShoppingCartBeanArrayList.clear();
        mUserShoppingCartViewPresenter.getUserShoppingCart(mShoppingCartBeanArrayList, mUserBean);
        mShoppingCartListAdapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(UserShoppingCartActivity.this).setTitle("注意").setMessage("请退出登录").setPositiveButton("确定", null).show();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mShoppingCartListAdapter.setSelectedIndex(position);
        mShoppingCartListAdapter.notifyDataSetChanged();

        mShoppingCartBean = mShoppingCartBeanArrayList.get(position);
        double goodsPrice = mShoppingCartBean.getGoodsBean().getPrice();
        int amounts = mShoppingCartBean.getAmounts();
        double total = goodsPrice * amounts;
        mTotalTxt.setText("总计(元): " + Double.toString(total));
        mTotalTxt.setTextSize(20);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_settle_accounts:
                LinearLayout verifyPasswordLly = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_verify_password, null);
                final TextView passwordTxt = (TextView) verifyPasswordLly.findViewById(R.id.txt_dialog_verify_password_password);

                mDialog = new AlertDialog.Builder(UserShoppingCartActivity.this).setTitle("请输入密码").setView(verifyPasswordLly).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String password = passwordTxt.getText().toString().trim();
                        if (password.contains("\'") || password.contains("\"") || password.isEmpty()) {
                            Toast.makeText(UserShoppingCartActivity.this, "输入不合法,请重新输入", Toast.LENGTH_SHORT).show();

                            //输入不合法则对话框不消失
                            try {
                                Field field = mDialog.getClass().getSuperclass().getSuperclass().getDeclaredField("mShowing");
                                field.setAccessible(true);
                                field.set(mDialog, false);
                                mDialog.dismiss();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {
                            //重新校验密码
                            UserBean userBean = new UserBean();
                            userBean.setName(mUserBean.getName());
                            userBean.setPassword(password);

                            boolean isPasswordCorrect = mUserShoppingCartViewPresenter.verifyPassword(userBean);
                            if (!isPasswordCorrect) {
                                Toast.makeText(UserShoppingCartActivity.this, "用户名密码不匹配", Toast.LENGTH_SHORT).show();

                                //密码不对,则对话框不消失
                                try {
                                    Field field = mDialog.getClass().getSuperclass().getSuperclass().getDeclaredField("mShowing");
                                    field.setAccessible(true);
                                    field.set(mDialog, false);
                                    mDialog.dismiss();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                //密码用户名匹配,则进入下订单页面
                                try {
                                    Field field = mDialog.getClass().getSuperclass().getSuperclass().getDeclaredField("mShowing");
                                    field.setAccessible(true);
                                    field.set(mDialog, true);
                                    mDialog.dismiss();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                // TODO: 16/7/26 传参数并打开下订单activity
                                Intent intent = new Intent();
                                intent.setClass(UserShoppingCartActivity.this, UserSubmitOrderActivity.class);

                                startActivity(intent);

                            }
                        }
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Field field = mDialog.getClass().getSuperclass().getSuperclass().getDeclaredField("mShowing");
                            field.setAccessible(true);
                            field.set(mDialog, true);
                            mDialog.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).show();
                break;
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        new AlertDialog.Builder(this).setTitle("警告").setMessage("确定从购物车删除该商品?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mUserShoppingCartViewPresenter.delFromShoppingCart(mShoppingCartBeanArrayList.get(position));
                mShoppingCartBeanArrayList.clear();
                mUserShoppingCartViewPresenter.getUserShoppingCart(mShoppingCartBeanArrayList, mUserBean);
                mShoppingCartListAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton("取消", null).show();
        return false;
    }
}
