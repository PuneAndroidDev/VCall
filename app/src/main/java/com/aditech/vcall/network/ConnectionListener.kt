package com.aditech.vcall.network
import android.content.Context

interface ConnectionListener {
    fun connectionOffline(context: Context)
    fun connectionOnline(context: Context)
}