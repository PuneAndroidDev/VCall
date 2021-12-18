package com.aditech.vcall

import android.webkit.JavascriptInterface
import com.aditech.vcall.ui.mainDashboard.DashBoard

class JavascriptInterface(val dashBoard: DashBoard) {

    @JavascriptInterface
    public fun onPeerConnected() {
    }
}