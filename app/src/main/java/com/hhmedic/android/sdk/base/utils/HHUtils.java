package com.hhmedic.android.sdk.base.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;


/**
 * Created by iOS on 2018/4/11.
 *
 *
 */

public class HHUtils
{



    private static long lastClickTime;

    /// 是否快速连续点击
    public static boolean isFastClick()
    {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 300) {
            return true;
        }
        lastClickTime = time;
        return false;
    }



    public static float density;

    public static int screenWidth;
    public static int screenHeight;
    public static int screenMin;// 宽高中，小的一边
    public static int screenMax;// 宽高中，较大的值




    public static void init(Context context) {
        if (null == context) {
            return;
        }

        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();

        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        screenMin = (screenWidth > screenHeight) ? screenHeight : screenWidth;

        density = dm.density;
    }


    public static int screenOffset(Activity activity, View globalView) {
        DisplayMetrics dm = new DisplayMetrics();

        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        int topOffset = dm.heightPixels - globalView.getMeasuredHeight();

        return Math.abs(topOffset);
    }



    public static int dip2px(float dipValue) {
        return (int) (dipValue * density + 0.5f);
    }






    public static void errorTips(Context context,String tips)
    {
        if (TextUtils.isEmpty(tips) || context == null)
        {
            return;
        }


        try
        {
            Toast.makeText(context,tips,Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Logger.e(e.toString());
        }
    }
}
