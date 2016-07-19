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

import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.model.IUserModel;
import com.example.lifeng.myapplication.model.IUserModelImpl;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 普通用户登录的Presenter
 */
public class UserLoginViewPresenter {
    private IUserModel mUserModel;

    public UserLoginViewPresenter() {
        mUserModel = new IUserModelImpl();
    }

    /**
     * 普通用户登录
     *
     * @param userBean
     * @return 是否成功登录
     */
    public boolean userLogin(UserBean userBean) {
        return mUserModel.userLogin(userBean);
    }
}