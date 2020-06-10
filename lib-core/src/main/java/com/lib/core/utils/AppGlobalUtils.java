package com.lib.core.utils;

import android.annotation.SuppressLint;
import android.app.Application;

import com.lib.core.log.ALog;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @Description: 内存中存储一些APP全局配置，需在 项目的 Application 中优先初始化
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/6/10
 */
public class AppGlobalUtils {
    /**
     * 全局上下文
     */
    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    /**
     * 初始化全局上下文
     *
     * @param application application
     */
    public static void init(final Application application) {
        if (null == application) {
            ALog.e("application 不能为 null");
            return;
        }
        sApplication = application;
    }

    /**
     * @return Application 外部使用时无需在判空
     */
    public static Application getApplication() {
        if (null != sApplication) {
            return sApplication;
        }
        //基于未知因素导致Application为null时，通过反射进行重新初始化，如果还失败，那就无力回天了
        init(getApplicationByReflect());
        if (null == sApplication) {
            throw new NullPointerException("反射初始化 Application 失败");
        }
        ALog.i("成功通过反射初始化 Application");
        return sApplication;
    }

    private static Application getApplicationByReflect() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object thread = getActivityThread();
            Object app = activityThreadClass.getMethod("getApplication").invoke(thread);
            if (app == null) {
                return null;
            }
            return (Application) app;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object getActivityThread() {

        Object activityThread = getActivityThreadInActivityThreadStaticField();
        if (activityThread != null) {
            return activityThread;
        }
        activityThread = getActivityThreadInActivityThreadStaticMethod();
        if (activityThread != null) {
            return activityThread;
        }
        return null;
    }

    private static Object getActivityThreadInActivityThreadStaticField() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Field sCurrentActivityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
            sCurrentActivityThreadField.setAccessible(true);
            return sCurrentActivityThreadField.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Object getActivityThreadInActivityThreadStaticMethod() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            return activityThreadClass.getMethod("currentActivityThread").invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
