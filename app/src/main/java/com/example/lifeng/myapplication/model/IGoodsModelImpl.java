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

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description IGoodsModel接口的实现类
 */
public class IGoodsModelImpl implements IGoodsModel {
    private MyDatabaseHelper mMyDatabaseHelper;

    public IGoodsModelImpl() {
        mMyDatabaseHelper = MyDatabaseHelper.getInstantce();
    }

    @Override
    public GoodsBean getGoodsInfo(GoodsBean goodsBean) {
        return null;
    }

    @Override
    public boolean addGoods(GoodsBean goodsBean) {
        String name = goodsBean.getName();
        int amounts = goodsBean.getAmounts();
        double price = goodsBean.getPrice();
        String kind = goodsBean.getKind();
        String briefDescription = goodsBean.getBriefDescription();
        String description = goodsBean.getDescription();
        // TODO: 16/7/22 图片未添加
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("insert into tb_goods(goodsname,amounts,price,kind,briefdescription,description) values(\"" + name + "\"," + amounts + "," + price + ",\"" + kind + "\" ,\"" + briefDescription + "\",\"" + description + "\")");
            db.close();
        }
        Log.e(">>>>>", "商品添加成功");
        return true;
    }

    @Override
    public void delGoods(GoodsBean goodsBean) {
        int goodsId = goodsBean.getId();
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("delete from tb_goods where goodsid=" + goodsId);
            db.close();
        }
        Log.e(">>>>>", "商品删除成功");
    }

    @Override
    public void modifyGoods(GoodsBean goodsBean) {
        int goodsId = goodsBean.getId();
        int goodsAmounts = goodsBean.getAmounts();
        double goodsPrice = goodsBean.getPrice();
        String goodsKind = goodsBean.getKind();
        String goodsBriefDescription = goodsBean.getBriefDescription();
        String goodsDescription = goodsBean.getDescription();

        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("update tb_goods set amounts=" + goodsAmounts + ",price=" + goodsPrice + ",kind=\"" + goodsKind + "\",briefdescription=\"" + goodsBriefDescription + "\",description=\"" + goodsDescription + "\" where goodsid=" + goodsId);
            db.close();
        }
        Log.e(">>>>>", "商品信息更新成功");
    }

    @Override
    public void getGoods(ArrayList<GoodsBean> goodsBeanArrayList) {
        SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_goods", null);
            GoodsBean goodsBean = null;
            while (cursor.moveToNext()) {
                goodsBean = new GoodsBean();
                goodsBean.setId(cursor.getInt(cursor.getColumnIndex("goodsid")));
                goodsBean.setName(cursor.getString(cursor.getColumnIndex("goodsname")));
                goodsBean.setAmounts(cursor.getInt(cursor.getColumnIndex("amounts")));
                goodsBean.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));
                goodsBean.setKind(cursor.getString(cursor.getColumnIndex("kind")));
                goodsBean.setBriefDescription(cursor.getString(cursor.getColumnIndex("briefdescription")));
                goodsBean.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                goodsBeanArrayList.add(goodsBean);
            }
        }
    }

    @Override
    public ArrayList<GoodsBean> searchGoods(GoodsBean goodsBean) {
        return null;
    }
}
