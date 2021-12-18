package com.aditech.vcall.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object CheckConnection {

    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        activeNetwork.let {
            return it != null
        }
    }
}