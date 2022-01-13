package com.freddy.shine.kotlin.interf

import com.freddy.shine.kotlin.cipher.ICipher
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

    /**
     * 异步请求
     * @param options   请求参数
     * @param type      数据类型映射
     * @param parserCls 数据解析器
     */
    suspend fun <T> request(options: RequestOptions, type: Type, parserCls: KClass<out IParser>, cipherCls: KClass<out ICipher>? = null): T

    /**
     * 同步请求
     * @param options   请求参数
     * @param type      数据类型映射
     * @param parserCls 数据解析器
     */
    fun <T> requestSync(options: RequestOptions, type: Type, parserCls: KClass<out IParser>, cipherCls: KClass<out ICipher>? = null): T
}