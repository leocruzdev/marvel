package com.dacruzl2.marvel.networking.commom;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

    private static Util sInstance;

    public static Util getInstance() {
        if (sInstance == null) {
            sInstance = new Util();
        }
        return sInstance;
    }

    public static String buildMd5AuthParameter(long timeStamp, String privateKey, String publicKey) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest((timeStamp + privateKey
                    + publicKey).getBytes());
            BigInteger number = new BigInteger(1, messageDigest);

            String md5 = number.toString(16);
            while (md5.length() < 32) {
                md5 = 0 + md5;
            }
            return md5;

        } catch (NoSuchAlgorithmException e) {
            //Log.e("DataManager", "Error hashing required parameters: " + e.getMessage());
            return "";
        }
    }
}
