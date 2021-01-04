package com.hhmedic.android.sdk.base.utils;

import android.app.Activity;
import android.content.Context;

public class HHActivityCompat {

    public static boolean isDead(Context context) {

        if (context == null) {
            return true;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity)context;
            if (activity.isFinishing() || activity.isDestroyed()) {
                return true;
            }
        }
        return false;
    }
}
