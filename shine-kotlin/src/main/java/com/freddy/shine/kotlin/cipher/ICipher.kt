package com.freddy.shine.kotlin.cipher

/**
 * 加解密器抽象接口
 *
 * @see [DefaultCipher]
 * @author: FreddyChen
 * @date  : 2022/01/13 16:07
 * @email : freddychencsc@gmail.com
 */
interface ICipher {

    /**
     * 加密数据
     */
    fun encrypt(original: String?): String?

    /**
     * 解密数据
     */
    fun decrypt(original: String?): String?

    /**
     * 获取加解密字段名称
     */
    fun getParamName(): String
}