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

import com.example.lifeng.myapplication.bean.ShoppingCartBean;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.model.IShoppingCartModel;
import com.example.lifeng.myapplication.model.IShoppingCartModelImpl;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/25
 * @description 购物车视图的Presenter
 */
public class UserShoppingCartViewPresenter {
    private IShoppingCartModel mShoppingCartModel;

    public UserShoppingCartViewPresenter() {
        mShoppingCartModel = new IShoppingCartModelImpl();
    }

    public void getUserShoppingCart(ArrayList<ShoppingCartBean> shoppingCartBeanArrayList, UserBean userBean) {
        mShoppingCartModel.getUserShoppingCart(shoppingCartBeanArrayList, userBean);
    }

    public void delFromShoppingCart(ShoppingCartBean shoppingCartBean) {
        mShoppingCartModel.delFromShoppingCart(shoppingCartBean);
    }
}
