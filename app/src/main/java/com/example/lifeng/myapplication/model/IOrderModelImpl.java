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

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lifeng.myapplication.bean.OrderBean;
import com.example.lifeng.myapplication.bean.ShoppingCartBean;
import com.example.lifeng.myapplication.bean.UserBean;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description IOrderModel实现类
 */
public class IOrderModelImpl implements IOrderModel {
    private MyDatabaseHelper mMyDatabaseHelper;

    public IOrderModelImpl() {
        if (mMyDatabaseHelper == null)
            mMyDatabaseHelper = MyDatabaseHelper.getInstantce();
    }

    @Override
    public void addOrder(OrderBean orderBean) {
        ShoppingCartBean shoppingCartBean = orderBean.getShoppingCartBean();
        int userid = shoppingCartBean.getUserBean().getId();
        int goodsid = shoppingCartBean.getGoodsBean().getId();
        int amounts = shoppingCartBean.getAmounts();
        String datetime = orderBean.getDateTime();
        int status = orderBean.getStatus();
        double account = orderBean.getAccount();

        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("insert into tb_order(userid,goodsid,goodsamounts,datetime,orderstatus,orderaccount) values(" + userid + "," + goodsid + "," + amounts + ",\"" + datetime + "\"," + status + "," + account + ")");
            db.close();
        }
        Log.e(">>>>>", "订单添加成功");
    }

    @Override
    public void updateOrderStatus(OrderBean orderBean) {

    }

    @Override
    public void getOrders(ArrayList<OrderBean> orderBeanArrayList, UserBean userBean) {

    }

    @Override
    public void searchOrders(ArrayList<OrderBean> orderBeanArrayList) {

    }
}
