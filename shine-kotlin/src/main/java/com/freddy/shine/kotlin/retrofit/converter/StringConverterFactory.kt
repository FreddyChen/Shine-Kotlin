package com.freddy.shine.kotlin.retrofit.converter

import com.freddy.shine.kotlin.config.NetworkConfig
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.ByteArrayOutputStream
import java.io.Closeable
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type

/**
 * 自定义StringConverterFactory
 * @author: FreddyChen
 * @date  : 2022/01/07 16:15
 * @email : freddychencsc@gmail.com
 */
class StringConverterFactory : Converter.Factory() {

    companion object {
        private val MEDIA_TYPE: MediaType? = NetworkConfig.DEFAULT_CONTENT_TYPE.toMediaTypeOrNull()
        private const val UTF_8 = "UTF-8"
        private const val BUFFER_SIZE = 4096

        fun create(): StringConverterFactory {
            return StringConverterFactory()
        }
    }

    override fun responseBodyConverter(
        type: Type, annotations: Array<Annotation?>?,
        retrofit: Retrofit?
    ): Converter<ResponseBody, *>? {
        return if (String::class.java == type) {
            Converter { value -> getStringFrom(value) }
        } else null
    }

    override fun requestBodyConverter(
        type: Type, parameterAnnotations: Array<Annotation?>?,
        methodAnnotations: Array<Annotation?>?, retrofit: Retrofit?
    ): Converter<*, RequestBody?>? {
        return if (String::class.java == type) {
            Converter<String?, RequestBody?> { value -> value.toRequestBody(MEDIA_TYPE) }
        } else null
    }

    @Throws(IOException::class)
    private fun getStringFrom(value: ResponseBody): String? {
        val inputStream = value.byteStream()
        return getStringFrom(inputStream)
    }

    @Throws(IOException::class)
    private fun getStringFrom(inputStream: InputStream?): String? {
        var result: String? = null
        try {
            if (inputStream != null) {
                result = writeStreamToString(inputStream)
            }
        } finally {
            closeQuietly(inputStream)
        }
        return result
    }

    @Throws(IOException::class)
    private fun writeStreamToString(inputStream: InputStream): String? {
        val result: String?
        val outputStream = ByteArrayOutputStream()
        result = try {
            readStreamAndConvert(inputStream, outputStream)
        } finally {
            closeQuietly(outputStream)
        }
        return result
    }

    @Throws(IOException::class)
    private fun readStreamAndConvert(inputStream: InputStream, outputStream: ByteArrayOutputStream): String? {
        val buffer = ByteArray(BUFFER_SIZE)
        var length: Int
        while (inputStream.read(buffer).also { length = it } != -1) {
            outputStream.write(buffer, 0, length)
        }
        return outputStream.toString(UTF_8)
    }

    private fun closeQuietly(stream: Closeable?) {
        try {
            stream?.close()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}