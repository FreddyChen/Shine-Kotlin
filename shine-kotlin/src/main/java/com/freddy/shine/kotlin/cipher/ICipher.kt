package com.freddy.shine.kotlin.cipher

/**
 * 加解密器抽象接口
 *
 *
 * @author: FreddyChen
 * @date  : 2022/01/13 16:07
 * @email : freddychencsc@gmail.com
 */
interface ICipher {
    fun encrypt(original: String?): String?
    fun decrypt(original: String?): String?
    fun getParamName(): String
}