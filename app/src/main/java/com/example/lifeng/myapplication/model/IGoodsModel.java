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

import com.example.lifeng.myapplication.bean.GoodsBean;

import java.util.ArrayList;

/**
 * @author lifeng
 * @version 1.0 16/7/19
 * @description 商品接口
 */
public interface IGoodsModel {
    /**
     * 得到商品信息接口
     *
     * @param goodsBean
     * @return 返回商品信息
     */
    GoodsBean getGoodsInfo(GoodsBean goodsBean);

    /**
     * 添加商品接口
     *
     * @param goodsBean
     * @return 商品是否成功添加
     */
    boolean addGoods(GoodsBean goodsBean);

    /**
     * 删除商品接口
     *
     * @param goodsBean
     * @return 商品是否成功删除
     */
    boolean delGoods(GoodsBean goodsBean);

    /**
     * 修改商品信息
     *
     * @param goodsBean
     * @return 商品信息是否修改成功
     */
    boolean modifyGoods(GoodsBean goodsBean);

    /**
     * 得到全部商品信息接口(浏览商品目录)
     *
     * @return 返回全部商品的列表
     */
    ArrayList<GoodsBean> getAllGoods();

    /**
     * 商品搜索接口
     *
     * @param goodsBean
     * @return 返回搜索得到的商品列表
     */
    ArrayList<GoodsBean> searchGoods(GoodsBean goodsBean);
}
