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
     * @return 返回查询得到的对象
     */
    GoodsBean getGoodsDetails(GoodsBean goodsBean);

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
     */
    void delGoods(GoodsBean goodsBean);

    /**
     * 修改商品信息
     *
     * @param goodsBean
     */
    void modifyGoods(GoodsBean goodsBean);

    /**
     * 得到全部商品信息接口(浏览商品目录)
     */
    void getGoods(ArrayList<GoodsBean> goodsBeanArrayList);

    /**
     * 得到指定类别的全部商品
     *
     * @param goodsBeanArrayList
     * @param kind
     */
    void getGoodsByKind(ArrayList<GoodsBean> goodsBeanArrayList, String kind);

    /**
     * 得到商品的全部种类
     *
     * @param stringArrayList
     */
    void getGoodsKinds(ArrayList<String> stringArrayList);

    /**
     * 商品搜索接口
     *
     * @param goodsBeanArrayList
     * @param keyword
     */
    void searchGoods(ArrayList<GoodsBean> goodsBeanArrayList, String keyword);

    /**
     * 更新库存
     *
     * @param goodsBean
     */
    void updateGoodsAmounts(GoodsBean goodsBean);
}
