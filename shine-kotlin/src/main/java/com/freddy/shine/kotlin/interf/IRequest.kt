package com.freddy.shine.kotlin.interf

import com.freddy.shine.kotlin.config.RequestOptions
import com.freddy.shine.kotlin.parser.DefaultParser
import com.freddy.shine.kotlin.parser.IParser
import java.lang.reflect.Type
import kotlin.reflect.KClass

/**
 * 抽象的接口请求封装，自定义RequestManager实现此接口即可
 * @author: FreddyChen
 * @date  : 2022/01/07 13:47
 * @email : freddychencsc@gmail.com
 */
interface IRequest {

    suspend fun <T> request(options: RequestOptions, type: Type, parserCls: KClass<out IParser>): T

    fun <T> requestSync(options: RequestOptions, type: Type, parserCls: KClass<out IParser>): T
}