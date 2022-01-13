package com.freddy.shine.kotlin.retrofit.interceptor

import com.freddy.shine.kotlin.config.RequestMethod
import com.freddy.shine.kotlin.utils.ShineLog
import okhttp3.Interceptor
import okhttp3.Response

/**
 * OkHttp请求数据解密拦截器
 * @author: FreddyChen
 * @date  : 2022/01/13 15:05
 * @email : freddychencsc@gmail.com
 */
class OkHttpRequestDecryptInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val charset = Charsets.UTF_8
        val requestMethod = request.method.uppercase()
        ShineLog.i(log = "OkHttpRequestDecryptInterceptor#intercept() requestMethod = $requestMethod")
        when(requestMethod) {
            RequestMethod.GET.name, RequestMethod.DELETE.name -> {
                ShineLog.i(log = "OkHttpRequestDecryptInterceptor#intercept() GET or DELETE")
            }
            RequestMethod.POST.name, RequestMethod.PUT.name -> {
                ShineLog.i(log = "OkHttpRequestDecryptInterceptor#intercept() POST or PUT")
            }
        }
        return chain.proceed(request)
    }
}