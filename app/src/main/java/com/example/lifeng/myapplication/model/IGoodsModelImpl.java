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
import com.example.lifeng.myapplication.bean.SellerBean;

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
    public GoodsBean getGoodsDetails(GoodsBean goodsBean) {
        GoodsBean mGoodsBean = null;
        int goodsId = goodsBean.getId();
        SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_goods where goodsid=" + goodsId, null);
            if (cursor.moveToFirst()) {
                mGoodsBean = new GoodsBean();
                int sellid = cursor.getInt(cursor.getColumnIndex("sellerid"));
                String sellername = cursor.getString(cursor.getColumnIndex("sellername"));
                SellerBean sellerBean = new SellerBean();
                sellerBean.setId(sellid);
                sellerBean.setName(sellername);
                mGoodsBean.setSellerBean(sellerBean);
                mGoodsBean.setName(cursor.getString(cursor.getColumnIndex("goodsname")));
                mGoodsBean.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));
                mGoodsBean.setAmounts(cursor.getInt(cursor.getColumnIndex("amounts")));
                mGoodsBean.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                mGoodsBean.setImage(cursor.getString(cursor.getColumnIndex("goodsimage")));
            }
            cursor.close();
            db.close();
        }
        Log.e(">>>>>", "商品详情获取成功");
        return mGoodsBean;
    }

    @Override
    public boolean addGoods(GoodsBean goodsBean) {
        int sellerId = goodsBean.getSellerBean().getId();
        String sellername = goodsBean.getSellerBean().getName();
        String name = goodsBean.getName();
        int amounts = goodsBean.getAmounts();
        double price = goodsBean.getPrice();
        String kind = goodsBean.getKind();
        String briefDescription = goodsBean.getBriefDescription();
        String description = goodsBean.getDescription();
        String image = goodsBean.getImage();
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("insert into tb_goods(sellerid,sellername,goodsname,amounts,price,kind,briefdescription,description,goodsimage) values(" + sellerId + ",\"" + sellername + "\",\"" + name + "\"," + amounts + "," + price + ",\"" + kind + "\" ,\"" + briefDescription + "\",\"" + description + "\",\"" + image + "\")");
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
        String goodsImage = goodsBean.getImage();

        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("update tb_goods set amounts=" + goodsAmounts + ",price=" + goodsPrice + ",kind=\"" + goodsKind + "\",briefdescription=\"" + goodsBriefDescription + "\",description=\"" + goodsDescription + "\",goodsimage=\"" + goodsImage + "\" where goodsid=" + goodsId);
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

                int sellid = cursor.getInt(cursor.getColumnIndex("sellerid"));
                String sellername = cursor.getString(cursor.getColumnIndex("sellername"));
                SellerBean sellerBean = new SellerBean();
                sellerBean.setId(sellid);
                sellerBean.setName(sellername);

                goodsBean.setSellerBean(sellerBean);
                goodsBean.setName(cursor.getString(cursor.getColumnIndex("goodsname")));
                goodsBean.setAmounts(cursor.getInt(cursor.getColumnIndex("amounts")));
                goodsBean.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));
                goodsBean.setKind(cursor.getString(cursor.getColumnIndex("kind")));
                goodsBean.setBriefDescription(cursor.getString(cursor.getColumnIndex("briefdescription")));
                goodsBean.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                goodsBean.setImage(cursor.getString(cursor.getColumnIndex("goodsimage")));
                goodsBeanArrayList.add(goodsBean);
            }
            cursor.close();
            db.close();
            Log.e(">>>>>", "商品列表获取成功");
        }
    }

    @Override
    public void getGoods(ArrayList<GoodsBean> goodsBeanArrayList, SellerBean sellerBean) {
        SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_goods where sellerid=" + sellerBean.getId(), null);
            GoodsBean goodsBean = null;
            while (cursor.moveToNext()) {
                goodsBean = new GoodsBean();
                goodsBean.setId(cursor.getInt(cursor.getColumnIndex("goodsid")));

                int sellid = cursor.getInt(cursor.getColumnIndex("sellerid"));
                String sellername = cursor.getString(cursor.getColumnIndex("sellername"));
                SellerBean sellerbean = new SellerBean();
                sellerbean.setId(sellid);
                sellerbean.setName(sellername);

                goodsBean.setSellerBean(sellerbean);
                goodsBean.setName(cursor.getString(cursor.getColumnIndex("goodsname")));
                goodsBean.setAmounts(cursor.getInt(cursor.getColumnIndex("amounts")));
                goodsBean.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));
                goodsBean.setKind(cursor.getString(cursor.getColumnIndex("kind")));
                goodsBean.setBriefDescription(cursor.getString(cursor.getColumnIndex("briefdescription")));
                goodsBean.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                goodsBean.setImage(cursor.getString(cursor.getColumnIndex("goodsimage")));
                goodsBeanArrayList.add(goodsBean);
            }
            cursor.close();
            db.close();
            Log.e(">>>>>", sellerBean.getName() + "商品列表获取成功");
        }
    }

    @Override
    public void getGoodsByKind(ArrayList<GoodsBean> goodsBeanArrayList, String kind) {
        if ("全部" == kind) {
            getGoods(goodsBeanArrayList);
        } else {
            SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
            if (db.isOpen()) {
                Cursor cursor = db.rawQuery("select * from tb_goods where kind=\"" + kind + "\"", null);
                GoodsBean goodsBean = null;
                while (cursor.moveToNext()) {
                    goodsBean = new GoodsBean();
                    goodsBean.setId(cursor.getInt(cursor.getColumnIndex("goodsid")));

                    int sellid = cursor.getInt(cursor.getColumnIndex("sellerid"));
                    String sellername = cursor.getString(cursor.getColumnIndex("sellername"));
                    SellerBean sellerBean = new SellerBean();
                    sellerBean.setId(sellid);
                    sellerBean.setName(sellername);
                    goodsBean.setSellerBean(sellerBean);

                    goodsBean.setName(cursor.getString(cursor.getColumnIndex("goodsname")));
                    goodsBean.setAmounts(cursor.getInt(cursor.getColumnIndex("amounts")));
                    goodsBean.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));
                    goodsBean.setKind(cursor.getString(cursor.getColumnIndex("kind")));
                    goodsBean.setBriefDescription(cursor.getString(cursor.getColumnIndex("briefdescription")));
                    goodsBean.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                    goodsBean.setImage(cursor.getString(cursor.getColumnIndex("goodsimage")));
                    goodsBeanArrayList.add(goodsBean);
                }
                cursor.close();
                db.close();
            }
        }
        Log.e(">>>>>", kind + "类别商品获取成功");
    }

    @Override
    public void getGoodsKinds(ArrayList<String> stringArrayList) {
        stringArrayList.add("全部");
        SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select kind from tb_goods group by kind", null);
            while (cursor.moveToNext()) {
                stringArrayList.add(cursor.getString(cursor.getColumnIndex("kind")));
            }
            cursor.close();
            db.close();
        }
        Log.e(">>>>>", "商品类别获取成功");
    }

    @Override
    public void searchGoods(ArrayList<GoodsBean> goodsBeanArrayList, String keyword) {
        SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_goods where goodsname like '%" + keyword + "%' or kind like '%" + keyword + "%' or sellername like '%" + keyword + "%'", null);
            GoodsBean goodsBean = null;
            while (cursor.moveToNext()) {
                goodsBean = new GoodsBean();
                goodsBean.setId(cursor.getInt(cursor.getColumnIndex("goodsid")));

                int sellid = cursor.getInt(cursor.getColumnIndex("sellerid"));
                String sellername = cursor.getString(cursor.getColumnIndex("sellername"));
                SellerBean sellerBean = new SellerBean();
                sellerBean.setId(sellid);
                sellerBean.setName(sellername);

                goodsBean.setSellerBean(sellerBean);
                goodsBean.setName(cursor.getString(cursor.getColumnIndex("goodsname")));
                goodsBean.setAmounts(cursor.getInt(cursor.getColumnIndex("amounts")));
                goodsBean.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));
                goodsBean.setKind(cursor.getString(cursor.getColumnIndex("kind")));
                goodsBean.setBriefDescription(cursor.getString(cursor.getColumnIndex("briefdescription")));
                goodsBean.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                goodsBean.setImage(cursor.getString(cursor.getColumnIndex("goodsimage")));
                goodsBeanArrayList.add(goodsBean);
            }
            cursor.close();
            db.close();
        }
    }

    @Override
    public void updateGoodsAmounts(GoodsBean goodsBean) {
        int temp = goodsBean.getAmounts();
        int amounts = 0;
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_goods where goodsid=" + goodsBean.getId(), null);
            if (cursor.moveToFirst()) {
                amounts = cursor.getInt(cursor.getColumnIndex("amounts"));
            }
            cursor.close();
            amounts = amounts + temp;
            db.execSQL("update tb_goods set amounts=" + amounts + " where goodsid=" + goodsBean.getId());
            db.close();
        }
        Log.e(">>>>>", "商品库存已更新");
    }
}
