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

import com.example.lifeng.myapplication.bean.AdministratorBean;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description IAdministratorModel接口实现类
 */
public class IAdministratorModelImpl implements IAdministratorModel {
    private MyDatabaseHelper mDatabaseHelper;

    public IAdministratorModelImpl() {
        mDatabaseHelper = MyDatabaseHelper.getInstantce();
    }

    @Override
    public boolean adminLogin(AdministratorBean administratorBean) {
        //得到输入的用户名和密码
        String mAdminName = administratorBean.getName();
        String mAdminPassword = administratorBean.getPassword();
        boolean result = false;
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_admin where adminname=? and adminpassword=?", new String[]{mAdminName, mAdminPassword});
            if (cursor.moveToFirst()) {
                result = true;
            }
            cursor.close();
            db.close();
        }
        Log.e(">>>>>", "管理员登录" + Boolean.toString(result));
        return result;
    }

    @Override
    public void updateAdminStatus(AdministratorBean administratorBean) {
        String mAdminName = administratorBean.getName();
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("update tb_admin set adminstatus=" + administratorBean.getStatus() + " where adminname=\"" + mAdminName + "\"");
            db.close();
        }
        Log.e(">>>>>", "管理员登录状态更新为:" + Integer.toString(administratorBean.getStatus()));
    }
}
