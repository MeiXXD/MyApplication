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

import com.example.lifeng.myapplication.bean.GoodsBean;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 商品详情View
 */
public interface IGoodsDetailsView {
    /**
     * 得到用户输入的数量
     *
     * @return 输入是否合法
     */
    boolean getUserInput();

    /**
     * UI交互,设置输出
     *
     * @param goodsBean
     */
    void setOutput(GoodsBean goodsBean);
}
