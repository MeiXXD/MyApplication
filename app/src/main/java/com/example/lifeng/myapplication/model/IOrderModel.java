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
     * 删除订单接口
     *
     * @param orderBean
     */
    void delOrder(OrderBean orderBean);

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
     * 得到全部订单的接口
     *
     * @param orderBeanArrayList
     */
    void getAllOrders(ArrayList<OrderBean> orderBeanArrayList);

    /**
     * 订单搜索接口
     *
     * @param orderBeanArrayList
     * @param keyword
     */
    void searchOrders(ArrayList<OrderBean> orderBeanArrayList, UserBean userBean, String keyword);

    /**
     * 获取某用户的指定状态的订单
     *
     * @param orderBeanArrayList
     * @param userBean
     * @param status
     */
    void getOrdersByStatus(ArrayList<OrderBean> orderBeanArrayList, UserBean userBean, String status);

    /**
     * 获取指定状态的订单
     *
     * @param orderBeanArrayList
     * @param status
     */
    void getOrdersByStatus(ArrayList<OrderBean> orderBeanArrayList, String status);
}
