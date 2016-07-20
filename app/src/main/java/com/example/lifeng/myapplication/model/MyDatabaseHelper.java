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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author lifeng
 * @version 1.0 16/7/20
 * @description 数据库
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static MyDatabaseHelper mMyDatabaseHelper;

    public static MyDatabaseHelper getInstantce(Context context) {
        if (mMyDatabaseHelper == null) {
            mMyDatabaseHelper = new MyDatabaseHelper(context);
        }
        return mMyDatabaseHelper;
    }

    public static MyDatabaseHelper getInstantce() {
        return mMyDatabaseHelper;
    }

    private MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //数据库名
    private static final String DB_NAME = "myapplication.db3";
    //版本号
    private static final int DB_VERSION = 1;
    //表名
    private static final String TABLE_ADMIN = "tb_admin";
    private static final String TABLE_USER = "tb_user";
    private static final String TABLE_SELLER = "tb_seller";
    private static final String TABLE_GOODS = "tb_goods";
    private static final String TABLE_ORDER = "tb_order";

    //表定义
    private static final String CREATE_TABLE_ADMIN_SQL = "create table " + TABLE_ADMIN + "(adminid integer primary key autoincrement,adminstatus interger not null,adminname TEXT not null,adminpassword TEXT not null)";
    private static final String CREATE_TABLE_USER_SQL = "create table " + TABLE_USER + "user(userid integer primary key autoincrement,userstatus interger not null,username TEXT not null,userpassword TEXT not null,email TEXT,address TEXT)";
    private static final String CREATE_TABLE_SELLER_SQL = "create table " + TABLE_SELLER + "(sellerid integer primary key autoincrement,sellerstatus interger not null,sellername TEXT not null,sellerpassword TEXT not null)";
    private static final String CREATE_TABLE_GOODS_SQL = "create table " + TABLE_GOODS + "(goodsid integer primary key autoincrement,goodsname TEXT,price TEXT,description TEXT,briefdescription TEXT,goodsimage TEXT)";
    private static final String CREATE_TABLE_ORDER_SQL = "create table " + TABLE_ORDER + "(orderid integer primary key autoincrement,userid integer not null,goodsid integer not null,orderdate TEXT,orderstatus integer,orderaccount TEXT)";

    //初始管理员插入
    private static final String INSERT_ADMIN_SQL = "insert into tb_admin (adminid,adminstatus,adminname,adminpassword) values(1,0,'admin','admin')";
    private static final String DQL_DROP = "drop table if exists ";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //首次使用drop表
        db.execSQL(DQL_DROP + TABLE_ADMIN);
        db.execSQL(DQL_DROP + TABLE_USER);
        db.execSQL(DQL_DROP + TABLE_SELLER);
        db.execSQL(DQL_DROP + TABLE_GOODS);
        db.execSQL(DQL_DROP + TABLE_ORDER);
        //创建表
        db.execSQL(CREATE_TABLE_ADMIN_SQL);
        Log.e(">>>", CREATE_TABLE_ADMIN_SQL);
        db.execSQL(CREATE_TABLE_USER_SQL);
        db.execSQL(CREATE_TABLE_SELLER_SQL);
        db.execSQL(CREATE_TABLE_GOODS_SQL);
        db.execSQL(CREATE_TABLE_ORDER_SQL);
        //添加管理员
        db.execSQL(INSERT_ADMIN_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("数据库版本更新," + oldVersion + "----------->" + newVersion);
    }
}