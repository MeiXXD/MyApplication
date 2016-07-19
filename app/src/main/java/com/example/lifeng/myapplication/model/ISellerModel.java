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

import com.example.lifeng.myapplication.bean.SellerBean;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 销售商接口
 */
public interface ISellerModel {
    /**
     * 添加销售商接口
     *
     * @param sellerBean
     * @return 是否成功添加销售商
     */
    boolean addSeller(SellerBean sellerBean);

    /**
     * 销售商登录接口
     *
     * @param sellerBean
     * @return 是否成功登录
     */
    boolean sellerLogin(SellerBean sellerBean);
}
