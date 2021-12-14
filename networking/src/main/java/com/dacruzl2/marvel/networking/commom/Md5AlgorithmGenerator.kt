package com.dacruzl2.marvel.networking.commom

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun md5Algorithm(privateKey: String, publickKey: String, timeStamp: Long): String {
    val md = MessageDigest.getInstance("MD5")

    val codeToEncrypt = privateKey + publickKey + timeStamp

    return BigInteger(1, md.digest(codeToEncrypt.toByteArray()))
        .toString(16)
        .padStart(32, '0')
}

