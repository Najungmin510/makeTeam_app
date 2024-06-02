package com.example.maketeam_app.view.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentMembershipSuccessBinding

class fragment_membership_success : BaseFragment<FragmentMembershipSuccessBinding>
    (R.layout.fragment_membership_success){
    override fun initView() {
        (requireActivity() as MainActivity).noShowNavigation()
        (requireActivity() as MainActivity).noShowTabLayout()
    }

    override fun initClick() {
        binding.btnSetSuccessGohome.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_membership_success_to_home_fragment_school_main)
        }
    }

}