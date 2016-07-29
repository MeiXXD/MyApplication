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

import com.example.lifeng.myapplication.bean.OrderBean;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.model.IOrderModel;
import com.example.lifeng.myapplication.model.IOrderModelImpl;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/28
 * @description 订单搜索的Presenter
 */
public class OrdersSearchViewPresenter {
    private IOrderModel mOrderModel;

    public OrdersSearchViewPresenter() {
        mOrderModel = new IOrderModelImpl();
    }

    /**
     * 用户订单搜索功能
     *
     * @param orderBeanArrayList
     * @param userBean
     * @param keyword
     */
    public void searchOrders(ArrayList<OrderBean> orderBeanArrayList, UserBean userBean, String keyword) {
        mOrderModel.searchOrders(orderBeanArrayList, userBean, keyword);
    }
}
