package com.aditech.vcall.ui.splashscreen

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.aditech.vcall.MainDashBoardActivity
import com.aditech.vcall.R
import com.aditech.vcall.network.CheckConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val view: View= inflater.inflate(R.layout.fragment_splash_screen, container, false)
        checkConnection(view)
        return view as View
    }

    private fun checkConnection(view: View) {
        if (!CheckConnection.isConnected(requireContext())) {

            val builder = AlertDialog.Builder(context)
            builder.setMessage("No Connection")
            builder.setCancelable(false)
            builder.setPositiveButton("ok") { dialog, _ ->
                if (!CheckConnection.isConnected(requireContext())) {
                    context.let { ActivityCompat.finishAffinity(context as Activity) }
                } else {

                    dialog.dismiss()
                }
            }
            val alertDialog = builder.create()
            alertDialog.show()

        } else {


        }
    }

}