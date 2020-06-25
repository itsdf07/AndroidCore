package com.itsdf07.core.app;

import com.lib.core.CoreApplication;
import com.lib.core.net.NetInit;

import static com.itsdf07.core.app.config.GlobalConfigs.BASE_URL_LOCAL;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/6/25
 */
public class AppApplication extends CoreApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        NetInit.init(this)
                .withApiHost(BASE_URL_LOCAL)
                .configure();
    }
}
