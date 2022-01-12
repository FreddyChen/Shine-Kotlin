package com.freddy.shine.kotlin.model

/**
 * 抽象的ResponseModel，所有ResponseModel都应继承此类，并重写[isSuccessful]方法
 *
 * @see [DefaultResponseModel]
 * @author: FreddyChen
 * @date  : 2022/01/08 21:52
 * @email : freddychencsc@gmail.com
 */
abstract class AbstractResponseModel {

    abstract fun isSuccessful(): Boolean
}