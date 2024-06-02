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
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    navController.navigate(R.id.home_fragment_school_main)
                }
            }
            return@setOnItemSelectedListener true
        }
    }


    /**
     * 탭 레이아웃 & 하단 바텀 네비게이션 안보이게 할 때 쓰는 함수에요
     * (requireActicity() as MainActicity).함수명 입력하심 사용할 수 있어요
     * */

    fun noShowNavigation(){
        binding.bottomNav.visibility = View.GONE
    }

    fun noShowTabLayout(){
        binding.tabLayoutMain.visibility = View.GONE
    }

    fun showNavigation(){
        binding.bottomNav.visibility = View.VISIBLE
    }

    fun showTabLayout(){
        binding.tabLayoutMain.visibility = View.VISIBLE
    }
}