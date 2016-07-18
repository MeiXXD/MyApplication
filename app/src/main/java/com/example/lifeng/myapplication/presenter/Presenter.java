package com.example.lifeng.myapplication.presenter;

import com.example.lifeng.myapplication.activity.IInfoView;
import com.example.lifeng.myapplication.bean.InfoBean;
import com.example.lifeng.myapplication.model.IInfoModel;
import com.example.lifeng.myapplication.model.InfoModelImpl;

/**
 * Created by lifeng on 16/7/18.
 */
public class Presenter {
    private IInfoModel infoModel;
    private IInfoView infoView;

    public Presenter(IInfoView infoView) {
        this.infoView = infoView;
        infoModel = new InfoModelImpl();
    }

    //供UI调用
    public void saveInfo(InfoBean bean) {
        infoModel.setInfo(bean);
    }

    //供UI调用
    public void getInfo() {
        //通过调用IInfoView的方法来更新显示
        //类似回调监听处理
        infoView.setInfo(infoModel.getInfo());
    }
}
