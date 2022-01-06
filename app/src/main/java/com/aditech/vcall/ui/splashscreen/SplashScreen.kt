package com.aditech.vcall.ui.splashscreen

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.aditech.vcall.R
import com.aditech.vcall.network.CheckConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment() {

    private lateinit var view: Any


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
        view = inflater.inflate(R.layout.fragment_splash_screen, container, false)
        checkConnection()
        return view as View
    }

    private fun checkConnection() {
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
            CoroutineScope(Dispatchers.Main).launch{
                delay(100)
            Navigation.findNavController(view as View)
                .navigate(R.id.action_splashScreen_to_login2)
        }}
    }

}