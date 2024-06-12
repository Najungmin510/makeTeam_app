package com.example.maketeam_app.view.user

import android.os.Bundle
import android.view.View
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
        settingBar()
    }

    override fun initClick() {
        binding.btnSetSuccessGohome.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_membership_success_to_home_fragment_school_main)
        }
    }

    private fun settingBar(){
        binding.headerSuccess.textHeaderTitle.text = "회원가입"
        binding.headerSuccess.btnWriteDetailSetting.visibility = View.INVISIBLE
        binding.headerSuccess.btnBackX.setBackgroundResource(R.drawable.btn_back_left)
        binding.headerSuccess.btnBackX.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_membership_success_to_fragment_membership_setting_sch_inform)
        }
        (requireActivity() as MainActivity).noShowToolbar()
    }
}