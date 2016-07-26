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
import com.example.lifeng.myapplication.bean.ShoppingCartBean;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.model.IGoodsModel;
import com.example.lifeng.myapplication.model.IGoodsModelImpl;
import com.example.lifeng.myapplication.model.IShoppingCartModel;
import com.example.lifeng.myapplication.model.IShoppingCartModelImpl;
import com.example.lifeng.myapplication.model.IUserModel;
import com.example.lifeng.myapplication.model.IUserModelImpl;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/25
 * @description 用户商品浏览页面的Presenter
 */
public class UserGoodsViewPresenter {
    private IGoodsModel mGoodsModel;
    private IUserModel mUserModel;
    private IShoppingCartModel mShoppingCartModel;
    private UserBean mUserBean;

    public UserGoodsViewPresenter(UserBean userBean) {
        mUserBean = userBean;
        mGoodsModel = new IGoodsModelImpl();
        mShoppingCartModel = new IShoppingCartModelImpl();
        mUserModel = new IUserModelImpl();
    }

    public void getGoodsByKind(ArrayList<GoodsBean> goodsBeanArrayList, String kind) {
        mGoodsModel.getGoodsByKind(goodsBeanArrayList, kind);
    }

    public void getGoodsKinds(ArrayList<String> stringArrayList) {
        mGoodsModel.getGoodsKinds(stringArrayList);
    }

    public void getGoods(ArrayList<GoodsBean> goodsBeanArrayList) {
        mGoodsModel.getGoods(goodsBeanArrayList);
    }

    public void addToShoppingCart(GoodsBean goodsBean) {
        ShoppingCartBean shoppingCartBean = new ShoppingCartBean(mUserBean, goodsBean);
        shoppingCartBean.setAmounts(1);
        mShoppingCartModel.addToShoppingCart(shoppingCartBean);
    }
}
