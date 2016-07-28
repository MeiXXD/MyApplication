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
        String goodsname = orderBean.getGoodsName();
        double goodsprice = orderBean.getGoodsPrice();
        int goodsamounts = orderBean.getAmounts();

        String datetime = orderBean.getDateTime();
        int status = orderBean.getStatus();
        double account = orderBean.getAccount();

        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("insert into tb_order(userid,phone,address,goodsid,goodsname,goodsprice,goodsamounts,datetime,orderstatus,orderaccount) values(" + userid + ",\"" + phone + "\",\"" + address + "\"," + goodsid + ",\"" + goodsname + "\"," + goodsprice + "," + goodsamounts + ",\"" + datetime + "\"," + status + "," + account + ")");
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
            Cursor cursor = db.rawQuery("select * from tb_order where userid=" + userid, null);
            while (cursor.moveToNext()) {
                orderBean = new OrderBean();
                orderBean.setId(cursor.getInt(cursor.getColumnIndex("orderid")));
                orderBean.setUserId(userid);
                orderBean.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                orderBean.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                orderBean.setGoodsId(cursor.getInt(cursor.getColumnIndex("goodsid")));
                orderBean.setGoodsName(cursor.getString(cursor.getColumnIndex("goodsname")));
                orderBean.setGoodsPrice(cursor.getDouble(cursor.getColumnIndex("goodsprice")));
                orderBean.setAmounts(cursor.getInt(cursor.getColumnIndex("goodsamounts")));
                orderBean.setDateTime(cursor.getString(cursor.getColumnIndex("datetime")));
                orderBean.setStatus(cursor.getInt(cursor.getColumnIndex("orderstatus")));
                orderBean.setAccount(cursor.getDouble(cursor.getColumnIndex("orderaccount")));

                orderBeanArrayList.add(orderBean);
            }
            cursor.close();
        }
        db.close();
    }

    @Override
    public void getAllOrders(ArrayList<OrderBean> orderBeanArrayList) {
        OrderBean orderBean;
        SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_order", null);
            while (cursor.moveToNext()) {
                orderBean = new OrderBean();
                orderBean.setId(cursor.getInt(cursor.getColumnIndex("orderid")));
                orderBean.setUserId(cursor.getInt(cursor.getColumnIndex("userid")));
                orderBean.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                orderBean.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                orderBean.setGoodsId(cursor.getInt(cursor.getColumnIndex("goodsid")));
                orderBean.setGoodsName(cursor.getString(cursor.getColumnIndex("goodsname")));
                orderBean.setGoodsPrice(cursor.getDouble(cursor.getColumnIndex("goodsprice")));
                orderBean.setAmounts(cursor.getInt(cursor.getColumnIndex("goodsamounts")));
                orderBean.setDateTime(cursor.getString(cursor.getColumnIndex("datetime")));
                orderBean.setStatus(cursor.getInt(cursor.getColumnIndex("orderstatus")));
                orderBean.setAccount(cursor.getDouble(cursor.getColumnIndex("orderaccount")));

                orderBeanArrayList.add(orderBean);
            }
            cursor.close();
        }
        db.close();
    }

    @Override
    public void searchOrders(ArrayList<OrderBean> orderBeanArrayList, UserBean userBean, String keyword) {
        int userid = userBean.getId();
        OrderBean orderBean;
        SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_order where userid=" + userid + " and goodsname like '%" + keyword + "%' or goodsid like '%" + keyword + "%' or orderid like '%" + keyword + "%'", null);
            while (cursor.moveToNext()) {
                orderBean = new OrderBean();
                orderBean.setId(cursor.getInt(cursor.getColumnIndex("orderid")));
                orderBean.setUserId(userid);
                orderBean.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                orderBean.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                orderBean.setGoodsId(cursor.getInt(cursor.getColumnIndex("goodsid")));
                orderBean.setGoodsName(cursor.getString(cursor.getColumnIndex("goodsname")));
                orderBean.setGoodsPrice(cursor.getDouble(cursor.getColumnIndex("goodsprice")));
                orderBean.setAmounts(cursor.getInt(cursor.getColumnIndex("goodsamounts")));
                orderBean.setDateTime(cursor.getString(cursor.getColumnIndex("datetime")));
                orderBean.setStatus(cursor.getInt(cursor.getColumnIndex("orderstatus")));
                orderBean.setAccount(cursor.getDouble(cursor.getColumnIndex("orderaccount")));

                orderBeanArrayList.add(orderBean);
            }
            cursor.close();
        }
        db.close();
    }

    @Override
    public void getOrderStatusKinds(ArrayList<String> stringArrayList, UserBean userBean) {
        int userid = userBean.getId();
        stringArrayList.add("全部");
        SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select orderstatus from tb_order where userid=" + userid + " group by orderstatus", null);
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
        if (status.equals("全部")) {
            getUsersAllOrders(orderBeanArrayList, userBean);
        } else {
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
                Cursor cursor = db.rawQuery("select * from tb_order where userid=" + userid + " and orderstatus=" + mStatus, null);
                while (cursor.moveToNext()) {
                    orderBean = new OrderBean();
                    orderBean.setId(cursor.getInt(cursor.getColumnIndex("orderid")));
                    orderBean.setUserId(userid);
                    orderBean.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                    orderBean.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                    orderBean.setGoodsId(cursor.getInt(cursor.getColumnIndex("goodsid")));
                    orderBean.setGoodsName(cursor.getString(cursor.getColumnIndex("goodsname")));
                    orderBean.setGoodsPrice(cursor.getDouble(cursor.getColumnIndex("goodsprice")));
                    orderBean.setAmounts(cursor.getInt(cursor.getColumnIndex("goodsamounts")));
                    orderBean.setDateTime(cursor.getString(cursor.getColumnIndex("datetime")));
                    orderBean.setStatus(cursor.getInt(cursor.getColumnIndex("orderstatus")));
                    orderBean.setAccount(cursor.getDouble(cursor.getColumnIndex("orderaccount")));

                    orderBeanArrayList.add(orderBean);
                }
                cursor.close();
            }
            db.close();
        }
    }

    @Override
    public void getOrdersByStatus(ArrayList<OrderBean> orderBeanArrayList, String status) {
        if (status.equals("全部")) {
            getAllOrders(orderBeanArrayList);
        } else {
            int mStatus = -2;
            if (status.equals("订单驳回")) {
                mStatus = -1;
            } else if (status.equals("等待卖家确认")) {
                mStatus = 0;
            } else {
                mStatus = 1;
            }

            OrderBean orderBean;
            SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
            if (db.isOpen()) {
                Cursor cursor = db.rawQuery("select * from tb_order where orderstatus=" + mStatus, null);
                while (cursor.moveToNext()) {
                    orderBean = new OrderBean();
                    orderBean.setId(cursor.getInt(cursor.getColumnIndex("orderid")));
                    orderBean.setUserId(cursor.getInt(cursor.getColumnIndex("userid")));
                    orderBean.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                    orderBean.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                    orderBean.setGoodsId(cursor.getInt(cursor.getColumnIndex("goodsid")));
                    orderBean.setGoodsName(cursor.getString(cursor.getColumnIndex("goodsname")));
                    orderBean.setGoodsPrice(cursor.getDouble(cursor.getColumnIndex("goodsprice")));
                    orderBean.setAmounts(cursor.getInt(cursor.getColumnIndex("goodsamounts")));
                    orderBean.setDateTime(cursor.getString(cursor.getColumnIndex("datetime")));
                    orderBean.setStatus(cursor.getInt(cursor.getColumnIndex("orderstatus")));
                    orderBean.setAccount(cursor.getDouble(cursor.getColumnIndex("orderaccount")));

                    orderBeanArrayList.add(orderBean);
                }
                cursor.close();
            }
            db.close();
        }
    }
}
