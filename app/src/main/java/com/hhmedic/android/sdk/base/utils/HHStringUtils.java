package com.hhmedic.android.sdk.base.utils;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Base64;


import com.hhmedic.android.sdk.base.utils.secret.HHSecretUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by iOS on 2016/11/3.
 *
 */

public final class  HHStringUtils
{

    private  static  String WHITE_SPACE = " ";

    public static String getSHA1(String str)
    {
        return HHSecretUtils.getSHA1(str);

    }

    public static String createLinkString(Map<String, Object> params,boolean urlEncode)
    {
        List<String> keys = new ArrayList<String>(params.keySet());

        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++)
        {
            String key = keys.get(i);

            if (params.get(key)==null)
            {
                continue;
            }
            String value = params.get(key).toString();

            if(urlEncode)
            {
                value = urlEncode(value);
            }

            if (i == keys.size() - 1)
            {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            }
            else
            {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }


    public static String urlEncode(String value)
    {
        try
        {
            value = URLEncoder.encode(value,"UTF-8");

        } catch (UnsupportedEncodingException e) {
            Logger.e("urlEncode error:"+e.getLocalizedMessage());
        }

        return value;
    }



    private static byte[] getContentBytes(String content, String charset)
    {
        if (TextUtils.isEmpty(charset))
        {
            return content.getBytes();
        }
        try
        {
            return content.getBytes(charset);
        }
        catch (UnsupportedEncodingException e)
        {
            Logger.e("getContentBytes error:"+e.getLocalizedMessage());
        }

        return null;
    }


    public static Spanned formatHtml(String content)
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
        {
            return Html.fromHtml(content,Html.FROM_HTML_MODE_LEGACY);
        }
        else
        {
            return Html.fromHtml(content);
        }
    }


    public static String base64(String str)
    {

        try
        {
            return Base64.encodeToString(str.getBytes("UTF-8"),Base64.NO_WRAP);
        }
        catch (UnsupportedEncodingException e)
        {
            Logger.e("base64 error:"+e.getLocalizedMessage());
        }

        return "";
    }


    public static String base64(byte[] data)
    {
        if (data == null)
        {
            return null;
        }

        return Base64.encodeToString(data,Base64.NO_WRAP);
    }




}
