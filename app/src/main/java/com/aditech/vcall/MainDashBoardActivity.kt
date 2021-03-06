package com.aditech.vcall

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import com.aditech.vcall.ui.liveStreams.LiveStreams
import com.aditech.vcall.ui.setting.Setting
import com.aditech.vcall.ui.stars.Stars
import com.aditech.vcall.ui.videoStreams.VideoStreams
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainDashBoardActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_VCall)
        setContentView(R.layout.activity_main_dash_board)
        bottomNavigationView=findViewById(R.id.bottomNavigationView1)
        setCurrentFragment(VideoStreams())
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.tv->setCurrentFragment(VideoStreams())
                R.id.stars->setCurrentFragment(Stars())
                R.id.livestream->setCurrentFragment(LiveStreams())
                R.id.setting->setCurrentFragment(Setting())
            }
            true
        }
    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }


}