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

import com.example.lifeng.myapplication.activity.IUserInfoModifyView;
import com.example.lifeng.myapplication.bean.UserBean;
import com.example.lifeng.myapplication.model.IUserModel;
import com.example.lifeng.myapplication.model.IUserModelImpl;

/**
 * @author lifeng
 * @version 1.0 16/7/27
 * @description 用户信息修改界面的Presenter
 */
public class UserInfoModifyViewPresenter {
    private IUserModel mUserModel;
    private IUserInfoModifyView mUserInfoModifyView;

    public UserInfoModifyViewPresenter(IUserInfoModifyView userInfoModifyView) {
        mUserModel = new IUserModelImpl();
        mUserInfoModifyView = userInfoModifyView;
    }

    /**
     * 得到用户信息
     *
     * @param userBean
     */
    public void getUserInfo(UserBean userBean) {
        mUserInfoModifyView.setOutput(mUserModel.getUserInfo(userBean));
    }

    /**
     * 修改用户信息
     *
     * @param userBean
     */
    public void modifyUserInfo(UserBean userBean) {
        mUserModel.modifyUserInfo(userBean);
    }

}