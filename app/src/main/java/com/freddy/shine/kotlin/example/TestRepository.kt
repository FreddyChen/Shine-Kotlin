package com.freddy.shine.kotlin.example

import android.util.ArrayMap
import com.freddy.shine.kotlin.cipher.DefaultCipher
import com.freddy.shine.kotlin.config.RequestMethod

/**
 *
 * @author: FreddyChen
 * @date  : 2022/01/08 23:09
 * @email : freddychencsc@gmail.com
 */
class TestRepository : BaseRepository() {

    suspend fun fetchArticleList(): ArticleList {
        val headers = ArrayMap<String, Any?>()
        headers["userId"] = 1001
        headers["userName"] = "Fc"

        val params = ArrayMap<String, Any?>()
        params["a"] = 100
        params["b"] = "testFetchArticleList"
        return request(
            requestMethod = RequestMethod.GET,
            function = "article/list/0/json",
            headers = headers,
            params = params,
            cipherCls = DefaultCipher::class
        )
    }

    suspend fun fetchJokeList(): ArrayList<Joke> {
        val params = ArrayMap<String, Any?>()
        params["a"] = 10011
        params["b"] = "testFetchJokeList"
        return request(
            requestMethod = RequestMethod.POST,
            baseUrl = "https://api.apiopen.top/",
            function = "getJoke",
            params = params,
            parserCls = CustomParser2::class,
            cipherCls = DefaultCipher::class
        )
    }
}