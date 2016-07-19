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
 * @description IOrderModel实现类
 */
public class IOrderModelImpl implements IOrderModel {
    @Override
    public boolean addOrder(UserBean userBean, OrderBean orderBean) {
        return false;
    }

    @Override
    public boolean modifyOrder(OrderBean orderBean) {
        return false;
    }

    @Override
    public ArrayList<OrderBean> getOrders(UserBean userBean) {
        return null;
    }

    @Override
    public ArrayList<OrderBean> searchOrders(UserBean userBean, OrderBean orderBean) {
        return null;
    }
}
