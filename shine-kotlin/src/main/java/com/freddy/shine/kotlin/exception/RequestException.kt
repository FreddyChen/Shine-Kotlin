package com.freddy.shine.kotlin.exception

/**
 * 封装的请求异常
 *
 * * [type]         异常类型
 * * [statusCode]   http状态码
 * * [errCode]      业务错误码
 * * [errMsg]       业务错误信息
 * @author: FreddyChen
 * @date  : 2022/01/07 16:19
 * @email : freddychencsc@gmail.com
 */
class RequestException(
    val type: Type = Type.NATIVE,
    val statusCode: Int = 200,
    val errCode: Int? = null,
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