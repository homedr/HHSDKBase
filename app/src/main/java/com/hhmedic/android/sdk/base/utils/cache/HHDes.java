package com.hhmedic.android.sdk.base.utils.cache;

import android.util.Base64;

import com.hhmedic.android.sdk.base.utils.secret.HHDesConfig;
//
//
//import com.hhmedic.android.sdk.base.utils.secret.HHDesConfig;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;

/**
 * Created by iOS on 2017/1/12.
 *
 */

class HHDes
{

    private static byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0};

    static String encode(String str) throws Exception
    {
//        IvParameterSpec zeroIv = new IvParameterSpec(iv);
//        SecretKeySpec key = new SecretKeySpec(HHDesConfig.getSIMPLE().getBytes(HHDesConfig.getENCODING()), HHDesConfig.getDES());
//        Cipher cipher = Cipher.getInstance(HHDesConfig.getDES());
//        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
//        byte[] encryptedData = cipher.doFinal(str.getBytes(HHDesConfig.getENCODING()));
//        return Base64.encodeToString(encryptedData, 1);

        return Base64.encodeToString(str.getBytes(HHDesConfig.getENCODING()),1);
    }

    static String decode(String str) throws Exception
    {
//        IvParameterSpec zeroIv = new IvParameterSpec(iv);
//        SecretKeySpec key = new SecretKeySpec(HHDesConfig.getSIMPLE().getBytes(HHDesConfig.getENCODING()), HHDesConfig.getDES());
//        Cipher cipher = Cipher.getInstance(HHDesConfig.getDES());
//        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
//        byte[] decryptData = cipher.doFinal(Base64.decode(str, 1));
//
//        return new String(decryptData,HHDesConfig.getENCODING());

        return  new String(Base64.decode(str, 1), HHDesConfig.getENCODING());
    }
}
