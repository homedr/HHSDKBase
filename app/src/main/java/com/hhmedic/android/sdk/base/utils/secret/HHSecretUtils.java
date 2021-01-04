package com.hhmedic.android.sdk.base.utils.secret;

import java.security.MessageDigest;

public class HHSecretUtils {

    public static String getSHA1(String str)
    {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("SHA-1");

            md5.update(str.getBytes("UTF-8"));

            byte[] m = md5.digest();//加密

            return get16String(m);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return "";

    }

    private static String get16String(byte[] messageDigest)
    {
        StringBuilder hexString = new StringBuilder();
        // 字节数组转换为 十六进制 数
        for (int i = 0; i < messageDigest.length; i++)
        {
            String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);

            if (shaHex.length() < 2)
            {
                hexString.append(0);
            }

            hexString.append(shaHex);
        }

        return hexString.toString();

    }



    public static String createToken(long uuid)
    {
        String uuidStr = String.valueOf(uuid) + "HEHUAN_2015";

        return string2MD5(uuidStr);
    }


    private static String string2MD5(String inStr)
    {
        MessageDigest md5 = null;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
}
