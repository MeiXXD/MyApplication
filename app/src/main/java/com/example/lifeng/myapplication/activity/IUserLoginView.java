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

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 普通用户登录View
 */
public interface IUserLoginView {
    /**
     * UI交互,得到输入的用户名和密码
     *
     * @return 输入是否合法
     */
    boolean getUserInput();
}
