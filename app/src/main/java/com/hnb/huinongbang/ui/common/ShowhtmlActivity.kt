package com.hnb.huinongbang.ui.common

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.hnb.huinongbang.R
import com.hnb.huinongbang.util.LogUtil
import kotlinx.android.synthetic.main.activity_showhtml.*


class ShowhtmlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showhtml)

        val id = intent.getIntExtra("id", 1)
        val m = intent.getStringExtra("m") ?: "plant"
        LogUtil.d("id+m", "${id}${m}")
        val url = "https://www.huinongbang.club/upload/${m}/file/${id}.html"
        val webView = webView as WebView
        webView.loadUrl(url)
    }
}