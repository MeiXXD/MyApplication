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

import com.example.lifeng.myapplication.bean.OrderBean;
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
        int userid = orderBean.getUserId();
        String phone = orderBean.getPhone();
        String address = orderBean.getAddress();

        int goodsid = orderBean.getGoodsId();
        double goodsprice = orderBean.getGoodsPrice();
        int goodsamounts = orderBean.getAmounts();

        String datetime = orderBean.getDateTime();
        int status = orderBean.getStatus();
        double account = orderBean.getAccount();

        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("insert into tb_order(userid,phone,address,goodsid,goodsprice,goodsamounts,datetime,orderstatus,orderaccount) values(" + userid + ",\"" + phone + "\",\"" + address + "\"," + goodsid + "," + goodsprice + "," + goodsamounts + ",\"" + datetime + "\"," + status + "," + account + ")");
            db.close();
        }
        Log.e(">>>>>", "订单添加成功");
    }

    @Override
    public void updateOrderStatus(OrderBean orderBean) {

    }

    @Override
    public void getUsersAllOrders(ArrayList<OrderBean> orderBeanArrayList, UserBean userBean) {
        int userid = userBean.getId();
        OrderBean orderBean;
        SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor1 = db.rawQuery("select * from tb_order where userid=" + userid, null);
            while (cursor1.moveToNext()) {
                orderBean = new OrderBean();
                orderBean.setId(cursor1.getInt(cursor1.getColumnIndex("orderid")));
                orderBean.setUserId(userid);
                orderBean.setPhone(cursor1.getString(cursor1.getColumnIndex("phone")));
                orderBean.setAddress(cursor1.getString(cursor1.getColumnIndex("address")));
                orderBean.setGoodsId(cursor1.getInt(cursor1.getColumnIndex("goodsid")));
                orderBean.setGoodsPrice(cursor1.getDouble(cursor1.getColumnIndex("goodsprice")));
                orderBean.setAmounts(cursor1.getInt(cursor1.getColumnIndex("goodsamounts")));
                orderBean.setDateTime(cursor1.getString(cursor1.getColumnIndex("datetime")));
                orderBean.setStatus(cursor1.getInt(cursor1.getColumnIndex("orderstatus")));
                orderBean.setAccount(cursor1.getDouble(cursor1.getColumnIndex("orderaccount")));

                Cursor cursor2 = db.rawQuery("select * from tb_goods where goodsid=" + orderBean.getGoodsId(), null);
                if (cursor2.moveToFirst()) {
                    orderBean.setGoodsName(cursor2.getString(cursor2.getColumnIndex("goodsname")));
                }
                cursor2.close();

                orderBeanArrayList.add(orderBean);
            }
            cursor1.close();
        }
        db.close();
    }

    @Override
    public void searchOrders(ArrayList<OrderBean> orderBeanArrayList) {

    }

    @Override
    public void getOrderStatusKinds(ArrayList<String> stringArrayList) {
        stringArrayList.add("全部");
        SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select orderstatus from tb_order group by orderstatus", null);
            while (cursor.moveToNext()) {
                int status = cursor.getInt(cursor.getColumnIndex("orderstatus"));
                if (-1 == status) {
                    stringArrayList.add("订单驳回");
                } else if (0 == status) {
                    stringArrayList.add("等待卖家确认");
                } else {
                    stringArrayList.add("确认发货");
                }
            }
            cursor.close();
            db.close();
        }
    }

    @Override
    public void getOrdersByStatus(ArrayList<OrderBean> orderBeanArrayList, UserBean userBean, String status) {
        int mStatus = -2;
        if (status.equals("订单驳回")) {
            mStatus = -1;
        } else if (status.equals("等待卖家确认")) {
            mStatus = 0;
        } else {
            mStatus = 1;
        }
        int userid = userBean.getId();
        OrderBean orderBean;
        SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor1 = db.rawQuery("select * from tb_order where userid=" + userid + " and orderstatus=" + mStatus, null);
            while (cursor1.moveToNext()) {
                orderBean = new OrderBean();
                orderBean.setId(cursor1.getInt(cursor1.getColumnIndex("orderid")));
                orderBean.setUserId(userid);
                orderBean.setPhone(cursor1.getString(cursor1.getColumnIndex("phone")));
                orderBean.setAddress(cursor1.getString(cursor1.getColumnIndex("address")));
                orderBean.setGoodsId(cursor1.getInt(cursor1.getColumnIndex("goodsid")));
                orderBean.setGoodsPrice(cursor1.getDouble(cursor1.getColumnIndex("goodsprice")));
                orderBean.setAmounts(cursor1.getInt(cursor1.getColumnIndex("goodsamounts")));
                orderBean.setDateTime(cursor1.getString(cursor1.getColumnIndex("datetime")));
                orderBean.setStatus(cursor1.getInt(cursor1.getColumnIndex("orderstatus")));
                orderBean.setAccount(cursor1.getDouble(cursor1.getColumnIndex("orderaccount")));

                Cursor cursor2 = db.rawQuery("select * from tb_goods where goodsid=" + orderBean.getGoodsId(), null);
                if (cursor2.moveToFirst()) {
                    orderBean.setGoodsName(cursor2.getString(cursor2.getColumnIndex("goodsname")));
                }
                cursor2.close();

                orderBeanArrayList.add(orderBean);
            }
            cursor1.close();
        }
        db.close();
    }
}
