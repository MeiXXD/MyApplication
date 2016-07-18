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

package com.example.lifeng.myapplication.bean;

import java.util.ArrayList;

/**
 * @author lifeng
 * @description 普通用户
 * @date 16/7/18
 */
public class UserBean {
    /**
     * 用户编号
     */
    private int mId;
    /**
     * 用户名
     */
    private String mName;
    /**
     * 用户密码
     */
    private String mPassword;
    /**
     * 邮箱
     */
    private String mEmail;
    /**
     * 收货地址
     */
    private ArrayList<String> mAddressesArrayList;
}
