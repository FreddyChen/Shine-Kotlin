package com.freddy.shine.kotlin.retrofit.manager

import android.util.ArrayMap
import com.freddy.shine.kotlin.AbstractRequestManager
import com.freddy.shine.kotlin.cipher.ICipher
import com.freddy.shine.kotlin.exception.RequestException
import com.freddy.shine.kotlin.config.RequestMethod
import com.freddy.shine.kotlin.config.RequestOptions
import com.freddy.shine.kotlin.parser.IParser
import com.freddy.shine.kotlin.retrofit.api.IApiService
import com.freddy.shine.kotlin.utils.ShineLog
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.lang.reflect.Type
import kotlin.reflect.KClass

/**
 * 基于Retrofit实现的RequestManager
 * @author: FreddyChen
 * @date  : 2022/01/07 13:50
 * @email : freddychencsc@gmail.com
 */
internal class RetrofitRequestManager private constructor() : AbstractRequestManager() {

    companion object {
        val INSTANCE: RetrofitRequestManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitRequestManager()
        }
    }

    /**
     * 异步请求
     * @param options   请求参数
     * @param type      数据类型映射
     * @param parserCls 数据解析器
     * @param parserCls 数据加解密器
     */
    override suspend fun <T> request(
        options: RequestOptions,
        type: Type,
        parserCls: KClass<out IParser>,
        cipherCls: KClass<out ICipher>?
    ): T {
        ShineLog.d(log = "${javaClass.simpleName}#request()\noptions = $options\ntype = $type\nparserCls = $parserCls\ncipherCls = $cipherCls")
        val function = options.function
        if (function.isNullOrEmpty()) {
            throw RequestException(
                type = RequestException.Type.NATIVE,
                errMsg = "${javaClass.simpleName}#request failure, reason: function is null or empty"
            )
        }
        val baseUrl = options.baseUrl
        if (baseUrl.isNullOrEmpty()) {
            throw RequestException(
                type = RequestException.Type.NATIVE,
                errMsg = "${javaClass.simpleName}#request failure, reason: baseUrl is null or empty"
            )
        }
        val url = "${options.baseUrl}${options.function}"
        return try {
            val apiService =
                RetrofitManager.INSTANCE.getRetrofit(baseUrl)
                    .create(IApiService::class.java)
            val headers = options.headers
            RetrofitManager.INSTANCE.saveHeaders("${baseUrl}${function}", headers)

            cipherCls?.apply {
                RetrofitManager.INSTANCE.saveCipher("${baseUrl}${function}", this)
            }
            val params = options.params
            val contentType = options.contentType
            val result = when (options.requestMethod) {
                RequestMethod.GET -> {
                    if (params.isNullOrEmpty()) {
                        apiService.get(function)
                    } else {
                        apiService.get(function, params)
                    }
                }
                RequestMethod.POST -> {
                    if (params.isNullOrEmpty()) {
                        apiService.post(function)
                    } else {
                        apiService.post(function, convertParamsToRequestBody(params, contentType))
                    }
                }
                RequestMethod.PUT -> {
                    if (params.isNullOrEmpty()) {
                        apiService.put(function)
                    } else {
                        apiService.put(function, convertParamsToRequestBody(params, contentType))
                    }
                }
                RequestMethod.DELETE -> {
                    if (params.isNullOrEmpty()) {
                        apiService.delete(function)
                    } else {
                        apiService.delete(function, params)
                    }
                }
            }
            parse(url, result, type, parserCls)
        } catch (e: HttpException) {
            val response = e.response()
            val errorBody = response?.errorBody()?.string()
            val statusCode = response?.code()
            val errorMsg = response?.message()
            throw RequestException(
                type = RequestException.Type.NETWORK,
                url = url,
                statusCode = statusCode,
                errMsg = errorMsg ?: e.message(),
                errBody = errorBody
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw RequestException(
                type = RequestException.Type.NATIVE,
                url = url,
                errMsg = e.message ?: ""
            )
        }
    }

    /**
     * 同步请求
     * @param options   请求参数
     * @param type      数据类型映射
     * @param parserCls 数据解析器
     * @param cipherCls 数据加解密器
     */
    override fun <T> syncRequest(
        options: RequestOptions,
        type: Type,
        parserCls: KClass<out IParser>,
        cipherCls: KClass<out ICipher>?
    ): T {
        ShineLog.d(log = "${javaClass.simpleName}#syncRequest()\noptions = $options\ntype = $type\nparserCls = $parserCls\ncipherCls = $cipherCls")
        val function = options.function
        if (function.isNullOrEmpty()) {
            throw RequestException(
                type = RequestException.Type.NATIVE,
                errMsg = "${javaClass.simpleName}#syncRequest failure, reason: function is null or empty"
            )
        }
        val baseUrl = options.baseUrl
        if (baseUrl.isNullOrEmpty()) {
            throw RequestException(
                type = RequestException.Type.NATIVE,
                errMsg = "${javaClass.simpleName}#syncRequest failure, reason: baseUrl is null or empty"
            )
        }
        val url = "${options.baseUrl}${options.function}"
        return try {
            val apiService =
                RetrofitManager.INSTANCE.getRetrofit(baseUrl)
                    .create(IApiService::class.java)
            val headers = options.headers
            RetrofitManager.INSTANCE.saveHeaders("${baseUrl}${function}", headers)
            val params = options.params
            val contentType = options.contentType
            val result = when (options.requestMethod) {
                RequestMethod.GET -> {
                    if (params.isNullOrEmpty()) {
                        apiService.syncGet(function)
                    } else {
                        apiService.syncGet(function, params)
                    }
                }
                RequestMethod.POST -> {
                    if (params.isNullOrEmpty()) {
                        apiService.syncPost(function)
                    } else {
                        apiService.syncPost(
                            function,
                            convertParamsToRequestBody(params, contentType)
                        )
                    }
                }
                RequestMethod.PUT -> {
                    if (params.isNullOrEmpty()) {
                        apiService.syncPut(function)
                    } else {
                        apiService.syncPut(
                            function,
                            convertParamsToRequestBody(params, contentType)
                        )
                    }
                }
                RequestMethod.DELETE -> {
                    if (params.isNullOrEmpty()) {
                        apiService.syncDelete(function)
                    } else {
                        apiService.syncDelete(
                            function,
                            params
                        )
                    }
                }
            }
            parse(url, result.execute().body()!!, type, parserCls)
        } catch (e: HttpException) {
            e.printStackTrace()
            val response = e.response()
            val errorBody = response?.errorBody()?.string()
            val statusCode = response?.code()
            val errorMsg = response?.message()
            throw RequestException(
                type = RequestException.Type.NETWORK,
                url = url,
                statusCode = statusCode,
                errMsg = errorMsg ?: e.message(),
                errBody = errorBody
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw RequestException(
                type = RequestException.Type.NATIVE,
                url = url,
                errMsg = e.message ?: ""
            )
        }
    }

    /**
     * 将请求参数转换到RequestBody
     * POST/PUT请求适用
     */
    private fun convertParamsToRequestBody(
        params: ArrayMap<String, Any?>?,
        contentType: String
    ): RequestBody {
        return gson.toJson(params).toRequestBody(contentType.toMediaTypeOrNull())
    }
}