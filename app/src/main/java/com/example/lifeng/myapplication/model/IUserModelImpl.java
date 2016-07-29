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
        boolean result = false;
        //得到输入的用户名和密码
        String mUserName = userBean.getName();
        String mUserPassword = userBean.getPassword();
        int mUserStatus = userBean.getStatus();
        int mUserIsVip = userBean.getIsVip();
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_user where username=\"" + mUserName + "\"", null);
            if (cursor.moveToFirst()) {
                Log.e(">>>>>", "普通用户添加失败");
                result = false;
            } else {
                db.execSQL("insert into tb_user(username,userpassword,userstatus,isvip) values(\"" + mUserName + "\",\"" + mUserPassword + "\"," + mUserStatus + "," + mUserIsVip + ")");
                result = true;
                Log.e(">>>>>", "普通用户添加成功");
            }
            cursor.close();
            db.close();
        }
        return result;
    }

    @Override
    public void delUser(UserBean userBean) {
        int mUserId = userBean.getId();
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("delete from tb_user where userid=" + mUserId);
            db.close();
        }
        Log.e(">>>>>", "普通用户删除成功");
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
                userBean.setId(cursor.getInt(cursor.getColumnIndex("userid")));
                result = true;
            }
            cursor.close();
            db.close();
        }
        Log.e(">>>>>", "普通用户登录" + Boolean.toString(result));
        return result;
    }

    @Override
    public void modifyUserInfo(UserBean userBean) {
        int id = userBean.getId();
        String password = userBean.getPassword();
        String phone = userBean.getPhone();
        String email = userBean.getEmail();
        String address = userBean.getAddress();

        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("update tb_user set userpassword=\"" + password + "\",phone=\"" + phone + "\",mail=\"" + email + "\",address=\"" + address + "\" where userid=" + id);
        }
        db.close();
        Log.e(">>>>>", "用户信息更新成功");
    }

    @Override
    public UserBean getUserInfo(UserBean userBean) {
        UserBean mUserBean = null;
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_user where userid=" + userBean.getId(), null);
            if (cursor.moveToNext()) {
                mUserBean = new UserBean();
                mUserBean.setId(cursor.getInt(cursor.getColumnIndex("userid")));
                mUserBean.setName(cursor.getString(cursor.getColumnIndex("username")));
                mUserBean.setPassword(cursor.getString(cursor.getColumnIndex("userpassword")));
                mUserBean.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                mUserBean.setEmail(cursor.getString(cursor.getColumnIndex("mail")));
                mUserBean.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                mUserBean.setIsVip(cursor.getInt(cursor.getColumnIndex("isvip")));
            }
            cursor.close();
            db.close();
        }
        Log.e(">>>>>", "用户信息获取成功");
        return mUserBean;
    }

    @Override
    public boolean verifyPassword(UserBean userBean) {
        boolean result = false;
        String name = userBean.getName();
        String password = userBean.getPassword();
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_user where username=\"" + name + "\" and userpassword=\"" + password + "\"", null);
            if (cursor.moveToFirst()) {
                result = true;
            }
            cursor.close();
            db.close();
        }
        Log.e(">>>>>", "用户密码获再次鉴定:" + Boolean.toString(result));
        return result;
    }

    @Override
    public void getUsers(ArrayList<UserBean> userBeanArrayList) {
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_user", null);
            UserBean userBean = null;
            while (cursor.moveToNext()) {
                userBean = new UserBean();
                userBean.setId(cursor.getInt(cursor.getColumnIndex("userid")));
                userBean.setName(cursor.getString(cursor.getColumnIndex("username")));
                userBean.setPassword(cursor.getString(cursor.getColumnIndex("userpassword")));
                userBean.setStatus(cursor.getInt(cursor.getColumnIndex("userstatus")));
                userBean.setIsVip(cursor.getInt(cursor.getColumnIndex("isvip")));
                userBeanArrayList.add(userBean);
            }
            cursor.close();
            db.close();
        }
        Log.e(">>>>>", "用户列表获取成功");
    }

    @Override
    public void updateUserStatus(UserBean userBean) {
        String mUserName = userBean.getName();
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("update tb_user set userstatus=" + userBean.getStatus() + " where username=\"" + mUserName + "\"");
            db.close();
        }
        Log.e(">>>>>", "用户登录状态更新为:" + Integer.toString(userBean.getStatus()));
    }

    @Override
    public boolean setUserIsVip(UserBean userBean) {
        String mUserName = userBean.getName();
        int isVip = 0;
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_user where username=\"" + mUserName + "\"", null);
            if (cursor.moveToNext()) {
                isVip = cursor.getInt(cursor.getColumnIndex("isvip"));
            }
            if (1 == isVip) {
                return false;
            } else {
                isVip = userBean.getIsVip();
                db.execSQL("update tb_user set isvip=" + isVip + " where username=\"" + mUserName + "\"");
            }
            cursor.close();
            db.close();
        }
        Log.e(">>>>>", "用户VIP信息设置为:" + Integer.toString(isVip));
        return true;
    }
}
