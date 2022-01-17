package com.freddy.shine.kotlin.example

import com.freddy.shine.kotlin.model.IResponseModel
import com.google.gson.annotations.SerializedName

/**
 *
 * @author: FreddyChen
 * @date  : 2022/01/17 16:05
 * @email : freddychencsc@gmail.com
 */
data class CustomResponseModel1<T>(
    @SerializedName("code")
    val code: String?,
    @SerializedName("day")
    val day: String?,
    @SerializedName("result")
    val result: T?
) : IResponseModel {

    override fun isSuccessful() = this.code == "1"

    override fun toString(): String {
        return "CustomResponseModel1(code=$code, day=$day, result=$result)"
    }
}