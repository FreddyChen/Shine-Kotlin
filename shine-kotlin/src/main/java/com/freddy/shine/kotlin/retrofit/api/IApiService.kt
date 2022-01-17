package com.freddy.shine.kotlin.retrofit.api

import android.util.ArrayMap
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * 统一的请求方式
 *
 * * [get]          function: String
 * * [get]          function: String, params: ArrayMap<String, Any?>
 * * [post]         function: String
 * * [post]         function: String, body: RequestBody
 * * [put]          function: String
 * * [put]          function: String, body: RequestBody
 * * [delete]       function: String
 * * [delete]       function: String, body: RequestBody
 * * [syncGet]      function: String
 * * [syncGet]      function: String, body: RequestBody
 * * [syncPost]     function: String
 * * [syncPost]     function: String, body: RequestBody
 * * [syncPut]      function: String
 * * [syncPut]      function: String, body: RequestBody
 * * [syncDelete]   function: String
 * * [syncDelete]   function: String, body: RequestBody
 * @author: FreddyChen
 * @date  : 2022/01/07 11:08
 * @email : freddychencsc@gmail.com
 */
internal interface IApiService {

    /**
     * 异步GET请求
     * 无参
     */
    @GET
    suspend fun get(@Url function: String): String

    /**
     * 异步GET请求
     * 带参
     */
    @GET
    suspend fun get(@Url function: String, @QueryMap params: ArrayMap<String, Any?>): String

    /**
     * 异步POST请求
     * 无参
     */
    @POST
    suspend fun post(@Url function: String): String

    /**
     * 异步POST请求
     * 带参
     */
    @POST
    suspend fun post(@Url function: String, @Body body: RequestBody): String

    /**
     * 异步PUT请求
     * 无参
     */
    @PUT
    suspend fun put(@Url function: String): String

    /**
     * 异步PUT请求
     * 带参
     */
    @PUT
    suspend fun put(@Url function: String, @Body body: RequestBody): String

    /**
     * 异步DELETE请求
     * 无参
     */
    @DELETE
    suspend fun delete(@Url function: String): String

    /**
     * 异步DELETE请求
     * 带参
     */
    @DELETE
    suspend fun delete(@Url function: String, @QueryMap params: ArrayMap<String, Any?>): String

    /**
     * 同步GET请求
     * 无参
     */
    @GET
    fun syncGet(@Url function: String): Call<String>

    /**
     * 同步GET请求
     * 带参
     */
    @GET
    fun syncGet(@Url function: String, @QueryMap params: ArrayMap<String, Any?>): Call<String>

    /**
     * 同步POST请求
     * 无参
     */
    @POST
    fun syncPost(@Url function: String): Call<String>

    /**
     * 同步POST请求
     * 带参
     */
    @POST
    fun syncPost(@Url function: String, @Body body: RequestBody): Call<String>

    /**
     * 同步PUT请求
     * 无参
     */
    @PUT
    fun syncPut(@Url function: String): Call<String>

    /**
     * 同步PUT请求
     * 带参
     */
    @PUT
    fun syncPut(@Url function: String, @Body body: RequestBody): Call<String>

    /**
     * 同步DELETE请求
     * 无参
     */
    @DELETE
    fun syncDelete(@Url function: String): Call<String>

    /**
     * 同步DELETE请求
     * 带参
     */
    @DELETE
    fun syncDelete(@Url function: String, @QueryMap params: ArrayMap<String, Any?>): Call<String>
}