package com.freddy.shine.kotlin.example

import android.util.ArrayMap
import com.freddy.shine.kotlin.ShineKit
import com.freddy.shine.kotlin.cipher.ICipher
import com.freddy.shine.kotlin.config.NetworkConfig
import com.freddy.shine.kotlin.config.RequestMethod
import com.freddy.shine.kotlin.config.RequestOptions
import com.freddy.shine.kotlin.parser.IParser
import com.google.gson.reflect.TypeToken
import kotlin.reflect.KClass

/**
 * @author: FreddyChen
 * @date  : 2022/01/09 03:54
 * @email : freddychencsc@gmail.com
 */
open class BaseRepository {

    /**
     * 异步请求
     */
    suspend inline fun <reified T> request(
        requestMethod: RequestMethod,
        baseUrl: String = "https://api.oick.cn/",
        function: String,
        headers: ArrayMap<String, Any?>? = null,
        params: ArrayMap<String, Any?>? = null,
        contentType: String = NetworkConfig.DEFAULT_CONTENT_TYPE,
        parserCls: KClass<out IParser> = CustomParser1::class,
        cipherCls: KClass<out ICipher>? = null
    ): T {
        val optionsBuilder = RequestOptions.Builder()
            .setRequestMethod(requestMethod)
            .setBaseUrl(baseUrl)
            .setFunction(function)
            .setContentType(contentType)

        if (!headers.isNullOrEmpty()) {
            optionsBuilder.setHeaders(headers)
        }

        if (!params.isNullOrEmpty()) {
            optionsBuilder.setParams(params)
        }

        return ShineKit.getRequestManager()
            .request(optionsBuilder.build(), object : TypeToken<T>() {}.type, parserCls, cipherCls)
    }

    /**
     * 异步请求
     */
    inline fun <reified T> requestSync(
        requestMethod: RequestMethod,
        baseUrl: String = "https://www.wanandroid.com/",
        function: String,
        headers: ArrayMap<String, Any?>? = null,
        params: ArrayMap<String, Any?>? = null,
        contentType: String = NetworkConfig.DEFAULT_CONTENT_TYPE,
        parserCls: KClass<out IParser> = CustomParser1::class,
        cipherCls: KClass<out ICipher>? = null
    ): T {
        val optionsBuilder = RequestOptions.Builder()
            .setRequestMethod(requestMethod)
            .setBaseUrl(baseUrl)
            .setFunction(function)
            .setContentType(contentType)

        if (!headers.isNullOrEmpty()) {
            optionsBuilder.setHeaders(headers)
        }

        if (!params.isNullOrEmpty()) {
            optionsBuilder.setParams(params)
        }

        return ShineKit.getRequestManager()
            .syncRequest(
                optionsBuilder.build(),
                object : TypeToken<T>() {}.type,
                parserCls,
                cipherCls
            )
    }
}