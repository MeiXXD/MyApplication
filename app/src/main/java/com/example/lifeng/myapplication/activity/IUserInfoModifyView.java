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

package com.example.lifeng.myapplication.activity;

import com.example.lifeng.myapplication.bean.UserBean;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 普通用户信息修改View
 */
public interface IUserInfoModifyView {
    /**
     * UI交互,得到输入的信息
     *
     * @return 输入是否合法
     */
    boolean getInput();

    /**
     * UI交互,设置界面输出信息
     *
     * @param userBean
     */
    void setOutput(UserBean userBean);
}
