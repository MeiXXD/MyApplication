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

import com.example.lifeng.myapplication.bean.UserBean;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 普通用户接口
 */
public interface IUserModel {
    /**
     * 添加普通用户接口
     *
     * @param userBean
     * @return 是否成功添加普通用户
     */
    boolean addUser(UserBean userBean);

    /**
     * 删除普通用户接口
     *
     * @param userBean
     */
    void delUser(UserBean userBean);

    /**
     * 普通用户登录接口
     *
     * @param userBean
     * @return 是否成功登录
     */
    boolean userLogin(UserBean userBean);

    /**
     * 普通用户修改用户信息接口
     *
     * @param userBean
     */
    void modifyUserInfo(UserBean userBean);

    /**
     * 得到用户信息
     *
     * @param userBean
     * @return
     */
    UserBean getUserInfo(UserBean userBean);

    /**
     * 普通用户下订单前的验证接口
     *
     * @param userBean
     * @return 是否验证通过
     */
    boolean verifyPassword(UserBean userBean);

    /**
     * 得到系统中存在的用户
     *
     * @param userBeanArrayList
     */
    void getUsers(ArrayList<UserBean> userBeanArrayList);

    /**
     * 更新用户状态
     *
     * @param userBean
     */
    void updateUserStatus(UserBean userBean);

    /**
     * 设置用户为会员
     *
     * @param userBean
     * @return 是否设置成功
     */
    boolean setUserIsVip(UserBean userBean);

}
