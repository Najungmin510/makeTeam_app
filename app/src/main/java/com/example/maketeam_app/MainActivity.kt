package com.example.maketeam_app

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.maketeam_app.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNav.itemIconTintList = null


        navControllerSetting()
        tabLayoutSetting()


        //val hash = Utility.getKeyHash(this)
        //Log.d("Hash", hash)
    }

    /**바텀 네비게이션 세팅*/
    private fun navControllerSetting(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_fragment) as NavHostFragment
        navController = navHostFragment.navController
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

    /**상단 탭 레이아웃 세팅*/
    private fun tabLayoutSetting(){
        binding.tabLayoutMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position){
                    0 -> navController.navigate(R.id.home_fragment_school_main)
                    1 -> navController.navigate(R.id.home_fragment_contest_main)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

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