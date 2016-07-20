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

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.SellerBean;
import com.example.lifeng.myapplication.presenter.SellerManagementViewPresenter;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 销售商管理Activity
 */
public class SellerManagementActivity extends AppCompatActivity implements ISellerManagementView, View.OnClickListener {
    private SellerBean mSellerBean;
    private SellerManagementViewPresenter mSellerManagementViewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_management_activity);
        //setTitle("销售商管理");
    }

    @Override
    public boolean getAdminInput() {
        return false;
    }

    @Override
    public void setOutput(ArrayList<SellerBean> sellerBeanArrayList) {

    }

    @Override
    public void onClick(View v) {

    }
}
