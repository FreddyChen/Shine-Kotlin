package com.freddy.shine.kotlin.utils

import android.util.Log
import com.freddy.shine.kotlin.ShineKit
import com.freddy.shine.kotlin.config.ShineConfig

/**
 * Shine日志工具类
 *
 * @author: FreddyChen
 * @date  : 2022/01/07 13:54
 * @email : freddychencsc@gmail.com
 */
object ShineLog {

    fun v(tag: String = ShineKit.options.logTag, log: Any) {
        if (!ShineKit.options.logEnable) return
        Log.v(tag, log.toString())
    }

    fun d(tag: String = ShineKit.options.logTag, log: Any) {
        if (!ShineKit.options.logEnable) return
        Log.d(tag, log.toString())
    }

    fun i(tag: String = ShineKit.options.logTag, log: Any) {
        if (!ShineKit.options.logEnable) return
        Log.i(tag, log.toString())
    }

    fun w(tag: String = ShineKit.options.logTag, log: Any) {
        if (!ShineKit.options.logEnable) return
        Log.w(tag, log.toString())
    }

    fun e(tag: String = ShineKit.options.logTag, log: Any) {
        if (!ShineKit.options.logEnable) return
        Log.e(tag, log.toString())
    }
}