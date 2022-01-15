package com.freddy.shine.kotlin.retrofit.manager

import android.util.ArrayMap
import com.freddy.shine.kotlin.cipher.ICipher
import com.freddy.shine.kotlin.retrofit.converter.StringConverterFactory
import com.freddy.shine.kotlin.retrofit.interceptor.OkHttpLoggingInterceptor
import com.freddy.shine.kotlin.retrofit.interceptor.OkHttpResponseDecryptInterceptor
import com.freddy.shine.kotlin.retrofit.interceptor.OkHttpRequestEncryptInterceptor
import com.freddy.shine.kotlin.retrofit.interceptor.OkHttpRequestHeaderInterceptor
import com.freddy.shine.kotlin.utils.ShineLog
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

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

    /**
     * Retrofit集合
     * key:     baseUrl
     * value:   Retrofit
     */
    private val retrofitMap: ConcurrentHashMap<String, Retrofit> by lazy {
        ConcurrentHashMap()
    }

    /**
     * 加解密器Cls集合
     * key:     url(baseUrl+function)
     * value:   Cipher Clazz
     */
    private val cipherClsMap: HashMap<String, KClass<out ICipher>?> by lazy {
        hashMapOf()
    }

    /**
     * 加解密器集合
     * key:     Cipher Clazz
     * value:   Cipher instance
     */
    private val cipherMap: ConcurrentHashMap<KClass<out ICipher>, ICipher> by lazy {
        ConcurrentHashMap()
    }

    /**
     * 请求头集合
     * key:     url(baseUrl+function)
     * value:   headers
     */
    private val headersMap: HashMap<String, ArrayMap<String, Any?>?> by lazy {
        hashMapOf()
    }

    /**
     * 获取OkHttpClient
     *
     * @return
     */
    private fun getOkHttpClient(): OkHttpClient {
        val timeout = 60 * 1000L
        val builder = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .addInterceptor(OkHttpRequestHeaderInterceptor())
            .addInterceptor(OkHttpLoggingInterceptor())
            .addInterceptor(OkHttpRequestEncryptInterceptor())
            .addInterceptor(OkHttpResponseDecryptInterceptor())
        return builder.build()
    }

    /**
     * 根据baseUrl获取对应的Retrofit实例
     * 首次获取时同时保存起来，方便下次直接获取
     */
    fun getRetrofit(baseUrl: String): Retrofit {
        return retrofitMap.getOrPut(baseUrl) {
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(StringConverterFactory.create())
                .client(getOkHttpClient())
                .build()
        }
    }

    /**
     * 临时保存接口请求头
     * @param url baseUrl+function
     */
    fun saveHeaders(url: String, headers: ArrayMap<String, Any?>?) {
        if (headersMap.containsKey(url)) return
        headersMap[url] = headers
    }

    /**
     * 获取接口请求头，并移除
     * @param url baseUrl+function
     */
    fun getHeaders(url: String): ArrayMap<String, Any?>? {
        if (!headersMap.containsKey(url)) return null
        val headers = headersMap[url]
        headers?.apply {
            headersMap.remove(url)
        }
        return headers
    }

    /**
     * 临时保存接口加解密器
     * @param url baseUrl+function
     */
    fun saveCipher(url: String, cipherCls: KClass<out ICipher>?) {
        if(cipherClsMap.containsKey(url)) return
        cipherClsMap[url] = cipherCls
    }

    /**
     * 获取接口加解密器
     * @param url baseUrl+function
     */
    fun getCipher(url: String): ICipher? {
        val cipherCls = cipherClsMap[url] ?: return null
        val cipher: ICipher = cipherMap.getOrPut(cipherCls) {
            Class.forName(cipherCls.java.name).newInstance() as ICipher
        }
        ShineLog.i(log = "RetrofitManager#getCipher() \nurl = $url\ncipherCls = $cipherCls\ncipher = $cipher")
        return cipher
    }

    /**
     * 移除接口加解密器
     */
    fun removeCipherCls(url: String) {
        if (!cipherClsMap.containsKey(url)) return
        cipherClsMap.remove(url)
    }
}