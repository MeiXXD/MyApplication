package com.example.lifeng.myapplication.activity;

import com.example.lifeng.myapplication.bean.InfoBean;

/**
 * Created by lifeng on 16/7/18.
 */
public interface IInfoView {
    //从UI中获取数据
    InfoBean getInfo();

    //给UI显示更新数据
    void setInfo(InfoBean info);
}
