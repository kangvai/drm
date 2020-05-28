package com.kangvai.demo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * @author kangvai
 * @date 2020/4/30 17:13
 */
public class MD5Util {
    private static String []hex = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
    private static String MD5init(String text) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte []bytes = md5.digest(text.getBytes("utf8"));
            StringBuffer sb = new StringBuffer();
            for(byte b : bytes) {
                int tmp = b;
                if(tmp < 0) tmp += 128;
                int index1 = tmp / 16;
                int index2 = tmp % 16;
                sb.append(hex[index1]).append(hex[index2]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String message) {
        return MD5init(MD5init(message+"kangvai")+"kangvai");
    }
}
