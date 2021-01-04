package com.hhmedic.android.sdk.base.utils.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.hhmedic.android.sdk.base.utils.Logger;
import com.hhmedic.android.sdk.base.utils.secret.HHSecretUtils;

/**
 * Created by iOS on 2017/1/12.
 *
 */

public class HHSharedPreferences
{

    private static final String COMMON = "hh_common";

    private static SharedPreferences mSharedPre;


    public static void init(Context context)
    {
        initShared(context,COMMON);
    }


    public static void check(Context context)
    {
        if (mSharedPre == null)
        {
            init(context);
        }
    }


    public static void putString(String key,String value)
    {
        try
        {
            mSharedPre.edit().putString(HHSecretUtils.getSHA1(key), HHDes.encode(value)).apply();
        }
        catch (Exception e)
        {
            Logger.e(e.toString());
        }

    }


    private static void initShared(Context context,String nameSpace)
    {
        mSharedPre  =  context.getSharedPreferences(HHSecretUtils.getSHA1(nameSpace),Context.MODE_PRIVATE);
    }



    public  static String getValue(String key)
    {
        return getValue(COMMON,key);
    }

    public  static String getValue(String nameSpace,String key)
    {
        try
        {
            SharedPreferences shared = mSharedPre;

            String content = shared.getString(HHSecretUtils.getSHA1(key),"");

            if (TextUtils.isEmpty(content))
            {
                return "";
            }

            return  HHDes.decode(content);
        }
        catch (Exception e)
        {
            Logger.e(e.toString());
        }

        return "";
    }



    public static void  clear()
    {
        if (mSharedPre !=null)
        {
            mSharedPre.edit().clear().apply();
        }
    }


    public static void removeKey(String key)
    {
        if (mSharedPre != null && !TextUtils.isEmpty(key))
        {
            mSharedPre.edit().remove(HHSecretUtils.getSHA1(key)).apply();
        }
    }
}
