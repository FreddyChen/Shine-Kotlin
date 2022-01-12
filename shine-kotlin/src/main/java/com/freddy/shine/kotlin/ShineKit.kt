package com.freddy.shine.kotlin

import com.freddy.shine.kotlin.config.ShineOptions
import com.freddy.shine.kotlin.interf.IRequest

/**
 * Shine核心类
 *
 * 包含：
 * * init(context: Context) 初始化配置
 * * getRequestManager(): IRequest 获取请求管理器
 * @author: FreddyChen
 * @date  : 2022/01/07 13:56
 * @email : freddychencsc@gmail.com
 */
object ShineKit {

    var options: ShineOptions = ShineOptions.Builder().build()

    fun init(options: ShineOptions) {
        this.options = options
    }

    fun getRequestManager(): IRequest {
        return RequestManagerFactory.getRequestManager()
    }
}