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
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.lifeng.myapplication.bean.AdministratorBean;
import com.example.lifeng.myapplication.presenter.AdministratorLoginViewPresenter;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 管理员登录Activity
 */
public class AdministratorLoginActivity extends AppCompatActivity implements IAdministratorLoginView {
    private AdministratorLoginViewPresenter administratorLoginViewPresenter;
    private AdministratorBean administratorBean;

    private void init() {
        administratorLoginViewPresenter = new AdministratorLoginViewPresenter();
    }

    @Override
    public boolean adminLogin(AdministratorBean administratorBean) {
        return administratorLoginViewPresenter.adminLogin(administratorBean);
    }

    @Override
    public AdministratorBean getAdministratorInfo() {
        administratorBean = new AdministratorBean();
        //得到界面的输入数据
        administratorBean.setName("hahha");
        administratorBean.setPassword("111111");
        return administratorBean;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
}
