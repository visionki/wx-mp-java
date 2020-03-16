package com.visionki.wechat.util;

import org.springframework.util.StringUtils;

import java.security.MessageDigest;

/**
 * @author liuboyi
 * @Description: MD5加密工具类
 * @create 2019-04-12 9:34
 */
public class MD5Utils {
    public MD5Utils() {
    }

    public static String md5(String str) {
        return md5(str, (String)null);
    }

    public static String md5(String str, String charsetName) {
        String origMD5 = null;

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = StringUtils.isEmpty(charsetName) ? str.getBytes() : str.getBytes(charsetName);
            byte[] result = md5.digest(bytes);
            origMD5 = byteArray2HexStr(result);
            return origMD5.toLowerCase();
        } catch (Exception var6) {
            throw new RuntimeException(var6);
        }
    }

    public static String md5(String str, int times) {
        String md5 = md5(str);

        for(int i = 0; i < times - 1; ++i) {
            md5 = md5(md5);
        }

        return md5;
    }

    public static boolean equals(String str, String md5Code) {
        return md5(str).equals(md5Code);
    }

    public static boolean equals(String str, String md5Code, int times) {
        return md5(str, times).equals(md5Code);
    }

    private static String byteArray2HexStr(byte[] bs) {
        StringBuffer sb = new StringBuffer();
        byte[] var2 = bs;
        int var3 = bs.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            byte b = var2[var4];
            sb.append(byte2HexStr(b));
        }

        return sb.toString();
    }

    private static String byte2HexStr(byte b) {
        String hexStr = null;
        int n = b;
        if (b < 0) {
            n = b & 255;
        }

        hexStr = Integer.toHexString(n / 16) + Integer.toHexString(n % 16);
        return hexStr.toUpperCase();
    }
}
