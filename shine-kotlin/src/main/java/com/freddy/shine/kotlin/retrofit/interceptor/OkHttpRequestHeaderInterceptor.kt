package com.freddy.shine.kotlin.retrofit.interceptor

import com.freddy.shine.kotlin.retrofit.manager.RetrofitManager
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response

/**
 * OkHttp 请求头拦截器
 * @author: FreddyChen
 * @date  : 2022/01/09 22:28
 * @email : freddychencsc@gmail.com
 */
class OkHttpRequestHeaderInterceptor : OkHttpBaseInterceptor() {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(request.newBuilder().headers(getHeaders()).build())
    }

    private fun getHeaders(): Headers {
        val headersBuilder = Headers.Builder()
        RetrofitManager.INSTANCE.headers?.forEach {
            headersBuilder.add(it.key, it.value.toString())
        }

        return headersBuilder.build()
    }
}