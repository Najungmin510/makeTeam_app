package com.example.maketeam_app.view.mypage

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.example.maketeam_app.R
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentApplyUserDocumentBinding


class fragment_apply_user_document : BaseFragment<FragmentApplyUserDocumentBinding>(R.layout.fragment_apply_user_document){

    override fun initView() {
        binding.includeMystackDocument.stackTitle.text = "지원자 스택"
        binding.includeMystackDocument.teamSituationDetailBack.visibility = View.INVISIBLE

        binding.includeUserInfoDocument.textUserNameMyinfo.text = "박수호(3.8점)"

        binding.includeUserInfoDocument.btnMyinfoEdit.visibility = View.INVISIBLE
        binding.includeUserInfoDocument.applyDetailTitle.text = "김성우 교수님의 HCI 팀원 찾습니다."
        binding.includeMessageDocument.etMyMessage.setText("안녕하세요. 해당 공고에 지원하고 싶습니다. 지원 포지션은 아무거나 상관없어 표시하지 않았습니다.")
        binding.includeMessageDocument.etMyMessage.isClickable = false
        binding.includeMessageDocument.btnChatGptSend.visibility = View.INVISIBLE

        binding.headerUserDocument.textHeaderTitle.text = "지원자보기"
        binding.headerUserDocument.btnWriteDetailSetting.visibility = View.INVISIBLE
        binding.headerUserDocument.btnBackX.setBackgroundResource(R.drawable.btn_back_left)
    }

    override fun initClick() {
        binding.btnGetUserPhonenum.setOnClickListener { //전화번호 복사하기 버튼 누르면 전화화면으로 이동하게 하기
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:01011112222"))
            startActivity(intent)
        }
    }

}