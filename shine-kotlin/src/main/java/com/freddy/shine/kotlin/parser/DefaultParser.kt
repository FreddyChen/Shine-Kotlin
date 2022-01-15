package com.freddy.shine.kotlin.parser

import com.freddy.shine.kotlin.exception.RequestException
import com.freddy.shine.kotlin.model.DefaultResponseModel
import com.freddy.shine.kotlin.utils.ShineLog
import java.lang.reflect.Type

/**
 * 默认数据解析器
 *
 * ResponseModel包含
 * * code
 * * msg
 * * data
 *
 * @see [DefaultResponseModel]
 * @author: FreddyChen
 * @date  : 2022/01/06 17:52
 * @email : freddychencsc@gmail.com
 */
internal class DefaultParser : AbstractParser() {

    override fun <T> parse(url: String, data: String, type: Type): T {
        ShineLog.i(log = "${javaClass.simpleName}#parse() data = $data, type = $type")
        var errMsg: String?
        var responseModel: DefaultResponseModel<T>? = null
        try {
            responseModel = gson.fromJson<DefaultResponseModel<T>>(
                data,
                DefaultResponseModel::class.java
            )
            if (!responseModel.isSuccessful()) {
                errMsg = "responseModel is failure"
            } else {
                return gson.fromJson(gson.toJson(responseModel.data), type)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            errMsg = e.message
        }

        throw RequestException(
            type = RequestException.Type.NATIVE,
            url = url,
            errCode = responseModel?.code ?: -1,
            errMsg = "${javaClass.simpleName}#parse() failure\nerrMsg = $errMsg\ntype = $type\nresponseModel = $responseModel\ndata = $data"
        )
    }
}