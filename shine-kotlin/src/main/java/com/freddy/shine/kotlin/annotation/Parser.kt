package com.freddy.shine.kotlin.annotation

import com.freddy.shine.kotlin.parser.DefaultParser
import com.freddy.shine.kotlin.parser.IParser
import kotlin.reflect.KClass

/**
 *
 * @author: FreddyChen
 * @date  : 2022/01/11 12:03
 * @email : freddychencsc@gmail.com
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Parser(val value: KClass<out IParser> = DefaultParser::class)