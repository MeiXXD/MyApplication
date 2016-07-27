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

import com.example.lifeng.myapplication.bean.OrderBean;
import com.example.lifeng.myapplication.bean.UserBean;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 订单接口
 */
public interface IOrderModel {
    /**
     * 添加订单接口(下单)
     *
     * @param orderBean
     */
    void addOrder(OrderBean orderBean);

    /**
     * 订单状态处理接口(修改订单状态:确认发货 状态码为1、订单驳回 状态码为-1)
     *
     * @param orderBean
     */
    void updateOrderStatus(OrderBean orderBean);

    /**
     * 得到某个用户的全部订单的接口
     *
     * @param orderBeanArrayList
     * @param orderBeanArrayList
     */
    void getUsersAllOrders(ArrayList<OrderBean> orderBeanArrayList, UserBean userBean);

    /**
     * 订单查询接口
     *
     * @param orderBeanArrayList
     */
    void searchOrders(ArrayList<OrderBean> orderBeanArrayList);

    /**
     * 得到商品的种类(按照状态分类)
     *
     * @param stringArrayList
     */
    void getOrderStatusKinds(ArrayList<String> stringArrayList);

    /**
     * 获取指定状态的订单
     *
     * @param orderBeanArrayList
     * @param status
     */
    void getOrdersByStatus(ArrayList<OrderBean> orderBeanArrayList, UserBean userBean, String status);
}
