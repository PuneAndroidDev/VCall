package com.aditech.vcall.ui.stars

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.VideoView
import com.aditech.vcall.R
import com.aditech.vcall.network.Constraints
import com.aditech.vcall.network.Constraints.Companion.BASE_URL


class Stars : Fragment() {

    private var web: String =BASE_URL+"horizontalvideo?key=Beauty"
    private lateinit var webView: WebView
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view:View=inflater.inflate(R.layout.fragment_stars, container, false)
        webView = view.findViewById(R.id.horizontalVideoPlayWebView)
        webView.loadUrl(web)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                webView.loadUrl(web)
                return true
            }
        }
        return view
    }

}