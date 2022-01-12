package com.freddy.shine.kotlin.exception

/**
 * 封装的请求异常
 * @author: FreddyChen
 * @date  : 2022/01/07 16:19
 * @email : freddychencsc@gmail.com
 */
class RequestException(
    val type: Type = Type.NATIVE,
    val statusCode: Int = 200,
    val errCode: Int = 0xfc1,
    val errMsg: String
) : Throwable(errMsg) {

    enum class Type {
        NATIVE,
        NETWORK
    }

    override fun toString(): String {
        return "RequestException(type=$type, statusCode=$statusCode, errCode=$errCode, errMsg='$errMsg')"
    }
}