package com.freddy.shine.kotlin.example

import com.freddy.shine.kotlin.model.IResponseModel

/**
 * 自定义的ResponseModel
 *
 * 包含：
 * * code: Int
 * * message: String
 * * result: T
 * @author: FreddyChen
 * @date  : 2022/01/08 23:44
 * @email : freddychencsc@gmail.com
 */
data class CustomResponseModel2<T>(val result: T) :
    IResponseModel {

    override fun isSuccessful() = true

    override fun toString(): String {
        return "CustomResponseModel2(result=$result)"
    }
}