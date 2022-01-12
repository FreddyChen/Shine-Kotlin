package com.freddy.shine.kotlin.example

import com.freddy.shine.kotlin.model.AbstractResponseModel

/**
 * 自定义的ResponseModel
 *
 * 包含：
 * * errorCode: Int
 * * errorMsg: String
 * * data: T
 * @author: FreddyChen
 * @date  : 2022/01/08 23:44
 * @email : freddychencsc@gmail.com
 */
data class CustomResponseModel1<T>(val errorCode: Int, val errorMsg: String, val data: T) :
    AbstractResponseModel() {

    override fun isSuccessful() = errorCode == 0

    override fun toString(): String {
        return "CustomResponseModel1(errorCode=$errorCode, errorMsg='$errorMsg', data=$data)"
    }
}