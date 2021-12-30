package com.aditech.vcall.ui.videoStreams

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditech.vcall.network.networkModal.VideoStreamModal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoStreamViewModal:ViewModel() {

    private var videoStreamRepository:VideoStreamRepository
    var videoStreamsArrayList: LiveData<ArrayList<VideoStreamModal>>

    init {
        videoStreamRepository = VideoStreamRepository()
        videoStreamsArrayList = videoStreamRepository.videoStreamsArrayList
    }

    fun videoStreamList() {
        viewModelScope.launch(Dispatchers.IO) {
            videoStreamRepository.videoStreamList()
        }
    }

}