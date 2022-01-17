package com.freddy.shine.kotlin.example

import com.freddy.shine.kotlin.config.RequestMethod

/**
 *
 * @author: FreddyChen
 * @date  : 2022/01/08 23:09
 * @email : freddychencsc@gmail.com
 */
class TestRepository : BaseRepository() {

    /**
     * 获取历史列表数据
     * 异步请求
     */
    suspend fun fetchHistoryList(): ArrayList<History> {
        return request(
            requestMethod = RequestMethod.POST,
            function = "lishi/api.php",
        )
    }

    /**
     * 获取新闻列表数据
     * 同步请求
     */
    fun fetchJournalismList(): ArrayList<Journalism> {
        return requestSync(
            requestMethod = RequestMethod.GET,
            baseUrl = "https://is.snssdk.com/",
            function = "api/news/feed/v51/",
            parserCls = CustomParser2::class,
        )
    }
}