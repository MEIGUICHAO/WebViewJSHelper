package com.mgc.webviewjshelper.helper

import android.webkit.JavascriptInterface
import com.mgc.webviewjshelper.utils.LogUtil
import org.jsoup.Jsoup


class JsInterface {


    @JavascriptInterface
    fun showSource(html: String?) {
        getHtmlContent(html!!)
    }

    fun getHtmlContent(html: String) {
        LogUtil.d("getHtmlContent:$html")
        var document = Jsoup.parse(html)
    }

}