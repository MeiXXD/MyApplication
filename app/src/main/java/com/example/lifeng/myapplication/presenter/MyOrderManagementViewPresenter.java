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
 * @version 1.0 16/7/27
 * @description 用户订单管理Presenter
 */
public class MyOrderManagementViewPresenter {
    private IOrderModel mOrderModel;

    public MyOrderManagementViewPresenter() {
        mOrderModel = new IOrderModelImpl();
    }

    public void getUserAllOrders(ArrayList<OrderBean> orderBeanArrayList, UserBean userBean) {
        mOrderModel.getUsersAllOrders(orderBeanArrayList, userBean);
    }

    public void getOrderStatusKinds(ArrayList<String> stringArrayList) {
        mOrderModel.getOrderStatusKinds(stringArrayList);
    }

    public void getOrdersByStatus(ArrayList<OrderBean> orderBeanArrayList, UserBean userBean, String status) {
        mOrderModel.getOrdersByStatus(orderBeanArrayList, userBean, status);
    }
}