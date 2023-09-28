package com.example.taskk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class WebActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_WEB_PAGE_URL = "extra_web_page_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val webPageUrl = intent.getStringExtra(EXTRA_WEB_PAGE_URL)

        val webView = findViewById<WebView>(R.id.webView)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        if (!webPageUrl.isNullOrEmpty()) {
            webView.loadUrl(webPageUrl)
        }
    }
}
