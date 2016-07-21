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

import com.example.lifeng.myapplication.activity.ISellerManagementView;
import com.example.lifeng.myapplication.bean.SellerBean;
import com.example.lifeng.myapplication.model.ISellerModel;
import com.example.lifeng.myapplication.model.ISellerModelImpl;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 销售商管理的Presenter
 */
public class SellerManagementViewPresenter {
    private ISellerModel mSellerModel;
    private ISellerManagementView mSellerManagementView;

    public SellerManagementViewPresenter(ISellerManagementView sellerManagementView) {
        mSellerModel = new ISellerModelImpl();
        mSellerManagementView = sellerManagementView;
    }

    /**
     * 添加销售商
     *
     * @param sellerBean
     * @return 是否成功添加
     */
    public boolean addSeller(SellerBean sellerBean) {
        return mSellerModel.addSeller(sellerBean);
    }

    /**
     * 得到系统中的销售商
     */
    public void getSellers(ArrayList<SellerBean> sellerBeanArrayList) {
        mSellerModel.getSellers(sellerBeanArrayList);
    }

    /**
     * 删除销售商
     *
     * @param sellerBean
     */
    public void delSeller(SellerBean sellerBean) {
        mSellerModel.delSeller(sellerBean);
    }
}