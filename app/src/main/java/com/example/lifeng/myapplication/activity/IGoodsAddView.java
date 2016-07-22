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
 * @version 1.0 16/7/22
 * @description 添加商品View
 */
public interface IGoodsAddView {
    /**
     * UI交互,得到商品的各种输入信息
     *
     * @return 输入是否合法
     */
    boolean getSellerInput();
}
