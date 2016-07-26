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
 * @version 1.0 16/7/26
 * @description user tab界面的presenter
 */
public class UserTabActivityViewPresenter {
    private IUserModel mUserModel;

    public UserTabActivityViewPresenter() {
        mUserModel = new IUserModelImpl();
    }

    public void userLogout(UserBean userBean) {
        mUserModel.updateUserStatus(userBean);
    }
}
