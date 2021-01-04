package com.hhmedic.android.sdk.base.utils;

import android.text.TextUtils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iOS on 2017/2/16.
 *
 */

public class HHDateUtils
{


    public static final String MONTH = "month";

    public static final String DAY = "day";

    private static final String NORMAL_FORMAT = "yyyy-MM-dd HH:mm";

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String DATE_FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_NO_SP = "yyyyMMdd";

    public static final String CHINA_MONTH = "MM月dd日";

    public static String formatTime(long time)
    {
        return formatTime(time,NORMAL_FORMAT);
    }


    public static String formatDay(long time)
    {
        return formatTime(time,DATE_FORMAT);
    }

    public static String formatDay(Date date)
    {
        return formatDay(date,DATE_FORMAT);
    }

    private HHDateUtils()
    {

    }

    public static String formatDay(Date date,String formart)
    {
        if (date == null)
        {
            return "";
        }

        return formatDate(date,formart);
    }

    public static String formatTime(long time, String formatStr)
    {
//        if (time <=0)
//        {
//            return null;
//        }

        SimpleDateFormat format = new SimpleDateFormat(formatStr);

        return format.format(new Date(time));
    }


    public static String formatDate(Date date, String formatStr)
    {
        if (date ==null)
        {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat(formatStr);

        return format.format(date);
    }




    public static String formatTimeFromLong(long time)
    {
        long se = time /1000;

        long  m = se/60;

        long la = se%60;


        String split = la < 10 ? "0" :"";

        if (m>0)
        {
            return   m + ":" + split +la;
        }
        else
        {
            return  "0:" + split +la;
        }

    }



    public static long timeStr2Long(String time)
    {
        return timeStr2Long(time,DATE_FORMAT);

    }


    public static long timeStr2Long(String time,String formatStr)
    {
        if (TextUtils.isEmpty(time))
        {
            return 0;
        }

        SimpleDateFormat format = new SimpleDateFormat(formatStr);

        try
        {
            Date date = format.parse(time);

            return date.getTime();
        }
        catch (ParseException e)
        {
            Logger.e(e.getMessage());
        }


        return 0;

    }



    public static Date str2Date(String time)
    {
        if (TextUtils.isEmpty(time))
        {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat(NORMAL_FORMAT);

        try
        {
            return  format.parse(time);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return null;


    }



    public static String parseTime(Date date)
    {
        return parseTime(date,"HH时mm分");
    }

    public static String parseNormal(Date date)
    {
        return parseTime(date,"HH:mm");
    }

    private static String parseTime(Date date, String formatStr)
    {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);

        return format.format(date);
    }



    public static Map<String,String> formart(Date date)
    {

        HashMap<String,String> result = new HashMap<>();

        Calendar cal = Calendar.getInstance();

        cal.setTime(date);


        int month = cal.get(Calendar.MONTH) + 1;

        String mStr = String.valueOf(month);

        if (month <10)
        {
            mStr = "0"+mStr;
        }

        result.put(MONTH,(mStr+"月"));

        result.put(DAY,String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));

        return  result;
    }
}
