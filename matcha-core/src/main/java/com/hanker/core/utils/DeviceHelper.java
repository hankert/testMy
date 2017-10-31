package com.hanker.core.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

/**
 * Created by jinhui on 2017/10/31.
 */

public class DeviceHelper {

    public static boolean checkNetWork(Context context) {
        ConnectivityManager cwjManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        cwjManager.getActiveNetworkInfo();
        NetworkInfo networkInfo = cwjManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return true;
        } else {
            return false;
        }
    }

    public static int getVersionName(Context context) {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo;
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            String version = packInfo.versionName;
            int versionCode = packInfo.versionCode;
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public static String getIMEI(Context context) {
        String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        return imei;
    }

    public static String getLocalMacAddress(Context context) {

        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        WifiInfo info = wifi.getConnectionInfo();

        return info.getMacAddress();

    }
}
