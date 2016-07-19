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

import com.example.lifeng.myapplication.bean.AdministratorBean;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description IAdministratorModel接口实现类
 */
public class IAdministratorModelImpl implements IAdministratorModel {
    @Override
    public boolean adminLogin(AdministratorBean administratorBean) {
        // TODO: 16/7/19 系统管理员登录逻辑待补
        return true;
    }
}
