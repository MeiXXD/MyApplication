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

/**
 * @author lifeng
 * @description 商品bean
 * @date 16/7/18
 */
public class GoodsBean {
    /**
     * 产品编号
     */
    private int mId;
    /**
     * 产品价格
     */
    private double mPrice;
    /**
     * 产品描述
     */
    private String mDescription;
    /**
     * 产品名称
     */
    private String mName;
    /**
     * 简单的描述
     */
    private String mBriefDescription;
    /**
     * 产品图片
     */
    private ArrayList<String> mImagesArrayList;
    /**
     * 商品数量
     */
    private int mAmounts;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getBriefDescription() {
        return mBriefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        mBriefDescription = briefDescription;
    }

    public ArrayList<String> getImagesArrayList() {
        return mImagesArrayList;
    }

    public void setImagesArrayList(ArrayList<String> imagesArrayList) {
        mImagesArrayList = imagesArrayList;
    }

    public int getAmounts() {
        return mAmounts;
    }

    public void setAmounts(int amounts) {
        mAmounts = amounts;
    }
}
