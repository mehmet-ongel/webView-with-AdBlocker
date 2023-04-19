package com.techmania.webviewwithadblocker

import android.os.Bundle
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.monstertechno.adblocker.AdBlockerWebView
import com.monstertechno.adblocker.AdBlockerWebView.init
import com.monstertechno.adblocker.util.AdBlocker


class MainActivity : AppCompatActivity() {

    lateinit var webView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)

        init(this).initializeWebView(webView)
        webView.webViewClient = BrowserHome()

        webView.loadUrl("https://www.android.com/")

    }
}

private class BrowserHome : WebViewClient() {

    override fun shouldInterceptRequest(view: WebView, url: String): WebResourceResponse? {
        return if (AdBlockerWebView.blockAds(
                view,
                url
            )
        ) AdBlocker.createEmptyResource() else super.shouldInterceptRequest(view, url)
    }
}