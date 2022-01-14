package com.freddy.shine.kotlin.example
import com.google.gson.annotations.SerializedName


/**
 *
 * @author: FreddyChen
 * @date  : 2022/01/15 03:44
 * @email : freddychencsc@gmail.com
 */
data class Cat(
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("deleted")
    val deleted: Boolean?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("status")
    val status: Status?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("user")
    val user: String?,
    @SerializedName("__v")
    val v: Int?
) {
    override fun toString(): String {
        return "Cat(createdAt=$createdAt, deleted=$deleted, id=$id, status=$status, text=$text, type=$type, updatedAt=$updatedAt, user=$user, v=$v)"
    }
}

data class Status(
    @SerializedName("sentCount")
    val sentCount: Int?,
    @SerializedName("verified")
    val verified: Any?
) {
    override fun toString(): String {
        return "Status(sentCount=$sentCount, verified=$verified)"
    }
}