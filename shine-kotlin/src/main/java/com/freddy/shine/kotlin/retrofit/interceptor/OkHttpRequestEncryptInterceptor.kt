package com.freddy.shine.kotlin.retrofit.interceptor

import com.freddy.shine.kotlin.config.RequestMethod
import com.freddy.shine.kotlin.retrofit.manager.RetrofitManager
import com.freddy.shine.kotlin.utils.ShineLog
import okhttp3.Interceptor
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.Buffer
import java.net.URLDecoder
import java.nio.charset.Charset

/**
 * OkHttp请求数据加密拦截器
 * @author: FreddyChen
 * @date  : 2022/01/13 15:05
 * @email : freddychencsc@gmail.com
 */
class OkHttpRequestEncryptInterceptor : OkHttpBaseInterceptor() {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val requestMethod = request.method.uppercase()
        val url = request.url
        val urlString = url.toString()
        when (requestMethod) {
            RequestMethod.GET.name, RequestMethod.DELETE.name -> {
                if (!url.encodedQuery.isNullOrEmpty()) {
                    try {
                        val api = "${url.scheme}://${url.host}:${url.port}${url.encodedPath}".trim()
                        RetrofitManager.INSTANCE.getCipher(
                            urlString.substring(
                                0,
                                urlString.indexOf("?")
                            )
                        )?.apply {
                            val newApi = "${api}?${getParamName()}=${encrypt(url.encodedQuery)}"
                            request = request.newBuilder().url(newApi).build()
                            ShineLog.i(log = "OkHttpRequestEncryptInterceptor#intercept() \napi = $api\nnewApi = $newApi")
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        ShineLog.e(log = "OkHttpRequestEncryptInterceptor#intercept() encrypt failure, reason:${e.message}")
                        chain.proceed(request)
                    }
                }
            }

            RequestMethod.POST.name, RequestMethod.PUT.name -> {
                request.body?.let { body ->
                    var charset: Charset? = null
                    val contentType = body.contentType()
                    if (contentType != null) {
                        charset = contentType.charset(Charsets.UTF_8)
                        // 如果contentType为multipart，则不进行加密
                        if (contentType.type.lowercase() == "multipart") {
                            return chain.proceed(request)
                        }
                    }
                    try {
                        RetrofitManager.INSTANCE.getCipher(urlString)?.apply {
                            val buffer = Buffer()
                            body.writeTo(buffer)
                            val requestData = URLDecoder.decode(
                                buffer.readString(charset!!).trim(),
                                Charsets.UTF_8.name()
                            )
                            val encryptData = encrypt(requestData)
                            encryptData?.apply {
                                val newRequestBody = toRequestBody(contentType)
                                val newRequestBuilder = request.newBuilder()
                                when (requestMethod) {
                                    RequestMethod.POST.name -> {
                                        newRequestBuilder.post(newRequestBody)
                                    }
                                    RequestMethod.PUT.name -> {
                                        newRequestBuilder.put(newRequestBody)
                                    }
                                }
                                request = newRequestBuilder.build()
                                ShineLog.i(log = "OkHttpRequestEncryptInterceptor#intercept() \nrequestBody = $body\nnewRequestBody = $newRequestBody\nrequestData = $requestData\nencryptData = $encryptData")
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        ShineLog.e(log = "OkHttpRequestEncryptInterceptor#intercept() encrypt failure, reason:${e.message}")
                        chain.proceed(request)
                    }
                }
            }
        }
        return chain.proceed(request)
    }
}