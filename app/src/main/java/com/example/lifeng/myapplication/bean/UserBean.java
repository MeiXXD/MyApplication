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
 * @description 普通用户
 * @date 16/7/18
 */
public class UserBean {
    /**
     * 用户编号
     */
    private int mId;

    /**
     * 用户是否登录状态
     */
    private int mStatus;
    /**
     * 用户是否为会员,会员购买享受折扣
     */
    private int mIsVip;
    /**
     * 用户名
     */
    private String mName;
    /**
     * 用户密码
     */
    private String mPassword;
    /**
     * 手机号码
     */
    private String mPhone;

    /**
     * 邮箱
     */
    private String mEmail;
    /**
     * 收货地址
     */
    private String mAddress;

    private String mImage;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
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

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public int getIsVip() {
        return mIsVip;
    }

    public void setIsVip(int isVip) {
        mIsVip = isVip;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }
}
