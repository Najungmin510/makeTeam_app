package com.example.maketeam_app.view.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.findNavController
import com.example.maketeam_app.R
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentHomeContentMainBinding

class home_fragment_contest_main : BaseFragment<FragmentHomeContentMainBinding>(R.layout.fragment_home_content_main) {
    override fun initView() {

    }

    override fun initClick() {
        binding.btnWriteNewBoardContest.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_school_main_to_fragment_write_new_board)
        }
    }

}