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

import com.example.lifeng.myapplication.bean.GoodsBean;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description IGoodsModel接口的实现类
 */
public class IGoodsModelImpl implements IGoodsModel {
    @Override
    public GoodsBean getGoodsInfo(GoodsBean goodsBean) {
        return null;
    }

    @Override
    public boolean addGoods(GoodsBean goodsBean) {
        return false;
    }

    @Override
    public boolean delGoods(GoodsBean goodsBean) {
        return false;
    }

    @Override
    public boolean modifyGoods(GoodsBean goodsBean) {
        return false;
    }

    @Override
    public ArrayList<GoodsBean> getAllGoods() {
        return null;
    }

    @Override
    public ArrayList<GoodsBean> searchGoods(GoodsBean goodsBean) {
        return null;
    }
}
