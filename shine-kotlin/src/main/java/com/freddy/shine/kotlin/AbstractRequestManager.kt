package com.freddy.shine.kotlin

import com.freddy.shine.kotlin.config.ShineConfig
import com.freddy.shine.kotlin.exception.RequestException
import com.freddy.shine.kotlin.interf.IRequest
import com.freddy.shine.kotlin.parser.IParser
import com.freddy.shine.kotlin.utils.ShineLog
import com.google.gson.Gson
import java.lang.reflect.Type
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

/**
 * RequestManager抽象类，自定义RequestManager需继承此类
 *
 * @see [RetrofitRequestManager]
 * @author: FreddyChen
 * @date  : 2022/01/07 14:05
 * @email : freddychencsc@gmail.com
 */
abstract class AbstractRequestManager : IRequest {

    protected val gson: Gson by lazy { Gson() }

    private val parserMap: ConcurrentHashMap<KClass<*>, IParser> by lazy {
        ConcurrentHashMap()
    }

    /**
     * 解析数据
     */
    protected fun <T> parse(
        data: String,
        type: Type,
        parserCls: KClass<out IParser>
    ): T {
        return getParser(parserCls).parse(data, type)
    }

    /**
     * 获取Parser
     */
    private fun getParser(parserCls: KClass<out IParser>?): IParser {
        try {
            (parserCls ?: ShineKit.options.defaultParserCls).apply {
                val parser: IParser = parserMap.getOrPut(parserCls) {
                    Class.forName(java.name).newInstance() as IParser
                }
                ShineLog.i(log = "${this@AbstractRequestManager.javaClass.simpleName}#getParser() parser = $parser, parser = $parser")
                return parser
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        throw RequestException(
            type = RequestException.Type.NATIVE,
            errMsg = "${this@AbstractRequestManager.javaClass.simpleName}#parse() parser为空"
        )
    }
}