package com.freddy.shine.kotlin.retrofit.interceptor

import com.freddy.shine.kotlin.config.RequestMethod
import com.freddy.shine.kotlin.retrofit.manager.RetrofitManager
import com.freddy.shine.kotlin.utils.ShineLog
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * OkHttp请求数据解密拦截器
 * @author: FreddyChen
 * @date  : 2022/01/13 15:05
 * @email : freddychencsc@gmail.com
 */
class OkHttpRequestDecryptInterceptor : Interceptor {

    companion object {
        private const val TAG = "OkHttpRequestDecryptInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)
        val requestUrl = request.url
        val urlString = requestUrl.toString()
        val url: String = when (request.method) {
            RequestMethod.GET.name, RequestMethod.DELETE.name -> {
                if (requestUrl.encodedQuery.isNullOrEmpty()) {
                    urlString
                } else {
                    urlString.substring(
                        0,
                        urlString.indexOf("?")
                    )
                }
            }
            RequestMethod.POST.name, RequestMethod.PUT.name -> {
                urlString
            }
            else -> {
                urlString
            }
        }
        if (response.isSuccessful) {
            val responseBody = response.body
            responseBody?.let { body ->
                try {
                    RetrofitManager.INSTANCE.getCipher(url)?.apply {
                        val source = body.source()
                        source.request(Long.MAX_VALUE)
                        val buffer = source.buffer
                        var charset = Charsets.UTF_8
                        val contentType = body.contentType()
                        contentType?.apply {
                            charset = contentType.charset(charset)!!
                        }
                        val responseData = buffer.clone().readString(charset).trim()
                        val decryptData = decrypt(responseData)
                        val newResponseBody = responseData.toResponseBody(contentType)
                        response = response.newBuilder().body(newResponseBody).build()
                        ShineLog.i(
                            tag = TAG,
                            log = "intercept() \nresponseBody = $body\nnewResponseBody = $newResponseBody\nresponseData = $responseData\ndecryptData = $decryptData"
                        )
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    ShineLog.e(
                        tag = TAG,
                        "log = intercept() decrypt failure, reason:${e.message}"
                    )
                }
            }
        }
        RetrofitManager.INSTANCE.removeCipherCls(url)
        return response
    }
}