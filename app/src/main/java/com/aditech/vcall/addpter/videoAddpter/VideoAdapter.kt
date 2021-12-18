package com.aditech.vcall.addpter.videoAddpter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aditech.vcall.R
import com.aditech.vcall.network.networkModal.VideoStreamModal
import com.aditech.vcall.network.Constraints
import com.aditech.vcall.util.displayImage

class VideoAdapter(private var videoStreams: ArrayList<VideoStreamModal>, private var context: Context) :
    RecyclerView.Adapter<VideoAdapter.VideoHolder>() {

    var clickListener: ClickListener? = null

    class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var clickListener: ClickListener? = null
        fun bindItems(v: VideoStreamModal, context: Context, clickListener: ClickListener) {

            val videoImageView: ImageView = itemView.findViewById(R.id.videoimage)
            val videoTitleTextView: TextView = itemView.findViewById(R.id.videoTitle)
            val videoDescription: TextView = itemView.findViewById(R.id.videoDescription)

            videoDescription.text=v.description
            videoTitleTextView.text=v.fileName
            context.displayImage(Constraints.VIDEO_IMAGE_URL + v.image, videoImageView)

            this.clickListener=clickListener
            itemView.setOnClickListener(this)

        }
        override fun onClick(v: View?) {
            clickListener?.onItemClick(adapterPosition, v)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoAdapter.VideoHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.inflate_videostream, parent, false)
        return VideoHolder(view)
    }

    override fun onBindViewHolder(holder: VideoAdapter.VideoHolder, position: Int) {
        clickListener?.let { holder.bindItems(videoStreams[position], context, it) }
    }

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun onItemClick(position: Int, v: View?)
    }


    override fun getItemCount(): Int {
        return videoStreams.size
    }

}