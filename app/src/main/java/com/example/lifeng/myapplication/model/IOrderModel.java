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
     * @param userBean
     * @param orderBean
     * @return 订单是否成功添加
     */
    boolean addOrder(UserBean userBean, OrderBean orderBean);

    /**
     * 订单处理接口(修改订单状态:确认发货 状态码为1、订单驳回 状态码为-1)
     *
     * @param orderBean
     * @return 是否成功修改
     */
    boolean modifyOrder(OrderBean orderBean);

    /**
     * 得到某个用户的全部订单接口
     *
     * @param userBean
     * @return 全部订单信息
     */
    ArrayList<OrderBean> getOrders(UserBean userBean);

    /**
     * 订单查询接口
     *
     * @param userBean
     * @param orderBean
     * @return 返回查询得到的订单
     */
    ArrayList<OrderBean> searchOrders(UserBean userBean, OrderBean orderBean);
}
