package com.freddy.shine.kotlin.example

import android.util.ArrayMap
import com.freddy.shine.kotlin.annotation.Parser
import com.freddy.shine.kotlin.config.RequestMethod

/**
 *
 * @author: FreddyChen
 * @date  : 2022/01/08 23:09
 * @email : freddychencsc@gmail.com
 */
@Parser(CustomParser1::class)
class TestRepository : BaseRepository() {

    @Parser(CustomParser1::class)
    suspend fun fetchArticleList(): ArticleList {
        val headers = ArrayMap<String, Any?>()
        headers["userId"] = 1001
        headers["userName"] = "Fc"
        return request(
            requestMethod = RequestMethod.GET,
            function = "article/list/0/json",
            headers = headers
        )
    }

    @Parser(CustomParser2::class)
    suspend fun fetchJokeList(): ArrayList<Joke> {
        return request(
            requestMethod = RequestMethod.GET,
            baseUrl = "https://api.apiopen.top/",
            function = "getJoke",
            parserCls = CustomParser2::class,
        )
    }
}