package com.example.maketeam_app

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.maketeam_app.databinding.ActivityMainBinding
import com.kakao.sdk.common.util.Utility

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.itemIconTintList = null
        navControllerSetting()


        //val hash = Utility.getKeyHash(this)
        //Log.d("Hash", hash)
    }

    private fun navControllerSetting(){
        val navHostFragment = supportFragmentManager.
        findFragmentById(R.id.container_fragment) as NavHostFragment

        val navController = navHostFragment.navController

        val bottomNavigationView = binding.bottomNav
        bottomNavigationView.setupWithNavController(navController)
    }

    fun noShowNavigation(){
        binding.bottomNav.visibility = View.GONE
    }

    fun noShowTabLayout(){
        binding.tabLayoutMain.visibility = View.GONE
    }
}