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

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.lifeng.myapplication.R;

/**
 * @author lifeng
 * @version 1.0 16/7/20
 * @description 销售商管理和用户管理tab主界面
 */
public class SellerUserManagementTabActivity extends TabActivity {
    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_user_management_tab_activity);

        mTabHost = getTabHost();
        /* 去除标签下方的白线 */
        mTabHost.setPadding(mTabHost.getLeft(), mTabHost.getTop(), mTabHost.getRight(), mTabHost.getBottom() - 5);

        mTabHost.addTab(mTabHost.newTabSpec("usermanagement").setIndicator("普通用户管理").setContent(new Intent(this, UserManagementActivity.class)));
        mTabHost.addTab(mTabHost.newTabSpec("sellermanagement").setIndicator("销售商管理").setContent(new Intent(this, SellerManagementActivity.class)));

        mTabHost.getTabWidget().setBackgroundResource(R.color.colorPrimary);
        updateTab(mTabHost);
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                updateTab(mTabHost);
            }
        });
    }

    private void updateTab(TabHost tabHost) {
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).getLayoutParams().height = 150;
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(20);
            if (tabHost.getCurrentTab() == i) {//选中
                tv.setTextColor(this.getResources().getColorStateList(
                        android.R.color.holo_orange_light));
            } else {//不选中
                tv.setTextColor(this.getResources().getColorStateList(
                        android.R.color.holo_orange_dark));
            }
        }
    }
}
