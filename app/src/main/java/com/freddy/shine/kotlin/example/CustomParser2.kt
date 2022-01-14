package com.freddy.shine.kotlin.example

import com.freddy.shine.kotlin.exception.RequestException
import com.freddy.shine.kotlin.parser.AbstractParser
import com.freddy.shine.kotlin.utils.ShineLog
import java.lang.reflect.Type

/**
 * 自定义的数据解析器，用于解析 [CustomResponseModel2] 格式数据
 * @author: FreddyChen
 * @date  : 2022/01/07 14:16
 * @email : freddychencsc@gmail.com
 */
class CustomParser2 : AbstractParser() {

    override fun <T> parse(data: String, type: Type): T {
        ShineLog.i(log = "${javaClass.simpleName}#parse() data = $data, type = $type")
        var errMsg: String?
        var responseModel: CustomResponseModel2<T>? = null
        try {
//            responseModel = gson.fromJson<CustomResponseModel2<T>>(
//                data,
//                CustomResponseModel2::class.java
//            )
//            if (!responseModel.isSuccessful()) {
//                errMsg = "responseModel is failure"
//            } else {
                return gson.fromJson(data, type)
//            }
        } catch (e: Exception) {
            errMsg = e.message
        }

        throw RequestException(
            type = RequestException.Type.NETWORK,
            errCode = -1,
            errMsg = "${javaClass.simpleName}#parse() failure\nerrMsg = $errMsg\ntype = $type\nresponseModel = $responseModel\ndata = $data"
        )
    }
}