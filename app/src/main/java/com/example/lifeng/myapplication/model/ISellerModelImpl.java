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

import com.example.lifeng.myapplication.bean.SellerBean;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description ISellerModel接口实现类
 */
public class ISellerModelImpl implements ISellerModel {
    private MyDatabaseHelper mDatabaseHelper;

    public ISellerModelImpl() {
        mDatabaseHelper = MyDatabaseHelper.getInstantce();
    }

    @Override
    public boolean addSeller(SellerBean sellerBean) {
        boolean result = false;
        //得到输入的用户名和密码
        String mSellerName = sellerBean.getName();
        String mSellerPassword = sellerBean.getPassword();
        int mSellerStatus = sellerBean.getStatus();
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_seller where sellername=\"" + mSellerName + "\"", null);
            if (cursor.moveToFirst()) {
                result = false;
            } else {
                db.execSQL("insert into tb_seller(sellername,sellerpassword,sellerstatus) values(\"" + mSellerName + "\",\"" + mSellerPassword + "\"," + mSellerStatus + ")");
                result = true;
            }
            db.close();
        }
        return result;
    }

    @Override
    public boolean sellerLogin(SellerBean sellerBean) {
        //得到输入的用户名和密码
        String mSellerName = sellerBean.getName();
        String mSellerPassword = sellerBean.getPassword();
        boolean result = false;
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_seller where sellername=? and sellerpassword=?", new String[]{mSellerName, mSellerPassword});
            if (cursor.moveToFirst()) {
                result = true;
            }
            cursor.close();
            db.close();
        }
        Log.e(">>>>>", "销售商登录" + Boolean.toString(result));
        return result;
    }

    @Override
    public void getSellers(ArrayList<SellerBean> sellerBeanArrayList) {
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_seller", null);
            SellerBean sellerBean = null;
            while (cursor.moveToNext()) {
                sellerBean = new SellerBean();
                sellerBean.setId(cursor.getInt(cursor.getColumnIndex("sellerid")));
                sellerBean.setName(cursor.getString(cursor.getColumnIndex("sellername")));
                sellerBean.setPassword(cursor.getString(cursor.getColumnIndex("sellerpassword")));
                sellerBean.setStatus(cursor.getInt(cursor.getColumnIndex("sellerstatus")));
                sellerBeanArrayList.add(sellerBean);
            }
        }
    }

    @Override
    public void updateSellerStatus(SellerBean sellerBean) {
        String mSellerName = sellerBean.getName();
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("update tb_seller set sellerstatus=" + sellerBean.getStatus() + " where username=\"" + mSellerName + "\"");
            db.close();
        }
        Log.e(">>>>>", "销售商登录状态更新成功");
    }

    @Override
    public void delSeller(SellerBean sellerBean) {
        int mSellerId = sellerBean.getId();
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("delete from tb_seller where sellerid=" + mSellerId);
            db.close();
        }
        Log.e(">>>>>", "销售商删除成功");
    }
}