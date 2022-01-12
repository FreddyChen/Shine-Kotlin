package com.freddy.shine.kotlin.retrofit.interceptor

import com.freddy.shine.kotlin.config.RequestMethod
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.lang.StringBuilder
import java.nio.charset.Charset

/**
 * OkHttp拦截器基类，所有OkHttp拦截器都继承此类
 *
 * @see [OkHttpLoggingInterceptor]
 * @author: FreddyChen
 * @date  : 2022/01/09 19:11
 * @email : freddychencsc@gmail.com
 */
abstract class OkHttpBaseInterceptor : Interceptor {

    open fun getRequestInfo(request: Request, method: String): String? {
        when (method) {
            RequestMethod.GET.name -> {
                val httpUrl = request.url
                val paramKeys = httpUrl.queryParameterNames
                if (paramKeys.isNullOrEmpty()) {
                    return null
                }
                val resultBuilder = StringBuilder()
                for (key in paramKeys) {
                    val value = httpUrl.queryParameter(key)
                    resultBuilder.append("$key=$value\n")
                }
                resultBuilder.deleteAt(resultBuilder.length - 1)
                return resultBuilder.toString()
            }
            RequestMethod.POST.name, RequestMethod.PUT.name -> {
                val requestBody = request.body ?: return null
                try {
                    val bufferedSink = Buffer()
                    requestBody.writeTo(bufferedSink)
                    val charset: Charset = Charset.forName("utf-8")
                    return bufferedSink.readString(charset)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return null
    }

    open fun getResponseInfo(response: Response): String? {
        var str: String? = null
//        if (!response.isSuccessful) {
//            return response.message
//        }
        val responseBody = response.body
        val contentLength = responseBody!!.contentLength()
        val source = responseBody.source()
        try {
            source.request(Long.MAX_VALUE)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val buffer = source.buffer
        val charset = Charset.forName(Charsets.UTF_8.name())
        if (contentLength != 0L) {
            str = buffer.clone().readString(charset)
        }
        return str
    }
}