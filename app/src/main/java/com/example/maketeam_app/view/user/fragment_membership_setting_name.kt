package com.example.maketeam_app.view.user

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentMemberSettingNameBinding

class fragment_membership_setting_name : BaseFragment<FragmentMemberSettingNameBinding>(R.layout.fragment_member_setting_name) {
    override fun initView() {
        (requireActivity() as MainActivity).noShowNavigation()
        (requireActivity() as MainActivity).noShowTabLayout()

    }

    override fun initClick() {

    }

}