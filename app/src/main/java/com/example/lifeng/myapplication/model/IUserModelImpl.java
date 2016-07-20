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

package com.example.lifeng.myapplication.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.bean.UserBean;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description IUserModel接口实现类
 */
public class IUserModelImpl implements IUserModel {
    private MyDatabaseHelper mDatabaseHelper;

    public IUserModelImpl() {
        mDatabaseHelper = MyDatabaseHelper.getInstantce();
    }

    @Override
    public boolean addUser(UserBean userBean) {
        return false;
    }

    @Override
    public boolean delUser(UserBean userBean) {
        return false;
    }

    @Override
    public boolean userLogin(UserBean userBean) {
        //得到输入的用户名和密码
        String mUserName = userBean.getName();
        String mUserPassword = userBean.getPassword();
        boolean result = false;
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_user where username=? and userpassword=?", new String[]{mUserName, mUserPassword});
            if (cursor.moveToFirst()) {
                result = true;
            }
            cursor.close();
            db.close();
        }
        Log.e(">>>>>", "普通用户登录" + Boolean.toString(result));
        return result;
    }

    @Override
    public boolean userLogout(UserBean userBean) {
        return false;
    }

    @Override
    public boolean modifyUserInfo(UserBean userBean) {
        return false;
    }

    @Override
    public boolean addTOShoppingCart(GoodsBean goodsBean) {
        return false;
    }

    @Override
    public boolean verifyAgain(UserBean userBean) {
        return false;
    }

    @Override
    public ArrayList<UserBean> getUsers() {
        return null;
    }
}
