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
     * 用户ID
     */
    private int mUserId;

    /**
     * 手机号
     */
    private String mPhone;

    /**
     * 收获地址
     */
    private String mAddress;

    /**
     * 销售商ID
     */
    private int mSellerId;

    /**
     * 销售商名称
     */
    private String mSellerName;

    /**
     * 商品ID
     */
    private int mGoodsId;

    /**
     * 商品名
     */
    private String mGoodsName;

    /**
     * 商品价格
     */
    private Double mGoodsPrice;

    /**
     * 购买数量
     */
    private int mAmounts;

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

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public int getGoodsId() {
        return mGoodsId;
    }

    public void setGoodsId(int goodsId) {
        mGoodsId = goodsId;
    }

    public Double getGoodsPrice() {
        return mGoodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        mGoodsPrice = goodsPrice;
    }

    public int getAmounts() {
        return mAmounts;
    }

    public void setAmounts(int amounts) {
        mAmounts = amounts;
    }

    public String getGoodsName() {
        return mGoodsName;
    }

    public void setGoodsName(String goodsName) {
        mGoodsName = goodsName;
    }

    public String getSellerName() {
        return mSellerName;
    }

    public void setSellerName(String sellerName) {
        mSellerName = sellerName;
    }

    public int getSellerId() {
        return mSellerId;
    }

    public void setSellerId(int sellerId) {
        mSellerId = sellerId;
    }
}
