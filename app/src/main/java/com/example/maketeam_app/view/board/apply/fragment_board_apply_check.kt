package com.example.maketeam_app.view.board.apply

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.maketeam_app.R
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentBoardApplyCheckBinding

class fragment_board_apply_check : BaseFragment<FragmentBoardApplyCheckBinding>(R.layout.fragment_board_apply_check) {

    private val args : fragment_board_apply_checkArgs by navArgs()
    override fun initView() {
        settingTitle()

    }

    override fun initClick() {
        binding.btnApplyGo.setOnClickListener {
            findNavController().navigate(fragment_board_apply_checkDirections
                .actionFragmentBoardApplyCheckToFragmentBoardApplyToGroup(
                clickId = args.clickId
                ))
        }
    }

    private fun settingTitle(){
        binding.headerApplyCheck.btnWriteDetailSetting.visibility = View.INVISIBLE
        binding.headerApplyCheck.btnBackX.setBackgroundResource(R.drawable.btn_back_left)
        binding.headerApplyCheck.textHeaderTitle.text = "지원하기"
    }

}