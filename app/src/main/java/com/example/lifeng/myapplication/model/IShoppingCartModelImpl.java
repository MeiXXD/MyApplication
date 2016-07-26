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
import com.example.lifeng.myapplication.bean.ShoppingCartBean;
import com.example.lifeng.myapplication.bean.UserBean;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/24
 * @description ShoppingCartModel实现类
 */
public class IShoppingCartModelImpl implements IShoppingCartModel {
    private MyDatabaseHelper mMyDatabaseHelper;

    public IShoppingCartModelImpl() {
        mMyDatabaseHelper = MyDatabaseHelper.getInstantce();
    }

    @Override
    public void addToShoppingCart(ShoppingCartBean shoppingCartBean) {
        int goodsId = shoppingCartBean.getGoodsBean().getId();
        int userId = shoppingCartBean.getUserBean().getId();
        int amounts = shoppingCartBean.getAmounts();
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_shopping_cart where userid=" + userId + " and goodsid=" + goodsId, null);
            if (cursor.moveToFirst()) {
                //存在则更新数量
                amounts = cursor.getInt(cursor.getColumnIndex("amounts")) + amounts;
                db.execSQL("update tb_shopping_cart set amounts=" + amounts + " where userid=" + userId + " and goodsid=" + goodsId);
            } else {
                //不存在则插入
                db.execSQL("insert into tb_shopping_cart(userid,goodsid,amounts) values(" + userId + "," + goodsId + "," + amounts + ")");
            }
            cursor.close();
            db.close();
        }
        Log.e(">>>>>", "添加购物车成功");
    }

    @Override
    public void getUserShoppingCart(ArrayList<ShoppingCartBean> shoppingCartBeanArrayList, UserBean userBean) {
        int userId = userBean.getId();
        GoodsBean goodsBean = null;
        ShoppingCartBean shoppingCartBean = null;
        SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor1 = db.rawQuery("select * from tb_shopping_cart where userid=" + userId, null);
            while (cursor1.moveToNext()) {
                int goodsId = cursor1.getInt(cursor1.getColumnIndex("goodsid"));
                goodsBean = new GoodsBean();
                goodsBean.setId(goodsId);
                Cursor cursor2 = db.rawQuery("select * from tb_goods where goodsid=" + goodsId, null);
                while (cursor2.moveToNext()) {
                    goodsBean.setPrice(cursor2.getDouble(cursor2.getColumnIndex("price")));
                    goodsBean.setName(cursor2.getString(cursor2.getColumnIndex("goodsname")));
                }
                shoppingCartBean = new ShoppingCartBean(userBean, goodsBean);
                Cursor cursor3 = db.rawQuery("select * from tb_shopping_cart where userid=" + userId + " and goodsid=" + goodsId, null);
                if (cursor3.moveToFirst()) {
                    shoppingCartBean.setAmounts(cursor3.getInt(cursor3.getColumnIndex("amounts")));
                }
                shoppingCartBeanArrayList.add(shoppingCartBean);

                cursor3.close();
                cursor2.close();
            }
            cursor1.close();
            db.close();
        }
        Log.e(">>>>>", "获取用户购物车成功");
    }

    @Override
    public void delFromShoppingCart(ShoppingCartBean shoppingCartBean) {
        int userid = shoppingCartBean.getUserBean().getId();
        int goodsid = shoppingCartBean.getGoodsBean().getId();
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("delete from tb_shopping_cart where userid=" + userid + " and goodsid=" + goodsid);
            db.close();
        }
        Log.e(">>>>>", "从购物车删除商品成功");
    }
}
