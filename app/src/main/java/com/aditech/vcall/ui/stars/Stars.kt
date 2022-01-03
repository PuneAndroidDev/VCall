package com.aditech.vcall.ui.stars

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.aditech.vcall.R
import com.aditech.vcall.network.Constraints.Companion.CHANNEL
import io.agora.rtc.Constants
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private const val TAG = "Stars"
class Stars : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stars, container, false)
        requireActivity().startActivity(Intent(requireContext(),LiveStreamActivity::class.java))
        return view as View
    }



/*
    private var web: String = TESTBASEURL
    private lateinit var webView: WebView

    companion object {
        private const val CAMERA_REQUEST_CODE = 113
        private const val REQUEST_SELECT_FILE = 13
        private const val INTENT_FILE_TYPE = "image"
        private const val RESULT_OK = 78
    }

    private var imagePathCallback: ValueCallback<Array<Uri>>? = null
    private var cameraImagePath: String? = null

 webView = view.findViewById(R.id.horizontalVideoPlayWebView)
        openFrontFacingCameraGingerbread()
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.settings.setSupportZoom(false)
        webView.settings.allowFileAccess = true
        webView.settings.allowContentAccess = true
        webView.webChromeClient = getCustomWebChromeClient()

        webView.loadUrl(web)
        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState)
        } else {
            webView.settings.javaScriptEnabled = true
        }
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                webView.loadUrl(web)
                return true
            }
        }

    private fun openFrontFacingCameraGingerbread(): Camera? {
        var cameraCount = 0
        var cam: Camera? = null
        val cameraInfo = Camera.CameraInfo()
        cameraCount = Camera.getNumberOfCameras()
        for (camIdx in 0 until cameraCount) {
            Camera.getCameraInfo(camIdx, cameraInfo)
            if (cameraInfo.facing === Camera.CameraInfo.CAMERA_FACING_FRONT) {
                try {
                    cam = Camera.open(camIdx)
                } catch (e: RuntimeException) {
                    Log.e("TAG", "Camera failed to open: " + e.localizedMessage)
                }
            }
        }
        return cam
    }


    private fun getCustomWebChromeClient() = object : WebChromeClient() {

    }*/
}