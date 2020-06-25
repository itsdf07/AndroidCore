package com.itsdf07.core.app.mvvm.model;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/6/7
 */
public interface ILoginModel {
    /**
     * 执行用户登录
     *
     * @param loginId
     * @param pwd
     */
    void login(String loginId, String pwd);

    /**
     * 执行用户登录
     *
     * @param user
     */
    void login(User user);

}
