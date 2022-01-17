package com.freddy.shine.kotlin.example

import com.google.gson.annotations.SerializedName


/**
 *
 * @author: FreddyChen
 * @date  : 2022/01/17 16:17
 * @email : freddychencsc@gmail.com
 */
data class History(
    @SerializedName("date")
    val date: String?,
    @SerializedName("title")
    val title: String?
) {
    override fun toString(): String {
        return "History(date=$date, title=$title)"
    }
}