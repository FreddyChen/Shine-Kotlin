package com.freddy.shine.kotlin.example

import com.freddy.shine.kotlin.model.AbstractResponseModel

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
data class CustomResponseModel2<T>(val code: Int, val message: String, val result: T) :
    AbstractResponseModel() {

    override fun isSuccessful() = code == 200

    override fun toString(): String {
        return "CustomResponseModel(code=$code, message='$message', result=$result)"
    }
}