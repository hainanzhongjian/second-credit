/**
 * Copyright(c) 2013-2013 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.second.credit.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * MD5加密工具类，
 * 
 * @author luyujian
 */
public class MD5keyUtil {
    private static Logger log = Logger.getLogger(MD5keyUtil.class);

    /**
     * 返回加密的密文
     * 
     * @param str
     *            要加密的字符串
     * @return
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) { // NOSONAR
            log.error(e.getMessage());
        } catch (UnsupportedEncodingException e) { // NOSONAR
            log.error(e.getMessage());
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString().toUpperCase();
    }

    public static void main(String[] arg) {
        System.out.println(getMD5Str("123456"));
    }
}
