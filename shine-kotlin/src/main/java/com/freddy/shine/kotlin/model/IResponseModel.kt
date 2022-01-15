package com.freddy.shine.kotlin.model

/**
 * ResponseModel接口，自定义ResponseModel需继承此接口并实现[isSuccessful]方法
 *
 *
 * @author: FreddyChen
 * @date  : 2022/01/15 00:51
 * @email : freddychencsc@gmail.com
 */
interface IResponseModel {

    fun isSuccessful(): Boolean
}