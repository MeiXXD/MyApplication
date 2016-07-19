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

import com.example.lifeng.myapplication.bean.AdministratorBean;
import com.example.lifeng.myapplication.model.IAdministratorModel;
import com.example.lifeng.myapplication.model.IAdministratorModelImpl;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 管理员登录Presenter
 */
public class AdministratorLoginViewPresenter {
    private IAdministratorModel administratorModel;

    public AdministratorLoginViewPresenter() {
        administratorModel = new IAdministratorModelImpl();
    }

    public boolean adminLogin(AdministratorBean administratorBean) {
        return administratorModel.adminLogin(administratorBean);
    }

}
