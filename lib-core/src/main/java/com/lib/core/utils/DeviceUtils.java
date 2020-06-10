package com.lib.core.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

/**
 * @Description: 与设备有关的工具类
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/6/9
 */
public class DeviceUtils {
    /**
     * @return 返回设备的Android ID，如 00e54fff9e248c1d
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID() {
        String id = Settings.Secure.getString(AppGlobalUtils.getApplication().getContentResolver(), Settings.Secure.ANDROID_ID);
        //厂商定制系统的Bug：不同的设备可能会产生相同的ANDROID_ID：9774d56d682e549c。
        //厂商定制系统的Bug：有些设备返回的值为null。
        if ("9774d56d682e549c".equals(id)) {
            return "";
        }
        return id == null ? "" : id;
    }

    /**
     * @return CPU指令集(CPU 架构)，如 [arm64-v8a, armeabi-v7a, armeabi]
     */
    public static String[] getABIs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Build.SUPPORTED_ABIS;
        } else {
            if (!TextUtils.isEmpty(Build.CPU_ABI2)) {
                return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
            }
            return new String[]{Build.CPU_ABI};
        }
    }

    /**
     * @return Android 系统 API版本名称，如 Android 系统 9
     */
    public static String getSDKVersionName() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * @return Android 系统 API版本，即 API版本，如 28
     */
    public static int getSDKVersionCode() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * @return 获取设备型号，如 VTR-AL00
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (null == model) {
            model = "";
        }
        return model;
    }
}
