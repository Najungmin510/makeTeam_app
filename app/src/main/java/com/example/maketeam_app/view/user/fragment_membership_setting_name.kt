package com.example.maketeam_app.view.user

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentMemberSettingNameBinding

class fragment_membership_setting_name :
    BaseFragment<FragmentMemberSettingNameBinding>(R.layout.fragment_member_setting_name){

    override fun initView() {
        (requireActivity() as MainActivity).noShowNavigation()
        (requireActivity() as MainActivity).noShowTabLayout()
        settingBar()
        val textAni : Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.textanim)
        binding.textNameTitle.startAnimation(textAni)


    }

    override fun initClick() {
        binding.etUserName.addTextChangedListener(object  : TextWatcher {
            var userInput = binding.etUserName
            var etLayout = binding.etLayoutUserName

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(userInput.text.toString().isEmpty()){
                    etLayout.error = "이름을 입력해주세요."
                    binding.btnNextSchool.setBackgroundResource(R.drawable.background_btn_border_gray)

                } else {
                    etLayout.error = null
                    binding.btnNextSchool.setBackgroundResource(R.drawable.background_btn_red)
                    binding.btnNextSchool.setOnClickListener {
                        findNavController().navigate(R.id.action_fragment_membership_setting_name_to_fragment_membership_setting_school)
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun settingBar(){
        binding.headerSettingName.btnBackX.setOnClickListener {
            activity?.finishAffinity()
        }
        binding.headerSettingName.textHeaderTitle.text = "회원가입"
        binding.headerSettingName.btnWriteDetailSetting.visibility = View.INVISIBLE

        (requireActivity() as MainActivity).noShowToolbar()
    }

}