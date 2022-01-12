package com.freddy.shine.kotlin.example
import com.google.gson.annotations.SerializedName


/**
 *
 * @author: FreddyChen
 * @date  : 2022/01/12 10:06
 * @email : freddychencsc@gmail.com
 */
data class Joke(
    @SerializedName("comment")
    val comment: String?,
    @SerializedName("down")
    val down: String?,
    @SerializedName("forward")
    val forward: String?,
    @SerializedName("header")
    val header: String?,
    @SerializedName("images")
    val images: Any?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("passtime")
    val passtime: String?,
    @SerializedName("sid")
    val sid: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("top_comments_content")
    val topCommentsContent: String?,
    @SerializedName("top_comments_header")
    val topCommentsHeader: String?,
    @SerializedName("top_comments_name")
    val topCommentsName: String?,
    @SerializedName("top_comments_uid")
    val topCommentsUid: String?,
    @SerializedName("top_comments_voiceuri")
    val topCommentsVoiceuri: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uid")
    val uid: String?,
    @SerializedName("up")
    val up: String?,
    @SerializedName("video")
    val video: String?
) {
    override fun toString(): String {
        return "Joke(comment=$comment, down=$down, forward=$forward, header=$header, images=$images, name=$name, passtime=$passtime, sid=$sid, text=$text, thumbnail=$thumbnail, topCommentsContent=$topCommentsContent, topCommentsHeader=$topCommentsHeader, topCommentsName=$topCommentsName, topCommentsUid=$topCommentsUid, topCommentsVoiceuri=$topCommentsVoiceuri, type=$type, uid=$uid, up=$up, video=$video)"
    }
}