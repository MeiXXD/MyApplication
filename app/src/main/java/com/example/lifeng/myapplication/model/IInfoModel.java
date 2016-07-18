package com.example.lifeng.myapplication.model;

import com.example.lifeng.myapplication.bean.InfoBean;

/**
 * Created by lifeng on 16/7/18.
 */
public interface IInfoModel {
    //从数据提供者获取数据
    InfoBean getInfo();

    //存入数据提供者
    void setInfo(InfoBean info);
}
