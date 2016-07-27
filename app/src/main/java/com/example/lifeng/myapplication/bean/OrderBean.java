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
     * 订单时间
     */
    private String mDateTime;

    /**
     * 订单状态
     */
    private int mStatus;

    /**
     * 购物车bean,包含userbean和goodsbean,以及amounts
     */
    private ShoppingCartBean mShoppingCartBean;
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

    public String getDateTime() {
        return mDateTime;
    }

    public void setDateTime(String datetime) {
        mDateTime = datetime;
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

    public ShoppingCartBean getShoppingCartBean() {
        return mShoppingCartBean;
    }

    public void setShoppingCartBean(ShoppingCartBean shoppingCartBean) {
        mShoppingCartBean = shoppingCartBean;
    }
}
