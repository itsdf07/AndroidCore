package com.itsdf07.core.app.mvvm.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;


/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/6/7
 */
public class User extends BaseObservable {
    private String loginId;
    private String passwd;

    public User(String loginId, String passwd) {
        this.loginId = loginId;
        this.passwd = passwd;
    }

    @Bindable
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
        notifyPropertyChanged(BR.loginId);
    }

    @Bindable
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
        notifyPropertyChanged(BR.passwd);
    }
}
