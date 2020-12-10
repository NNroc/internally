package com.cumt.internally.utils;

import java.security.MessageDigest;

/**
 * MD5 加密
 *
 * @author NNroc
 * @date 2020/5/12 19:56
 */
public class MD5Util {

    /**
     * 获取字符串加密后结果
     *
     * @param text 需要加密的字符串
     * @return
     */
    public static String md5(String text) {
        return md5(text, null);
    }

    /**
     * 获取字符串加密后结果
     *
     * @param text
     * @param prifix
     * @return
     */
    public static String md5(String text, String prifix) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        if (null != prifix && !"".endsWith(prifix)) {
            text = prifix + text;
        }
        byte[] originalByte = text.getBytes();
        md5.update(originalByte);

        byte[] newByte = md5.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < newByte.length; i++) {
            if ((newByte[i] & 0xff) < 0x10) {
                stringBuilder.append("0");
            }
            stringBuilder.append(Long.toString(newByte[i] & 0xff, 16));
        }
        return stringBuilder.toString().toUpperCase();
    }

    public static String md5(String text, int userId) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        text = String.valueOf(userId) + text;

        byte[] originalByte = text.getBytes();
        md5.update(originalByte);

        byte[] newByte = md5.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < newByte.length; i++) {
            if ((newByte[i] & 0xff) < 0x10) {
                stringBuilder.append("0");
            }
            stringBuilder.append(Long.toString(newByte[i] & 0xff, 16));
        }
        return stringBuilder.toString().toUpperCase();
    }

//    public static void main(String[] args) {
//        System.out.println(MD5Util.md5("admin", "admin"));
//        System.out.println(MD5Util.md5("admin", "admin"));
//        System.out.println(MD5Util.md5("admin"));
//    }
}
