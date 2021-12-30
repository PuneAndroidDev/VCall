package com.aditech.vcall.addpter.videoAddpter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.aditech.vcall.R
import com.aditech.vcall.network.networkModal.LiveVideoModal
import com.aditech.vcall.ui.videoStreams.VideoCallActivity

class LiveStreamList(private var liveVideoStreams: ArrayList<LiveVideoModal>, private var context: Context) :
    RecyclerView.Adapter<LiveStreamList.LiveVideoHolder>() {
    var clickListener: ClickListener? = null

    class LiveVideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var clickListener: ClickListener? = null
        fun bindItems(v: LiveVideoModal, context: Context, clickListener: ClickListener) {
            val videoCall1: ImageView = itemView.findViewById(R.id.videoCall1)
            videoCall1.setImageResource(R.drawable.logo)
            this.clickListener=clickListener
            itemView.setOnClickListener(this)

        }
        override fun onClick(v: View?) {
            clickListener?.onItemClick(adapterPosition, v)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveStreamList.LiveVideoHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.inflate_livevideos_list, parent, false)
        return LiveVideoHolder(view)
    }

    override fun onBindViewHolder(holder: LiveStreamList.LiveVideoHolder, position: Int) {
        clickListener?.let { holder.bindItems(liveVideoStreams[position], context, it) }
        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context,VideoCallActivity::class.java))
        }
    }

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun onItemClick(position: Int, v: View?)
    }


    override fun getItemCount(): Int {
        return liveVideoStreams.size
    }

}