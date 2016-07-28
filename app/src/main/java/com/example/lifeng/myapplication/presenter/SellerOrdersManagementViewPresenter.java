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
import com.example.lifeng.myapplication.model.IOrderModel;
import com.example.lifeng.myapplication.model.IOrderModelImpl;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/28
 * @description 销售商订单管理Presenter
 */
public class SellerOrdersManagementViewPresenter {
    private IOrderModel mOrderModel;

    public SellerOrdersManagementViewPresenter() {
        mOrderModel = new IOrderModelImpl();
    }

    public void getOrderStatusKinds(ArrayList<String> stringArrayList) {
        mOrderModel.getOrderStatusKinds(stringArrayList);
    }

    public void getOrdersByStatus(ArrayList<OrderBean> orderBeanArrayList, String status) {
        mOrderModel.getOrdersByStatus(orderBeanArrayList, status);
    }

    public void getAllOrders(ArrayList<OrderBean> orderBeanArrayList) {
        mOrderModel.getAllOrders(orderBeanArrayList);
    }
}
