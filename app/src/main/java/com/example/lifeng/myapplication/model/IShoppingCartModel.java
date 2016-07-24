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

import com.example.lifeng.myapplication.bean.ShoppingCartBean;

/**
 * @author lifeng
 * @version 1.0 16/7/24
 * @description 购物车Model
 */
public interface IShoppingCartModel {
    /**
     * 添加到购物车
     *
     * @param shoppingCartBean
     */
    void addToShoppingCart(ShoppingCartBean shoppingCartBean);
}
