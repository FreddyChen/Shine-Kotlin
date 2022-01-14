package com.freddy.shine.kotlin.model

/**
 * 默认的ResponseModel
 *
 * * code 返回码
 * * msg  返回信息
 * * data 返回数据
 * @author: FreddyChen
 * @date  : 2022/01/06 18:34
 * @email : freddychencsc@gmail.com
 */
internal data class DefaultResponseModel<T>(var code: Int?, var msg: String?, val data: T?) :
    AbstractResponseModel() {

    override fun isSuccessful() = code == 0

    override fun toString(): String {
        return "DefaultResponseModel(code=$code, msg=$msg, data=$data)"
    }
}