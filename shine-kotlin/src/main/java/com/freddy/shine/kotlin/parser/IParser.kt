package com.freddy.shine.kotlin.parser

import java.lang.reflect.Type

/**
 *
 * @author: FreddyChen
 * @date  : 2022/01/06 17:53
 * @email : freddychencsc@gmail.com
 */
interface IParser {
    fun<T> parse(data: String, type: Type): T
}