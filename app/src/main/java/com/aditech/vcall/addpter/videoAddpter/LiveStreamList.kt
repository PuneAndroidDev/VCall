package com.aditech.vcall.addpter.videoAddpter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aditech.vcall.R
import com.aditech.vcall.network.networkModal.LiveVideoModal

class LiveStreamList(private var liveVideoStreams: ArrayList<LiveVideoModal>, private var context: Context) :
    RecyclerView.Adapter<LiveStreamList.LiveVideoHolder>() {
    var clickListener: ClickListener? = null

    class LiveVideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var clickListener: ClickListener? = null
        fun bindItems(v: LiveVideoModal, context: Context, clickListener: ClickListener) {

            val videoImageView: ImageView = itemView.findViewById(R.id.videoimage)


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