package com.aditech.vcall

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.co_vision.co_vision.Localstorage_Room.SharedPreference.LoginCredentials.loginin

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.activity_main_fragment)

        if (this.loginin()) {
            startActivity(Intent(this, MainDashBoardActivity::class.java))
        }

    }
}