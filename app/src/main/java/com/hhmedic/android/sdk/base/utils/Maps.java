package com.hhmedic.android.sdk.base.utils;

import java.util.HashMap;

/**
 * Created by iOS on 2018/4/4.
 *
 *
 *
 */

public class Maps
{


    public static HashMap<String,Object> of(String k1,Object v1)
    {
        HashMap<String,Object> map = new HashMap<>();

        map.put(k1,v1);

        return map;
    }



    public static HashMap<String,Object> of(String k1,String v1,String k2,String v2)
    {
        HashMap<String,Object> map = new HashMap<>();

        map.put(k1,v1);

        map.put(k2,v2);

        return map;
    }



    public static HashMap<String,Object> of(String k1,Object v1,String k2,Object v2)
    {
        HashMap<String,Object> map = new HashMap<>();

        map.put(k1,v1);

        map.put(k2,v2);

        return map;
    }


    public static HashMap<String,Object> of(String k1,String v1,String k2,long v2)
    {
        HashMap<String,Object> map = new HashMap<>();

        map.put(k1,v1);

        map.put(k2,v2);

        return map;
    }

    public static HashMap<String,Object> of(String k1,Object v1,String k2,Object v2,String k3,Object v3)
    {
        HashMap<String,Object> map = new HashMap<>();

        map.put(k1,v1);

        map.put(k2,v2);

        map.put(k3,v3);

        return map;
    }


    public static HashMap<String,Object> of(String k1,Object v1,String k2,Object v2,String k3,Object v3,String k4,Object v4)
    {
        HashMap<String,Object> map = new HashMap<>();

        map.put(k1,v1);

        map.put(k2,v2);

        map.put(k3,v3);

        map.put(k4,v4);

        return map;
    }
}
