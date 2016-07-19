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

package com.example.lifeng.myapplication.bean;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author lifeng
 * @description 订单bean
 * @date 16/7/18
 */
public class OrderBean {
    /**
     * 订单编号
     */
    private int mId;
    /**
     * 订单日期
     */
    private Date mDate;
    /**
     * 用户
     */
    private UserBean mUserBean;
    /**
     * 订单包含的商品(一个订单可能包含多个商品)
     */
    private ArrayList<GoodsBean> mGoodsBeanArrayList;
    /**
     * 订单状态
     */
    private int mStatus;
    /**
     * 总计
     */
    private double mAccount;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public UserBean getUserBean() {
        return mUserBean;
    }

    public void setUserBean(UserBean userBean) {
        mUserBean = userBean;
    }

    public ArrayList<GoodsBean> getGoodsBeanArrayList() {
        return mGoodsBeanArrayList;
    }

    public void setGoodsBeanArrayList(ArrayList<GoodsBean> goodsBeanArrayList) {
        mGoodsBeanArrayList = goodsBeanArrayList;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public double getAccount() {
        return mAccount;
    }

    public void setAccount(double account) {
        mAccount = account;
    }
}
