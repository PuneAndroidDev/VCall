package com.aditech.vcall.ui.videoStreams

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.aditech.vcall.network.APIs.NetworkCalls
import com.aditech.vcall.network.networkModal.VideoStreamModal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoStreamRepository {

    var videoStreamsArrayList = MutableLiveData<ArrayList<VideoStreamModal>>()

    init {
        videoStreamsArrayList.value
    }

    fun videoStreamList() {
        NetworkCalls.instance()
        val call = NetworkCalls.videoCallAPI.videoList()
        call.enqueue(object : Callback<ArrayList<VideoStreamModal>> {
            override fun onResponse(
                call: Call<ArrayList<VideoStreamModal>>,
                response: Response<ArrayList<VideoStreamModal>>
            ) {
                val data=response.body()
                data?.let {
                    videoStreamsArrayList.value=it
                }
            }

            override fun onFailure(call: Call<ArrayList<VideoStreamModal>>, t: Throwable) {

            }

        })
    }
}