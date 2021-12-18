package com.aditech.vcall.ui.videoStreams

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aditech.vcall.R
import com.aditech.vcall.addpter.videoAddpter.VideoAdapter
import com.aditech.vcall.network.Constraints.Companion.SERVICE
import com.aditech.vcall.network.networkModal.VideoStreamModal
import com.aditech.vcall.util.Constraints.Companion.FETCH
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "VideoStreams"

class VideoStreams : Fragment() {

    private lateinit var webView: WebView
    private lateinit var recyclerview: RecyclerView
    private var data = ArrayList<VideoStreamModal>()
    private var key:String="?key="
    private var web: String = SERVICE+key
    private lateinit var viewModal: VideoStreamViewModal


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_video_streams, container, false)
        viewModal = ViewModelProvider(this).get(VideoStreamViewModal::class.java)
        webView = view.findViewById(R.id.videoPlayWebView)
        recyclerview = view.findViewById(R.id.recycle)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        CoroutineScope(Dispatchers.IO).launch {
            while (FETCH) {
                delay(1000)
                viewModal.videoStreamList()
            }
        }

        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState)
        } else {
            webView.settings.javaScriptEnabled = true
        }

        viewModal.videoStreamsArrayList.observe(viewLifecycleOwner, {
            if (it != null) {
                data=it
                FETCH=false
                webView.loadUrl(web+data[0].fileName)
                webView.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        webView.loadUrl(web+data[0].fileName)
                        return true
                    }
                }
                val adapter = VideoAdapter(data, requireContext())
                adapter.setOnItemClickListener(object : VideoAdapter.ClickListener {

                    override fun onItemClick(position: Int, v: View?) {

                        webView.loadUrl(web+data[position].fileName)
                        webView.webViewClient = object : WebViewClient() {
                            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                                webView.loadUrl(web+data[position].fileName)
                                return true
                            }
                        }

                        if (savedInstanceState != null) {
                            webView.restoreState(savedInstanceState)
                        } else {
                            webView.settings.javaScriptEnabled = true
                        }
                    }

                })
                recyclerview.adapter = adapter
            }
        })


        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }


}