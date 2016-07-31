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
import com.example.lifeng.myapplication.bean.SellerBean;
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

    /**
     * 根据状态获取订单
     *
     * @param orderBeanArrayList
     * @param status
     */
    public void getOrdersByStatus(ArrayList<OrderBean> orderBeanArrayList, SellerBean sellerBean, String status) {
        mOrderModel.getOrdersByStatus(orderBeanArrayList, sellerBean, status);
    }

    /**
     * 得到所有订单
     *
     * @param orderBeanArrayList
     */
    public void getAllOrders(ArrayList<OrderBean> orderBeanArrayList, SellerBean sellerBean) {
        mOrderModel.getAllOrders(orderBeanArrayList, sellerBean);
    }

    /**
     * 更新订单状态
     *
     * @param orderBean
     */
    public void updateOrderStatus(OrderBean orderBean) {
        mOrderModel.updateOrderStatus(orderBean);
    }
}
