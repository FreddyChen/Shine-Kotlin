package com.freddy.shine.kotlin.example
import com.google.gson.annotations.SerializedName


/**
 *
 * @author: FreddyChen
 * @date  : 2022/01/17 16:10
 * @email : freddychencsc@gmail.com
 */
data class Journalism(

    @SerializedName("code")
    val code: String?,
    @SerializedName("content")
    val content: String?
) {
    override fun toString(): String {
        return "Journalism(code=$code, content=$content)"
    }
}