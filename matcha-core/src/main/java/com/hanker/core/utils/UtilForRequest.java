package com.hanker.core.utils;

import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.hanker.core.net.app.Matcha;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Created by jinhui on 2017/10/31.
 */

public class UtilForRequest {


    /**
     * 获取签名
     *
     * @param map
     * @return
     */
    public static String genSigntureString(Map<String, Object> map) {
        StringBuilder sBuilder = new StringBuilder();
        Set<String> keySet = map.keySet();
        List<String> keyList = new ArrayList<>();
        for (String key : keySet) {
            keyList.add(key);
        }
        Collections.sort(keyList, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });
//        sBuilder.append(getk());
        sBuilder.append("m39dhaow8h5b091n");
        for (String key : keyList) {
            sBuilder.append(key).append(map.get(key));
        }
//        sBuilder.append(getS());
        sBuilder.append("3821054637287940658");
        keySet = null;
        keyList = null;
        MatChaLogger.i("xdebug", sBuilder.toString());
        return genMD5String(sBuilder.toString());
    }

    public static String genMD5String(String plainText) {

        return MD5.mkMd5(plainText);
    }

    public static String getAndroidVer() {
        return "android_" + android.os.Build.VERSION.RELEASE;
    }

    public static String getAndroidModel() {
        return android.os.Build.BRAND + "_" + android.os.Build.MODEL;
    }

    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }

    public static String getDeviceId() {
        String r = "wrong_device_id";
        try {
            r = ((TelephonyManager) Matcha.getApplicationContext()
                    .getSystemService(Matcha.getApplicationContext().TELEPHONY_SERVICE)).getDeviceId();
            if (TextUtils.isEmpty(r) || "0".equals(r)) {
                r = DeviceHelper.getLocalMacAddress(Matcha.getApplicationContext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public static String genSn() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault());
        return new String(sdf.format(new Date()));
    }

    public static String getHS(String time, String s) {
        if (TextUtils.isEmpty(s)) {
            // return Util.genMD5String(time+getK());
            return genMD5String(time + s + "for");
        } else {
            return genMD5String(time + s + "for");
        }
    }



}
