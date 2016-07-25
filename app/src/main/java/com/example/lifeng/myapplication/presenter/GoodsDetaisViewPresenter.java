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

import com.example.lifeng.myapplication.activity.IGoodsDetailsView;
import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.bean.ShoppingCartBean;
import com.example.lifeng.myapplication.model.IGoodsModel;
import com.example.lifeng.myapplication.model.IGoodsModelImpl;
import com.example.lifeng.myapplication.model.IShoppingCartModel;
import com.example.lifeng.myapplication.model.IShoppingCartModelImpl;

/**
 * @author lifeng
 * @version 1.0 16/7/25
 * @description 商品详情的Presenter
 */
public class GoodsDetaisViewPresenter {
    private IGoodsModel mGoodsModel;
    private IShoppingCartModel mShoppingCartModel;
    private IGoodsDetailsView mGoodsDetailsView;

    public GoodsDetaisViewPresenter(IGoodsDetailsView goodsDetailsView) {
        mGoodsModel = new IGoodsModelImpl();
        mShoppingCartModel = new IShoppingCartModelImpl();
        mGoodsDetailsView = goodsDetailsView;
    }

    public void getGoodsDetails(GoodsBean goodsBean) {
        mGoodsDetailsView.setOutput(mGoodsModel.getGoodsDetails(goodsBean));
    }

    public void addToShoppingCart(ShoppingCartBean shoppingCartBean) {
        mShoppingCartModel.addToShoppingCart(shoppingCartBean);
    }
}
