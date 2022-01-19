package com.aditech.vcall.ui.stars

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.aditech.vcall.R

private const val TAG = "Stars"

 class Stars : Fragment() {

     @SuppressLint("SetJavaScriptEnabled")
     override fun onCreateView(
         inflater: LayoutInflater, container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View {
         // Inflate the layout for this fragment
         val view = inflater.inflate(com.aditech.vcall.R.layout.fragment_stars, container, false)

         val channel=view.findViewById<TextView>(R.id.name)
         val call=view.findViewById<Button>(R.id.startcall)

         call.setOnClickListener {
             val intent=Intent(requireActivity(),ChatActivity::class.java)
             intent.putExtra("name",channel.text.toString())
             startActivity(intent)
         }
         return view as View
     }
 }