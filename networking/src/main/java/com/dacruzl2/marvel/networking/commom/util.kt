package com.dacruzl2.marvel.networking.commom

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun buildMd5AuthParameter(timeStamp: Long, privateKey: String, publicKey: String): String {
    return try {
        val md = MessageDigest.getInstance("MD5")
        val messageDigest = md.digest(
            (
                timeStamp.toString() + privateKey +
                    publicKey
                ).toByteArray()
        )
        val number = BigInteger(1, messageDigest)
        var md5 = number.toString(16)
        while (md5.length < 32) {
            md5 = "0$md5"
        }
        md5
    } catch (e: NoSuchAlgorithmException) {
        ""
    }
}