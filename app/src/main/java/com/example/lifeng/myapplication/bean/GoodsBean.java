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
    private ArrayList<String> imagesArrayList;
    /**
     * 商品数量
     */
    private int mAmounts;
}
