package com.example.maketeam_app.view.home

import androidx.navigation.fragment.findNavController
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentHomeMainBinding

class home_fragment_school_main : BaseFragment<FragmentHomeMainBinding>(R.layout.fragment_home_main) {
    override fun initView() {
        (requireActivity() as MainActivity).showNavigation()
        (requireActivity() as MainActivity).showTabLayout()
    }

    override fun initClick() {
        binding.btnWriteNewBoardSchool.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_school_main_to_fragment_write_new_board)
        }
    }

}