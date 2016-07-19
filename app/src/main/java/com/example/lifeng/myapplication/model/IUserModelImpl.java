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

import com.example.lifeng.myapplication.bean.GoodsBean;
import com.example.lifeng.myapplication.bean.UserBean;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description IUserModel接口实现类
 */
public class IUserModelImpl implements IUserModel {
    @Override
    public boolean addUser(UserBean userBean) {
        return false;
    }

    @Override
    public boolean delUser(UserBean userBean) {
        return false;
    }

    @Override
    public boolean userLogin(UserBean userBean) {
        return false;
    }

    @Override
    public boolean userLogout(UserBean userBean) {
        return false;
    }

    @Override
    public boolean modifyUserInfo(UserBean userBean) {
        return false;
    }

    @Override
    public boolean addTOShoppingCart(GoodsBean goodsBean) {
        return false;
    }

    @Override
    public boolean verifyAgain(UserBean userBean) {
        return false;
    }
}
