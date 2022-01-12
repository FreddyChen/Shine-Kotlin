package com.freddy.shine.kotlin.example

import com.google.gson.annotations.SerializedName

/**
 *
 * @author: FreddyChen
 * @date  : 2022/01/07 18:32
 * @email : freddychencsc@gmail.com
 */
data class ArticleList(val curPage: Int, val datas: ArrayList<Article>) {

    override fun toString(): String {
        return "ArticleList(curPage=$curPage, datas=$datas)"
    }
}

data class Article(
    @SerializedName("apkLink")
    val apkLink: String?,
    @SerializedName("audit")
    val audit: Int?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("canEdit")
    val canEdit: Boolean?,
    @SerializedName("chapterId")
    val chapterId: Int?,
    @SerializedName("chapterName")
    val chapterName: String?,
    @SerializedName("collect")
    val collect: Boolean?,
    @SerializedName("courseId")
    val courseId: Int?,
    @SerializedName("desc")
    val desc: String?,
    @SerializedName("descMd")
    val descMd: String?,
    @SerializedName("envelopePic")
    val envelopePic: String?,
    @SerializedName("fresh")
    val fresh: Boolean?,
    @SerializedName("host")
    val host: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("niceDate")
    val niceDate: String?,
    @SerializedName("niceShareDate")
    val niceShareDate: String?,
    @SerializedName("origin")
    val origin: String?,
    @SerializedName("prefix")
    val prefix: String?,
    @SerializedName("projectLink")
    val projectLink: String?,
    @SerializedName("publishTime")
    val publishTime: Long?,
    @SerializedName("realSuperChapterId")
    val realSuperChapterId: Int?,
    @SerializedName("selfVisible")
    val selfVisible: Int?,
    @SerializedName("shareDate")
    val shareDate: Long?,
    @SerializedName("shareUser")
    val shareUser: String?,
    @SerializedName("superChapterId")
    val superChapterId: Int?,
    @SerializedName("superChapterName")
    val superChapterName: String?,
    @SerializedName("tags")
    val tags: List<Any>?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: Int?,
    @SerializedName("userId")
    val userId: Int?,
    @SerializedName("visible")
    val visible: Int?,
    @SerializedName("zan")
    val zan: Int?
) {
    override fun toString(): String {
        return "Article(apkLink=$apkLink, audit=$audit, author=$author, canEdit=$canEdit, chapterId=$chapterId, chapterName=$chapterName, collect=$collect, courseId=$courseId, desc=$desc, descMd=$descMd, envelopePic=$envelopePic, fresh=$fresh, host=$host, id=$id, link=$link, niceDate=$niceDate, niceShareDate=$niceShareDate, origin=$origin, prefix=$prefix, projectLink=$projectLink, publishTime=$publishTime, realSuperChapterId=$realSuperChapterId, selfVisible=$selfVisible, shareDate=$shareDate, shareUser=$shareUser, superChapterId=$superChapterId, superChapterName=$superChapterName, tags=$tags, title=$title, type=$type, userId=$userId, visible=$visible, zan=$zan)"
    }
}