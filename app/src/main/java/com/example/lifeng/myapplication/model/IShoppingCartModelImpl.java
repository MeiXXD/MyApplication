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
 * @description ShoppingCartModel实现类
 */
public class IShoppingCartModelImpl implements IShoppingCartModel {
    private MyDatabaseHelper mMyDatabaseHelper;

    public IShoppingCartModelImpl() {
        mMyDatabaseHelper = MyDatabaseHelper.getInstantce();
    }

    @Override
    public void addToShoppingCart(ShoppingCartBean shoppingCartBean) {

    }
}
