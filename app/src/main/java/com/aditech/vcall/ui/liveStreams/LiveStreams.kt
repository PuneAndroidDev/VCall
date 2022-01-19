package com.aditech.vcall.ui.liveStreams

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aditech.vcall.R
import com.aditech.vcall.addpter.videoAddpter.LiveStreamList
import com.aditech.vcall.addpter.videoAddpter.VideoAdapter
import com.aditech.vcall.network.networkModal.LiveVideoModal
import com.aditech.vcall.network.networkModal.VideoStreamModal

class LiveStreams : Fragment() {

    private lateinit var recyclerview: RecyclerView
    private var data = ArrayList<LiveVideoModal>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view:View=inflater.inflate(R.layout.fragment_live_streams, container, false)
        recyclerview = view.findViewById(R.id.livelistrecycle)
        data.add(LiveVideoModal("Parth"))
        data.add(LiveVideoModal("Kevin"))




        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        val adapter = LiveStreamList(data, requireContext())
        recyclerview.adapter = adapter
        return view

    }

}