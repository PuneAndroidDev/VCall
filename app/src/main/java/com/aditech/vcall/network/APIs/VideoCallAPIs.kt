package com.co_vision.co_vision.Network.APIs

import com.aditech.vcall.network.networkModal.VideoStreamModal
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface VideoCallAPIs {

    @GET("/videolist")
    fun videoList(): Call<ArrayList<VideoStreamModal>>

}