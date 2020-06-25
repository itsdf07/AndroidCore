package com.itsdf07.core.app.mvvm.model.impl;

import com.itsdf07.core.app.mvvm.model.ILoginModel;
import com.itsdf07.core.app.mvvm.model.User;
import com.lib.core.log.ALog;
import com.lib.core.net.rtf2.NetClient;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/6/7
 */
public class LoginModelImpl implements ILoginModel {
    private static final String TAG = "Login";
    private User mUser;
    @Override
    public void login(String loginId, String pwd) {
        JSONObject body = new JSONObject();
        try {
            body.put("loginId", loginId);
            body.put("appOs", "1");
            body.put("version", "1.0.0");
            body.put("passwd", pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        NetClient.create()
                .url("api/usr/login")
                .raw(body.toString())
                .success(response -> {
                    //responce:{"code":20000,"data":"这是一个带参数的Get请求方式API接口测试:param1=77,param2=123456","msg":["Success","请求成功","请求成功"],"version":"当前版本信息：1.0.0403(dev)"}
                    ALog.dTag(TAG, "response:%s", response);
                    if (null != mUser){
                        mUser.setLoginId("叫我流氓");
                    }
                }).
                failure(() ->
                        ALog.dTag(TAG, "onFailure:%s")
                ).
                error((code, msg) ->
                        ALog.dTag(TAG, "code:%s,msg:%s", code, msg)
                ).build()
                .postRaw();

    }

    @Override
    public void login(User user) {
        mUser = user;
        login(mUser.getLoginId(),mUser.getPasswd());
    }
}
