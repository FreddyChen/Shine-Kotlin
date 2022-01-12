package com.freddy.shine.kotlin.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.freddy.shine.kotlin.exception.RequestException
import com.freddy.shine.kotlin.ShineKit
import com.freddy.shine.kotlin.config.ShineOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var tv_test: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val options = ShineOptions.Builder()
            .setLogEnable(true)
            .setLogTag("FreddyChen")
            .setDefaultBaseUrl("https://www.wanandroid.com/")
            .setDefaultParserCls(CustomParser1::class)
            .build()
        ShineKit.init(options)

        tv_test = findViewById(R.id.tv_test)

        val repository = TestRepository()
        tv_test.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val articleList = repository.fetchArticleList()
                    Log.i("MainActivity", "articleList = $articleList")
                } catch (e: RequestException) {
                    e.printStackTrace()
                }
                try {
                    val jokeList = repository.fetchJokeList()
                    Log.i("MainActivity", "jokeList = $jokeList")
                } catch (e: RequestException) {
                    e.printStackTrace()
                }
            }
        }
    }
}