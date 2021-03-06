package com.mgc.webviewjshelper.helper

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import com.mgc.webviewjshelper.base.Constant


object WebViewHelper {

    var jsInterfaceName = "java_obj"

    var getHtmlContentStr ="javascript:window.$jsInterfaceName.showSource('<head>'+" + "document.getElementsByTagName('html')[0].innerHTML+'</head>');"

    @SuppressLint("JavascriptInterface", "SetJavaScriptEnabled")
    fun initWb(mWebView: WebView) {

        mWebView.getSettings().setJavaScriptEnabled(true)

        mWebView.addJavascriptInterface(JsInterface(), jsInterfaceName)


        // 设置WebView是否支持使用屏幕控件或手势进行缩放，默认是true，支持缩放
        mWebView.getSettings().setSupportZoom(true)

        // 设置WebView是否使用其内置的变焦机制，该机制集合屏幕缩放控件使用，默认是false，不使用内置变焦机制。
        mWebView.getSettings().setBuiltInZoomControls(true)

        // 设置是否开启DOM存储API权限，默认false，未开启，设置为true，WebView能够使用DOM storage API
        mWebView.getSettings().setDomStorageEnabled(true)

        // 触摸焦点起作用.如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件。
        mWebView.requestFocus()

        // 设置此属性,可任意比例缩放,设置webview推荐使用的窗口
        mWebView.getSettings().setUseWideViewPort(true)

        // 设置webview加载的页面的模式,缩放至屏幕的大小
        mWebView.getSettings().setLoadWithOverviewMode(true)
        mWebView.getSettings().setUserAgentString(Constant.UserAgentString)



        mWebView.setWebViewClient(object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                // 在开始加载网页时会回调
                super.onPageStarted(view, url, favicon)
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                // 拦截 url 跳转,在里边添加点击链接跳转或者操作
                view?.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                // 在结束加载网页时会回调
                // 获取页面内容
                view?.loadUrl(getHtmlContentStr)

                super.onPageFinished(view, url)
            }

            override fun onReceivedError(
                view: WebView, errorCode: Int,
                description: String, failingUrl: String
            ) {
                // 加载错误的时候会回调，在其中可做错误处理，比如再请求加载一次，或者提示404的错误页面
                super.onReceivedError(view, errorCode, description, failingUrl)
            }

            override fun shouldInterceptRequest(
                view: WebView,
                request: WebResourceRequest
            ): WebResourceResponse? {
                // 在每一次请求资源时，都会通过这个函数来回调
                return super.shouldInterceptRequest(view, request)
            }
        })
    }
}