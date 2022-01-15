package com.freddy.shine.kotlin.example

import com.freddy.shine.kotlin.cipher.DefaultCipher
import com.freddy.shine.kotlin.config.RequestMethod

/**
 *
 * @author: FreddyChen
 * @date  : 2022/01/08 23:09
 * @email : freddychencsc@gmail.com
 */
class TestRepository : BaseRepository() {

    /**
     * 获取文章列表数据
     * 异步请求
     */
    suspend fun fetchArticleList(): ArticleList {
        return request(
            requestMethod = RequestMethod.POST,
            function = "article/list/0/json",
        )
    }

    /**
     * 获取Cat数据
     * 同步请求
     */
    fun fetchCatList(): ArrayList<Cat> {
        return requestSync(
            requestMethod = RequestMethod.GET,
            baseUrl = "https://cat-fact.herokuapp.com/",
            function = "facts/random?amount=2&animal_type=cat",
            parserCls = CustomParser2::class,
            cipherCls = DefaultCipher::class
        )
    }
}