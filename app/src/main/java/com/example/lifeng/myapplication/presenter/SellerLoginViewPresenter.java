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

import com.example.lifeng.myapplication.bean.SellerBean;
import com.example.lifeng.myapplication.model.ISellerModel;
import com.example.lifeng.myapplication.model.ISellerModelImpl;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 销售商登录的Presenter
 */
public class SellerLoginViewPresenter {
    private ISellerModel mSellerModel;

    public SellerLoginViewPresenter() {
        mSellerModel = new ISellerModelImpl();
    }

    /**
     * 销售商登录
     *
     * @param sellerBean
     * @return 是否登录成功
     */
    public boolean sellerLogin(SellerBean sellerBean) {
        return mSellerModel.sellerLogin(sellerBean);
    }

    /**
     * 销售商登录状态更新
     *
     * @param sellerBean
     */
    public void updateSellerStatus(SellerBean sellerBean) {
        mSellerModel.updateSellerStatus(sellerBean);
    }

}
