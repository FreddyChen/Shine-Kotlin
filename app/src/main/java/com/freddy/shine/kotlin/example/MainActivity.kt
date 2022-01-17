package com.freddy.shine.kotlin.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.freddy.shine.kotlin.exception.RequestException
import com.freddy.shine.kotlin.ShineKit
import com.freddy.shine.kotlin.config.ShineOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var btn1: View
    private lateinit var btn2: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val options = ShineOptions.Builder()
            .setLogEnable(true)
            .setLogTag("FreddyChen")
            .setBaseUrl("https://www.wanandroid.com/")
            .setParserCls(CustomParser1::class)
            .build()
        ShineKit.init(options)

        val repository = TestRepository()

        btn1 = findViewById(R.id.btn_1)
        btn2 = findViewById(R.id.btn_2)

        btn1.setOnClickListener {
            thread(start = true) {
                Log.i(TAG, "异步请求开始")
                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val historyList = repository.fetchHistoryList()
                        Log.i(TAG, "historyList = $historyList")
                    } catch (e: RequestException) {
                        Log.e(TAG, "e = $e")
                    }
                }
                Log.i(TAG, "异步请求结束")
            }
        }

        btn2.setOnClickListener {
            thread(start = true) {
                try {
                    Log.i(TAG, "同步请求开始")
                    val journalismList = repository.fetchJournalismList()
                    Log.i(TAG, "journalismList = $journalismList")
                    Log.i(TAG, "同步请求结束")
                } catch (e: RequestException) {
                    Log.e(TAG, "e = $e")
                }
            }
        }
    }
}