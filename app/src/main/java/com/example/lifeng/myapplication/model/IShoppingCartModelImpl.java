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

import com.example.lifeng.myapplication.bean.ShoppingCartBean;

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
    }
}
