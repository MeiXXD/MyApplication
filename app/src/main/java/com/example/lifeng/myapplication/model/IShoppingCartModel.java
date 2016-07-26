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

import com.example.lifeng.myapplication.bean.ShoppingCartBean;
import com.example.lifeng.myapplication.bean.UserBean;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/24
 */
public interface IShoppingCartModel {
    /**
     * 添加到购物车
     *
     * @param shoppingCartBean
     */
    void addToShoppingCart(ShoppingCartBean shoppingCartBean);

    /**
     * 得到用户的购物车清单
     *
     * @param shoppingCartBeanArrayList
     */
    void getUserShoppingCart(ArrayList<ShoppingCartBean> shoppingCartBeanArrayList, UserBean userBean);

    /**
     * 从购物车中删除商品
     *
     * @param shoppingCartBean
     */
    void delFromShoppingCart(ShoppingCartBean shoppingCartBean);

    /**
     * 得到购物车订单信息
     *
     * @param shoppingCartBean
     */
    void getShoppingCarOrder(ShoppingCartBean shoppingCartBean);
}
