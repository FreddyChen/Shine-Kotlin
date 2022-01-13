package com.freddy.shine.kotlin.config

import com.freddy.shine.kotlin.cipher.DefaultCipher
import com.freddy.shine.kotlin.cipher.ICipher
import com.freddy.shine.kotlin.parser.DefaultParser
import com.freddy.shine.kotlin.parser.IParser
import kotlin.reflect.KClass

/**
 *
 * @author: FreddyChen
 * @date  : 2022/01/11 10:05
 * @email : freddychencsc@gmail.com
 */
class ShineOptions(builder: Builder) {

    var logEnable: Boolean
    var logTag: String
    var defaultBaseUrl: String?
    var defaultParserCls: KClass<out IParser>

    init {
        this.logEnable = builder.logEnable
        this.logTag = builder.logTag
        this.defaultBaseUrl = builder.defaultBaseUrl
        this.defaultParserCls = builder.defaultParserCls
    }

    class Builder {
        internal var logEnable: Boolean = ShineConfig.LOG_ENABLE
        internal var logTag: String = ShineConfig.LOG_TAG
        internal var defaultBaseUrl: String? = null
        internal var defaultParserCls: KClass<out IParser> = DefaultParser::class

        fun setLogEnable(logEnable: Boolean): Builder {
            this.logEnable = logEnable
            return this
        }

        fun setLogTag(logTag: String): Builder {
            this.logTag = logTag
            return this
        }

        fun setDefaultBaseUrl(defaultBaseUrl: String): Builder {
            this.defaultBaseUrl = defaultBaseUrl
            return this
        }

        fun setDefaultParserCls(defaultParserCls: KClass<out IParser>): Builder {
            this.defaultParserCls = defaultParserCls
            return this
        }

        fun build(): ShineOptions {
            return ShineOptions(this)
        }
    }

    override fun toString(): String {
        return "ShineOptions(logEnable=$logEnable, logTag='$logTag', defaultBaseUrl=$defaultBaseUrl, defaultParserCls=$defaultParserCls)"
    }
}