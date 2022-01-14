package com.freddy.shine.kotlin.cipher

/**
 * 默认的加解密器
 * <p>
 * 示例实现
 * * 在[encrypt]中实现加密逻辑
 * * 在[decrypt]中实现解密逻辑
 * * [getParamName]配置与服务端协商好的加密字段key
 * @author: FreddyChen
 * @date  : 2022/01/13 16:11
 * @email : freddychencsc@gmail.com
 */
class DefaultCipher : AbstractCipher() {

    override fun encrypt(original: String?): String? {
        return original
    }

    override fun decrypt(original: String?): String? {
        return original
    }

    override fun getParamName(): String {
        return "params"
    }
}