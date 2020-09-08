package com.mgc.webviewjshelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mgc.webviewjshelper.helper.WebViewHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WebViewHelper.initWb(webView)
        webView.loadUrl("https://www.jianshu.com/p/fa8b334e2936")
    }
}
