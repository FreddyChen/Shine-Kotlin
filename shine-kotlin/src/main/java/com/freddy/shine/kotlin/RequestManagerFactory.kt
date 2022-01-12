package com.freddy.shine.kotlin

import com.freddy.shine.kotlin.interf.IRequest
import com.freddy.shine.kotlin.retrofit.manager.RetrofitRequestManager

/**
 * RequestManager工厂，提供获取RequestManager方法，应用层直接调用[getRequestManager]即可，无需关心内部实现逻辑
 * @author: FreddyChen
 * @date  : 2022/01/07 14:08
 * @email : freddychencsc@gmail.com
 */
internal object RequestManagerFactory {

    fun getRequestManager(): IRequest {
        return RetrofitRequestManager.INSTANCE
    }
}