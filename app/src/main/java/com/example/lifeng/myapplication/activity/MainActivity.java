package com.example.lifeng.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lifeng.myapplication.R;
import com.example.lifeng.myapplication.bean.InfoBean;
import com.example.lifeng.myapplication.presenter.Presenter;

public class MainActivity extends AppCompatActivity implements IInfoView, View.OnClickListener {
    private Button mbtGetInfo;
    private Button mbtSetInfo;

    //通过presenter操作数据
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
    }

    private void initData() {
        presenter = new Presenter(this);
        mbtGetInfo.setOnClickListener(this);
        mbtSetInfo.setOnClickListener(this);
    }

    @Override
    public InfoBean getInfo() {
        //得到界面输入数据,并新建info对象
        InfoBean bean = new InfoBean();
        //得到数据并设置相关域
        return bean;
    }

    @Override
    public void setInfo(InfoBean info) {
        //从后台获取数据
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //得到输入的各种信息
            case R.id.getInfo:
                presenter.saveInfo(getInfo());
                break;
            //获取后台的各种信息
            case R.id.setInfo:
                presenter.getInfo();
                break;
        }
    }
}
