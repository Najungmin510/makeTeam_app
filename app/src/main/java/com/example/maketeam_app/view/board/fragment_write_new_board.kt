package com.example.maketeam_app.view.board

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentWriteNewBoardBinding

class fragment_write_new_board : BaseFragment<FragmentWriteNewBoardBinding>(R.layout.fragment_write_new_board) {
    override fun initView() {
        (requireActivity() as MainActivity).noShowNavigation()
        (requireActivity() as MainActivity).noShowTabLayout()
    }

    override fun initClick() {

    }

}