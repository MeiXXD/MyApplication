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

package com.example.lifeng.myapplication.presenter;

import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.bean.SellerBean;
import com.example.lifeng.myapplication.model.IGoodsModel;
import com.example.lifeng.myapplication.model.IGoodsModelImpl;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/22
 * @description 商品管理
 */
public class GoodsManagementViewPresenter {
    private IGoodsModel mGoodsModel;

    public GoodsManagementViewPresenter() {
        mGoodsModel = new IGoodsModelImpl();
    }

    /**
     * 得到某销售商的所有商品
     *
     * @param goodsBeanArrayList
     * @param sellerBean
     */
    public void getGoods(ArrayList<GoodsBean> goodsBeanArrayList, SellerBean sellerBean) {
        mGoodsModel.getGoods(goodsBeanArrayList, sellerBean);
    }

    /**
     * 删除商品
     *
     * @param goodsBean
     */
    public void delGoods(GoodsBean goodsBean) {
        mGoodsModel.delGoods(goodsBean);
    }

}
