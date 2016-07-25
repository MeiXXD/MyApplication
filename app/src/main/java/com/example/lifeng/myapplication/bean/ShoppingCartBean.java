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
 * @version 1.0 16/7/24
 * @description 购物车Bean
 */
public class ShoppingCartBean {
    /**
     * 编号ID
     */
    private int mId;
    /**
     * 数量
     */
    private int mAmounts;
    /**
     * 用户
     */
    private UserBean mUserBean;
    /**
     * 商品
     */
    private GoodsBean mGoodsBean;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getAmounts() {
        return mAmounts;
    }

    public void setAmounts(int amounts) {
        mAmounts = amounts;
    }

    public UserBean getUserBean() {
        return mUserBean;
    }

    public void setUserBean(UserBean userBean) {
        mUserBean = userBean;
    }

    public GoodsBean getGoodsBean() {
        return mGoodsBean;
    }

    public void setGoodsBean(GoodsBean goodsBean) {
        mGoodsBean = goodsBean;
    }

    public ShoppingCartBean(UserBean userBean, GoodsBean goodsBean) {
        mUserBean = userBean;
        mGoodsBean = goodsBean;
    }
}
