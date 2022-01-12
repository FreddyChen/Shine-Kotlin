package com.freddy.shine.kotlin.retrofit.api

import android.util.ArrayMap
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * 统一的请求方式
 * 包含
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

    @GET
    suspend fun get(@Url function: String): String

    @GET
    suspend fun get(@Url function: String, @QueryMap params: ArrayMap<String, Any?>): String

    @POST
    suspend fun post(@Url function: String): String

    @POST
    suspend fun post(@Url function: String, @Body body: RequestBody): String

    @PUT
    suspend fun put(@Url function: String): String

    @PUT
    suspend fun put(@Url function: String, @Body body: RequestBody): String

    @DELETE
    suspend fun delete(@Url function: String): String

    @DELETE
    suspend fun delete(@Url function: String, @Body body: RequestBody): String

    @GET
    fun syncGet(@Url function: String): Call<String>

    @GET
    fun syncGet(@Url function: String, @QueryMap params: ArrayMap<String, Any?>): Call<String>

    @POST
    fun syncPost(@Url function: String): Call<String>

    @POST
    fun syncPost(@Url function: String, @Body body: RequestBody): Call<String>

    @PUT
    fun syncPut(@Url function: String): Call<String>

    @PUT
    fun syncPut(@Url function: String, @Body body: RequestBody): Call<String>

    @DELETE
    fun syncDelete(@Url function: String): Call<String>

    @DELETE
    fun syncDelete(@Url function: String, @Body body: RequestBody): Call<String>
}