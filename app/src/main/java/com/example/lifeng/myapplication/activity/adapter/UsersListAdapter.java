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

package com.example.lifeng.myapplication.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.UserBean;

import java.util.List;

/**
 * @author lifeng
 * @version 1.0 16/7/21
 * @description 普通用户列表的Adapter
 */
public class UsersListAdapter extends BaseAdapter {
    private List<UserBean> mUserBeanList;
    private Activity mActivity;
    private LayoutInflater mLayoutInflater;

    public UsersListAdapter(Activity activity, List<UserBean> userBeanList) {
        mActivity = activity;
        mUserBeanList = userBeanList;
    }

    @Override
    public int getCount() {
        return mUserBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mUserBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == mLayoutInflater) {
            mLayoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (null == convertView) {
            convertView = mLayoutInflater.inflate(R.layout.users_listitem, null);
        }

        TextView userIdTxt = (TextView) convertView.findViewById(R.id.txt_users_listitem_userid);
        TextView userNameTxt = (TextView) convertView.findViewById(R.id.txt_users_listitem_username);
        TextView userPasswordTxt = (TextView) convertView.findViewById(R.id.txt_users_listitem_userpassword);
        TextView userStatusTxt = (TextView) convertView.findViewById(R.id.txt_users_listitem_userstatus);

        UserBean userBean = mUserBeanList.get(position);
        userIdTxt.setText(Integer.toString(userBean.getId()));
        userNameTxt.setText(userBean.getName());
        userPasswordTxt.setText(userBean.getPassword());
        userStatusTxt.setText(Integer.toString(userBean.getStatus()));

        return convertView;
    }
}
