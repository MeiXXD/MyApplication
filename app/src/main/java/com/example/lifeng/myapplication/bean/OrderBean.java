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

package com.example.lifeng.myapplication.bean;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author lifeng
 * @description 订单bean
 * @date 16/7/18
 */
public class OrderBean {
    /**
     * 订单编号
     */
    private int mId;
    /**
     * 订单日期
     */
    private Date mDate;
    /**
     * 用户
     */
    private UserBean mUserBean;
    /**
     * 订单包含的商品(一个订单可能包含多个商品)
     */
    private ArrayList<GoodsBean> goodsBeanArrayList;
    /**
     * 订单状态
     */
    private int mStatus;
    /**
     * 总计
     */
    private double mAccount;
}
