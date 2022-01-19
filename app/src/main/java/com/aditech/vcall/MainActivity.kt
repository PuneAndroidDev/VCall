package com.aditech.vcall

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.aditech.vcall.ui.login.Login
import com.co_vision.co_vision.Localstorage_Room.SharedPreference.LoginCredentials.loginin
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.internal.ContextUtils.getActivity

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var floatingAction: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //floatingAction=findViewById(R.id.closeApp)
        navController = findNavController(R.id.activity_main_fragment)

        if (this.loginin()) {
            startActivity(Intent(this, MainDashBoardActivity::class.java))
        }
        /*floatingAction.setOnClickListener {
            finishAffinity()
        }*/

    }

    override fun onBackPressed() {

        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Caution")
        builder.setMessage("If You Go back Process might get Terminated")
        builder.setCancelable(false)
        builder.setPositiveButton(
            "Yes"
        ) { _, _ ->
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            navController.navigate(R.id.login2)
        }
        builder.setNegativeButton("No"
        ) { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }
}