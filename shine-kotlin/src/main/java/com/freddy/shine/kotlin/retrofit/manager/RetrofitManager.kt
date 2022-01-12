package com.freddy.shine.kotlin.retrofit.manager

import android.util.ArrayMap
import com.freddy.shine.kotlin.retrofit.converter.StringConverterFactory
import com.freddy.shine.kotlin.retrofit.interceptor.OkHttpLoggingInterceptor
import com.freddy.shine.kotlin.retrofit.interceptor.OkHttpRequestHeaderInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

/**
 * Retrofit管理类，提供获取OkHttpClient、Retrofit等方法
 * @author: FreddyChen
 * @date  : 2022/01/07 14:48
 * @email : freddychencsc@gmail.com
 */
class RetrofitManager private constructor() {

    companion object {
        val INSTANCE: RetrofitManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitManager()
        }
    }

    private val retrofitMap: ConcurrentHashMap<String, Retrofit> by lazy {
        ConcurrentHashMap()
    }

    var headers: ArrayMap<String, Any?>? = null

    private fun getOkHttpClient(): OkHttpClient {
        val timeout = 60 * 1000L
        val builder = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .addInterceptor(OkHttpRequestHeaderInterceptor())
            .addInterceptor(OkHttpLoggingInterceptor())
        return builder.build()
    }

    fun getRetrofit(baseUrl: String, headers: ArrayMap<String, Any?>?): Retrofit {
        this.headers = headers
        return retrofitMap.getOrPut(baseUrl) {
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(StringConverterFactory.create())
                .client(getOkHttpClient())
                .build()
        }
    }
}