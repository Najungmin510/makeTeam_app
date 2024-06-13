package com.example.maketeam_app.view.mypage

import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.access.ViewModel
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.base.BaseProgressDialog
import com.example.maketeam_app.databinding.FragmentCheckApplyBoardBinding
import com.example.maketeam_app.openai.GPTURL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class fragment_check_apply_board : BaseFragment<FragmentCheckApplyBoardBinding>(R.layout.fragment_check_apply_board) {

    private val vm : ViewModel by activityViewModels()
    private var ischeck = false
    private lateinit var progressBar: ProgressBar
    override fun initView() {
        (requireActivity() as MainActivity).noShowNavigation()
        (requireActivity() as MainActivity).noShowTabLayout()
        (requireActivity() as MainActivity).noShowToolbar()

        settingFakeData()
        settingBar()
    }

    override fun initClick() {
        binding.groupApplyButton.btnShowApply.setOnClickListener() {
            findNavController().navigate(R.id.action_applyto_fragment_to_fragment_show_apply_user)
        }
    }

    private fun settingFakeData(){
        binding.contentExample.teamLookTitleDetail.text = "김성우 교수님의 HCI 팀원 찾습니다."
        binding.contentExample.boardDetailContent.text = "같이 팀플하실분들 구합니다. 현재 인원 저포함 2명이구요. 끝까지 열심히 하실 분 모집합니다. 툴 다룰 줄 아셨으면 좋겠어요. 모르셔도 상관 없긴 합니다."
    }

    private fun settingBar(){
        binding.headerApplyToGroup.btnBackX.setBackgroundResource(R.drawable.btn_back_left)
        binding.headerApplyToGroup.textHeaderTitle.text = "한림대학교"
        binding.headerApplyToGroup.btnWriteDetailSetting.visibility = View.INVISIBLE
    }

}