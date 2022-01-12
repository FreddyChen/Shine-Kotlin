package com.freddy.shine.kotlin.config

import android.util.ArrayMap
import com.freddy.shine.kotlin.parser.DefaultParser
import com.freddy.shine.kotlin.parser.IParser
import kotlin.reflect.KClass

/**
 * 请求配置
 *
 * 包含
 * * requestMethod 请求方式
 * * baseUrl baseUrl
 * * function 接口地址
 * * headers 请求头
 * * params 请求参数
 * * contentType contentType
 * @author: FreddyChen
 * @date  : 2022/01/06 17:42
 * @email : freddychencsc@gmail.com
 */
class RequestOptions private constructor(builder: Builder) {

    val requestMethod: RequestMethod
    val baseUrl: String
    val function: String?
    val headers: ArrayMap<String, Any?>?
    val params: ArrayMap<String, Any?>?
    val contentType: String

    init {
        this.requestMethod = builder.requestMethod
        this.baseUrl = builder.baseUrl
        this.function = builder.function
        this.headers = builder.headers
        this.params = builder.params
        this.contentType = builder.contentType
    }

    class Builder {
        internal var requestMethod: RequestMethod = RequestMethod.GET
        internal var baseUrl: String = "http://www.baidu.com"
        internal var function: String? = null
        internal var headers: ArrayMap<String, Any?>? = null
        internal var params: ArrayMap<String, Any?>? = null
        internal var contentType: String = NetworkConfig.DEFAULT_CONTENT_TYPE

        fun setRequestMethod(requestMethod: RequestMethod): Builder {
            this.requestMethod = requestMethod
            return this
        }

        fun setBaseUrl(baseUrl: String): Builder {
            this.baseUrl = baseUrl
            return this
        }

        fun setFunction(function: String): Builder {
            this.function = function
            return this
        }

        fun setHeaders(headers: ArrayMap<String, Any?>): Builder {
            this.headers = headers
            return this
        }

        fun setParams(params: ArrayMap<String, Any?>): Builder {
            this.params = params
            return this
        }

        fun setContentType(contentType: String): Builder {
            this.contentType = contentType
            return this
        }

        fun build(): RequestOptions {
            return RequestOptions(this)
        }
    }

    override fun toString(): String {
        return "RequestOptions(requestMethod=$requestMethod, baseUrl='$baseUrl', function=$function, headers=$headers, params=$params, contentType='$contentType')"
    }
}