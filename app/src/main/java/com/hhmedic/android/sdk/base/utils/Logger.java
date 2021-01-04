package com.hhmedic.android.sdk.base.utils;

import android.text.TextUtils;
import android.util.Log;

import com.hhmedic.android.sdk.base.BaseConfig;

public class Logger {


    public static void e(String tips){

        if (!BaseConfig.isDebug){

            return;
        }

        if (!TextUtils.isEmpty(tips)){

            Log.e("HH",tips);
        }
    }
}
