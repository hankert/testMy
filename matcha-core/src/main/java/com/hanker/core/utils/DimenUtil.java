package com.hanker.core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.hanker.core.net.app.Matcha;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/10/25.
 */

public final class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Matcha.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Matcha.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
