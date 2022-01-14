package com.freddy.shine.kotlin.config

import com.freddy.shine.kotlin.parser.DefaultParser
import com.freddy.shine.kotlin.parser.IParser
import kotlin.reflect.KClass

/**
 * Shine配置类
 *
 * * logEnable          日志开关
 * * logTag             日志Tag
 * * baseUrl     默认baseUrl
 * * parserCls   默认数据解析器cls
 * @author: FreddyChen
 * @date  : 2022/01/11 10:05
 * @email : freddychencsc@gmail.com
 */
class ShineOptions(builder: Builder) {

    var logEnable: Boolean
    var logTag: String
    var baseUrl: String?
    var parserCls: KClass<out IParser>

    init {
        this.logEnable = builder.logEnable
        this.logTag = builder.logTag
        this.baseUrl = builder.baseUrl
        this.parserCls = builder.parserCls
    }

    class Builder {
        internal var logEnable: Boolean = ShineConfig.DEFAULT_LOG_ENABLE
        internal var logTag: String = ShineConfig.DEFAULT_LOG_TAG
        internal var baseUrl: String? = null
        internal var parserCls: KClass<out IParser> = DefaultParser::class

        fun setLogEnable(logEnable: Boolean): Builder {
            this.logEnable = logEnable
            return this
        }

        fun setLogTag(logTag: String): Builder {
            this.logTag = logTag
            return this
        }

        fun setBaseUrl(baseUrl: String): Builder {
            this.baseUrl = baseUrl
            return this
        }

        fun setParserCls(parserCls: KClass<out IParser>): Builder {
            this.parserCls = parserCls
            return this
        }

        fun build(): ShineOptions {
            return ShineOptions(this)
        }
    }

    override fun toString(): String {
        return "ShineOptions(logEnable=$logEnable, logTag='$logTag', baseUrl=$baseUrl, parserCls=$parserCls)"
    }
}