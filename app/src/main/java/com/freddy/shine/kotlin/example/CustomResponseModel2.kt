package com.freddy.shine.kotlin.example

import com.freddy.shine.kotlin.model.IResponseModel
import com.google.gson.annotations.SerializedName

/**
 *
 * @author: FreddyChen
 * @date  : 2022/01/17 16:05
 * @email : freddychencsc@gmail.com
 */
data class CustomResponseModel2<T>(
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: T?
) : IResponseModel {

    override fun isSuccessful() = this.message == "success"
}