package com.freddy.shine.kotlin.retrofit.manager

import android.util.ArrayMap
import android.util.Log
import com.freddy.shine.kotlin.AbstractRequestManager
import com.freddy.shine.kotlin.annotation.Parser
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
import kotlin.reflect.full.findAnnotation

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

    override suspend fun <T> request(
        options: RequestOptions,
        type: Type,
        parserCls: KClass<out IParser>
    ): T {
        ShineLog.d(log = "${javaClass.simpleName}#request() options = $options, type = $type, parserCls = $parserCls")

        val function = options.function
        if (function.isNullOrEmpty()) {
            throw RequestException(
                type = RequestException.Type.NATIVE,
                errMsg = "${javaClass.simpleName}#request() function is null or empty"
            )
        }
        val baseUrl = options.baseUrl
        if (baseUrl.isEmpty()) {
            throw RequestException(
                type = RequestException.Type.NATIVE,
                errMsg = "${javaClass.simpleName}#request() baseUrl is null or empty"
            )
        }
        return try {
            val headers = options.headers
            val apiService =
                RetrofitManager.INSTANCE.getRetrofit(baseUrl, headers)
                    .create(IApiService::class.java)
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
                        apiService.delete(function, convertParamsToRequestBody(params, contentType))
                    }
                }
            }
            parse(result, type, parserCls)
        } catch (e: HttpException) {
            e.printStackTrace()
            throw RequestException(type = RequestException.Type.NETWORK, errMsg = e.message ?: "")
        } catch (e: Exception) {
            e.printStackTrace()
            throw RequestException(type = RequestException.Type.NATIVE, errMsg = e.message ?: "")
        }
    }

    override fun <T> requestSync(
        options: RequestOptions,
        type: Type,
        parserCls: KClass<out IParser>
    ): T {
        TODO("Not yet implemented")
    }

    private fun convertParamsToRequestBody(
        params: ArrayMap<String, Any?>?,
        contentType: String
    ): RequestBody {
        return gson.toJson(params).toRequestBody(contentType.toMediaTypeOrNull())
    }
}