package com.example.lifeng.myapplication.model;

import com.example.lifeng.myapplication.bean.InfoBean;

/**
 * Created by lifeng on 16/7/18.
 */
public class InfoModelImpl implements IInfoModel {
    //模拟数据
    private InfoBean info = new InfoBean();

    @Override
    public InfoBean getInfo() {
        //模拟获取数据,真实操作很多
        return info;
    }

    @Override
    public void setInfo(InfoBean info) {
        //模拟存储数据,真实操作很多
        this.info = info;
    }
}
