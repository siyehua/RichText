package com.tencent.testrichtext

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.ViewGroup
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import java.net.URLDecoder

/**
 * 通用的 h5 页面
 */
class CommonH5Activity : AppCompatActivity() {
    private lateinit var wvContent: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.h5_activity_webview)
        wvContent = findViewById(R.id.wvContent)
        // 生命周期
//        wvContent.onPause() // 通过 onPause 动作通知内核暂停所有的动作，如 DOM 的解析、plugin 的执行、JavaScript 执行等
//        wvContent.onResume() // 恢复 WebView，能正常执行网页的响应
//        ((ViewGroup) mWebView.getParent()).removeView(mWebView);
//        wvContent.destroy() // 当 Activity 要 destroy 时，应先将 WebView 移除，再 destroy 掉


        // 缓存相关
//        wvContent.clearCache(true) // 清除缓存
//        wvContent.clearHistory() // 清除历史
//        wvContent.clearFormData() // 清除表单数据
//        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE;// 设置缓存模式
// 缓存模式
//        LOAD_DEFAULT: 默认，根据 cache-control 决定是否从网络上取数据
//        LOAD_NORMAL: API level 17 中已经废弃, 从API level 11开始作用同 LOAD_DEFAULT 模式
//        LOAD_CACHE_ELSE_NETWORK: 只要本地有，无论是否过期，或者 no-cache，都使用缓存中的数据
//        LOAD_NO_CACHE: 不使用缓存，只从网络获取数据
//        LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        setting()
        webViewClient()
        webChromeClient()
//        val ua: String = wvContent.getSettings().getUserAgentString()
        wvContent.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 5.0; SM-N9100 Build/LRX21V) > AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 > Chrome/37.0.0.0 Mobile Safari/537.36 > MicroMessenger/6.0.2.56_r958800.520 NetType/WIFI")
        //开始加载数据
        val url = intent.getStringExtra("url") ?: return
        wvContent.loadUrl(url)
    }


    override fun onPause() {
        super.onPause()
        wvContent.onPause() // 通过 onPause 动作通知内核暂停所有的动作，如 DOM 的解析、plugin 的执行、JavaScript 执行等
    }

    override fun onDestroy() {
        super.onDestroy()
        //做这些处理是为了防止内存泄露
        wvContent.loadUrl("about:blank")
        (wvContent.parent as? ViewGroup)?.removeView(wvContent)
        wvContent.destroy() // 当 Activity 要 destroy 时，应先将 WebView 移除，再 destroy 掉
    }

    override fun onBackPressed() {
        if (wvContent.canGoBack()) {
            //能返回先返回
            wvContent.goBack()
            return
        } else {
            super.onBackPressed()
        }
    }

    private fun webChromeClient() {
        wvContent.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                if (TextUtils.isEmpty(title)) {
                }
            }
        }
//        onProgressChanged()
//
//        获得网页的加载进度并显示。
//
//        onReceivedTitle()
//
//        获取 Web 页中的标题。
//
//        onJsAlert()
//
//        支持 javascript 的警告框。
//
//        onJsConfirm()
//
//        支持 javascript 的确认框。
//
//        onJsPrompt()
//
//        支持 javascript 输入框
    }

    private fun webViewClient() {
        wvContent.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                request?.let {
                    wvContent.loadUrl(it.url.toString())
                }
                return false
            }


            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let {
                    wvContent.loadUrl(it)
                }
                return false
            }
        }
//        shouldOverrideUrlLoading()
//
//        在网页上的所有加载都经过这个方法，这个函数我们可以做很多操作。
//
//        onPageStarted()
//
//        开始载入页面调用的，我们可以设定一个 loading 的页面，告诉用户程序在等待网络响应。
//
//        onPageFinished()
//
//        在页面加载结束时调用。我们可以关闭 loading 条，切换程序动作。
//
//        onLoadResource()
//
//        在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
//
//        onReceivedError()
//
//        加载页面出现错误时调用。
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setting() {
        // js 相关 , WebView 配置
        val webSettings = wvContent.settings
        webSettings.javaScriptEnabled =
            true // 支持 js。如果碰到后台无法释放 js 导致耗电，应在 onStop 和 onResume 里分别设成 false 和 true
        //js接口, 通用的 h5 页面不需要
//        mWebView.addJavascriptInterface(new WebAppInterface (this), "android"); // js 接口

        // 设置自适应屏幕，两者合用
        webSettings.useWideViewPort = true // 将图片调整到适合 WebView 的大小
        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小
        webSettings.setSupportZoom(true) // 支持缩放，默认为 true
        webSettings.builtInZoomControls = true // 设置内置的缩放控件，若为 false，则该 WebView 不可缩放
        webSettings.displayZoomControls = false // 隐藏原生的缩放控件

        //其他设置
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW//Https 和 Http 混合模式
    }

}