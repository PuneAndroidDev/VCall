package com.aditech.vcall.network.APIs

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.lang.NullPointerException


import android.net.ConnectivityManager
import com.aditech.vcall.network.ConnectionListener

class NetworkChangeReceiver(connectionListener: ConnectionListener): BroadcastReceiver() {

    private  var connectionListener: ConnectionListener

    init{
        this.connectionListener=connectionListener
    }

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            if (context?.let { isOnline(it) }!!) {

                connectionListener.connectionOnline(context)

            } else {

                connectionListener.connectionOffline(context)

            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }
    private fun isOnline(context: Context): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            //should check null because in airplane mode it will be null
            netInfo != null && netInfo.isConnected
        } catch (e: NullPointerException) {
            e.printStackTrace()
            false
        }
    }
}