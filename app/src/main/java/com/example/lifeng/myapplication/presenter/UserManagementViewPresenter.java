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

import com.example.lifeng.myapplication.activity.IUserManagementView;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.model.IUserModel;
import com.example.lifeng.myapplication.model.IUserModelImpl;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 普通用户管理的Presenter
 */
public class UserManagementViewPresenter {
    private IUserModel mUserModel;
    private IUserManagementView mUserManagementView;

    public UserManagementViewPresenter(IUserManagementView userManagementView) {
        mUserModel = new IUserModelImpl();
        mUserManagementView = userManagementView;
    }

    /**
     * 添加用户
     *
     * @param userBean
     * @return 是否成功添加
     */
    public boolean addUser(UserBean userBean) {
        return mUserModel.addUser(userBean);
    }

    /**
     * 得到系统中的普通用户
     */
    public void getUsers(ArrayList<UserBean> userBeanArrayList) {
        mUserModel.getUsers(userBeanArrayList);
    }

    /**
     * 删除普通用户
     *
     * @param userBean
     */
    public void delUser(UserBean userBean) {
        mUserModel.delUser(userBean);
    }

    /**
     * 设置用户为会员
     *
     * @param userBean
     * @return 是否设置成功
     */
    public boolean setUserIsVip(UserBean userBean) {
        return mUserModel.setUserIsVip(userBean);
    }
}
