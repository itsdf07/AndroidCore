package com.lib.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.lib.core.log.ALog;
import com.lib.core.log.ALogLevel;
import com.lib.core.utils.AppGlobalUtils;

/**
 * @Description:
 * @Author itsdf07
 * @Date 2020/6/6
 */
public class CoreApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //AppGlobalUtils.init的初始化优先级最高
        AppGlobalUtils.init(this);
        
        initALog();
        registerActivityLifecycleCallbacks(mCallbacks);
    }

    private void initALog() {
        ALog.init().setTag("Aso").setLogLevel(ALogLevel.FULL);
    }

    /**
     * 监听到 Activity 的生命周期状态，及可以判断 APP 是否在前台或者后台等。
     */
    private ActivityLifecycleCallbacks mCallbacks = new ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            ALog.i("activity:%s,savedInstanceState:%s", activity.getClass().getSimpleName(), savedInstanceState);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            ALog.i("activity:%s", activity.getClass().getSimpleName());
        }

        @Override
        public void onActivityResumed(Activity activity) {
            ALog.i("activity:%s", activity.getClass().getSimpleName());
        }

        @Override
        public void onActivityPaused(Activity activity) {
            ALog.i("activity:%s", activity.getClass().getSimpleName());
        }

        @Override
        public void onActivityStopped(Activity activity) {
            ALog.i("activity:%s", activity.getClass().getSimpleName());
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            ALog.i("activity:%s,outState:%s", activity.getClass().getSimpleName(), outState);
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            ALog.i("activity:%s", activity.getClass().getSimpleName());
        }
    };
}
