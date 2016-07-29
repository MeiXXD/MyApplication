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

import com.example.lifeng.myapplication.activity.IUserSubmitOrderView;
import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.bean.OrderBean;
import com.example.lifeng.myapplication.bean.ShoppingCartBean;
import com.example.lifeng.myapplication.model.IGoodsModel;
import com.example.lifeng.myapplication.model.IGoodsModelImpl;
import com.example.lifeng.myapplication.model.IOrderModel;
import com.example.lifeng.myapplication.model.IOrderModelImpl;
import com.example.lifeng.myapplication.model.IShoppingCartModel;
import com.example.lifeng.myapplication.model.IShoppingCartModelImpl;

/**
 * @author lifeng
 * @version 1.0 16/7/26
 * @description 用户提交订单视图的Presenter
 */
public class UserSubmitOrderViewPresenter {
    private IShoppingCartModel mShoppingCartModel;
    private IUserSubmitOrderView mUserSubmitOrderView;
    private IGoodsModel mGoodsModel;
    private IOrderModel mOrderModel;

    public UserSubmitOrderViewPresenter(IUserSubmitOrderView userSubmitOrderView) {
        mUserSubmitOrderView = userSubmitOrderView;
        mShoppingCartModel = new IShoppingCartModelImpl();
        mOrderModel = new IOrderModelImpl();
        mGoodsModel = new IGoodsModelImpl();
    }

    /**
     * 获取购物车订单
     *
     * @param shoppingCartBean
     */
    public void getShoppingCartOrder(ShoppingCartBean shoppingCartBean) {
        mShoppingCartModel.getShoppingCarOrder(shoppingCartBean);
        mUserSubmitOrderView.setOutput(shoppingCartBean);
    }

    /**
     * 提交订单
     *
     * @param orderBean
     */
    public void submitOrder(OrderBean orderBean) {
        mOrderModel.addOrder(orderBean);
    }

    /**
     * 更新购物车
     *
     * @param shoppingCartBean
     */
    public void updateShoppingCart(ShoppingCartBean shoppingCartBean) {
        mShoppingCartModel.delFromShoppingCart(shoppingCartBean);
    }

    /**
     * 更新商品库存
     *
     * @param goodsBean
     */
    public void updateGoodsAmounts(GoodsBean goodsBean) {
        mGoodsModel.updateGoodsAmounts(goodsBean);
    }
}
