package com.example.maketeam_app.view.mypage

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.findNavController
import com.example.maketeam_app.R
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentShowApplyUserBinding

class fragment_show_apply_user : BaseFragment<FragmentShowApplyUserBinding>(R.layout.fragment_show_apply_user) {
    override fun initView() {
        binding.headerShowApplyUser.textHeaderTitle.text = "지원자보기"
        binding.headerShowApplyUser.btnWriteDetailSetting.visibility = View.INVISIBLE

    }

    override fun initClick() {
        binding.btnShowUserApplyDocument.layoutGoShowApplyUser.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_show_apply_user_to_fragment_apply_user_document)
        }
    }

}