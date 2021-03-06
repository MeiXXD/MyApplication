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
import com.example.lifeng.myapplication.bean.SellerBean;
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

        int sellerid = orderBean.getSellerId();
        String sellername = orderBean.getSellerName();

        int goodsid = orderBean.getGoodsId();
        String goodsimage = orderBean.getGoodsImage();
        String goodsname = orderBean.getGoodsName();
        double goodsprice = orderBean.getGoodsPrice();
        int goodsamounts = orderBean.getAmounts();

        String datetime = orderBean.getDateTime();
        int status = orderBean.getStatus();
        double account = orderBean.getAccount();

        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("insert into tb_order(userid,phone,address,sellerid,sellername,goodsid,goodsname,goodsimage,goodsprice,goodsamounts,datetime,orderstatus,orderaccount) values(" + userid + ",\"" + phone + "\",\"" + address + "\"," + sellerid + ",\"" + sellername + "\"," + goodsid + ",\"" + goodsname + "\",\"" + goodsimage + "\"," + goodsprice + "," + goodsamounts + ",\"" + datetime + "\"," + status + "," + account + ")");
            db.close();
        }
        Log.e(">>>>>", "订单添加成功");
    }

    @Override
    public void delOrder(OrderBean orderBean) {
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("delete from tb_order where orderid=" + orderBean.getId());
            db.close();
        }
        Log.e(">>>>>", "订单删除成功");
    }

    @Override
    public void updateOrderStatus(OrderBean orderBean) {
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("update tb_order set orderstatus=" + orderBean.getStatus() + " where orderid=" + orderBean.getId());
        }
        db.close();
        Log.e(">>>>>", "订单状态更新成功");
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
                orderBean.setSellerId(cursor.getInt(cursor.getColumnIndex("sellerid")));
                orderBean.setSellerName(cursor.getString(cursor.getColumnIndex("sellername")));
                orderBean.setGoodsId(cursor.getInt(cursor.getColumnIndex("goodsid")));
                orderBean.setGoodsImage(cursor.getString(cursor.getColumnIndex("goodsimage")));
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
        Log.e(">>>>>", "用户订单获取成功");
    }

    @Override
    public void getAllOrders(ArrayList<OrderBean> orderBeanArrayList, SellerBean sellerBean) {
        OrderBean orderBean;
        SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_order where sellerid=" + sellerBean.getId(), null);
            while (cursor.moveToNext()) {
                orderBean = new OrderBean();
                orderBean.setId(cursor.getInt(cursor.getColumnIndex("orderid")));
                orderBean.setUserId(cursor.getInt(cursor.getColumnIndex("userid")));
                orderBean.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                orderBean.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                orderBean.setSellerId(cursor.getInt(cursor.getColumnIndex("sellerid")));
                orderBean.setSellerName(cursor.getString(cursor.getColumnIndex("sellername")));
                orderBean.setGoodsId(cursor.getInt(cursor.getColumnIndex("goodsid")));
                orderBean.setGoodsImage(cursor.getString(cursor.getColumnIndex("goodsimage")));
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
        Log.e(">>>>>", "全部订单获取成功");
    }

    @Override
    public void searchOrders(ArrayList<OrderBean> orderBeanArrayList, UserBean userBean, String keyword) {
        int userid = userBean.getId();
        OrderBean orderBean;
        SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from tb_order where userid=" + userid + " and (goodsname like '%" + keyword + "%' or goodsid like '%" + keyword + "%' or orderid like '%" + keyword + "%' or sellername like '%" + keyword + "%')", null);
            while (cursor.moveToNext()) {
                orderBean = new OrderBean();
                orderBean.setId(cursor.getInt(cursor.getColumnIndex("orderid")));
                orderBean.setUserId(userid);
                orderBean.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                orderBean.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                orderBean.setSellerId(cursor.getInt(cursor.getColumnIndex("sellerid")));
                orderBean.setSellerName(cursor.getString(cursor.getColumnIndex("sellername")));
                orderBean.setGoodsId(cursor.getInt(cursor.getColumnIndex("goodsid")));
                orderBean.setGoodsImage(cursor.getString(cursor.getColumnIndex("goodsimage")));
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
                    orderBean.setSellerId(cursor.getInt(cursor.getColumnIndex("sellerid")));
                    orderBean.setSellerName(cursor.getString(cursor.getColumnIndex("sellername")));
                    orderBean.setGoodsId(cursor.getInt(cursor.getColumnIndex("goodsid")));
                    orderBean.setGoodsImage(cursor.getString(cursor.getColumnIndex("goodsimage")));
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
        Log.e(">>>>>", "用户" + status + "订单获取成功");
    }

    @Override
    public void getOrdersByStatus(ArrayList<OrderBean> orderBeanArrayList, SellerBean sellerBean, String status) {
        if (status.equals("全部")) {
            getAllOrders(orderBeanArrayList, sellerBean);
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
                Cursor cursor = db.rawQuery("select * from tb_order where sellerid=" + sellerBean.getId() + " and orderstatus=" + mStatus, null);
                while (cursor.moveToNext()) {
                    orderBean = new OrderBean();
                    orderBean.setId(cursor.getInt(cursor.getColumnIndex("orderid")));
                    orderBean.setUserId(cursor.getInt(cursor.getColumnIndex("userid")));
                    orderBean.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                    orderBean.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                    orderBean.setSellerId(cursor.getInt(cursor.getColumnIndex("sellerid")));
                    orderBean.setSellerName(cursor.getString(cursor.getColumnIndex("sellername")));
                    orderBean.setGoodsId(cursor.getInt(cursor.getColumnIndex("goodsid")));
                    orderBean.setGoodsImage(cursor.getString(cursor.getColumnIndex("goodsimage")));
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
        Log.e(">>>>>", status + "订单获取成功");
    }
}