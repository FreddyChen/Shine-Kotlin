package com.freddy.shine.kotlin.parser

import com.google.gson.Gson
import java.lang.reflect.Type
import kotlin.reflect.KClass

/**
 * 抽象的数据解析器
 *
 * 如果需要自定义解析器，继承此[AbstractParser]，实现[parse]方法即可
 * @see [DefaultParser]
 * @author: FreddyChen
 * @date  : 2022/01/06 17:50
 * @email : freddychencsc@gmail.com
 */
abstract class AbstractParser : IParser {

    protected val gson: Gson by lazy { Gson() }
}