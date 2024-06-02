package com.example.maketeam_app.view.user

import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentMembershipSettingSchoolBinding

class fragment_membership_setting_school : BaseFragment<FragmentMembershipSettingSchoolBinding>
    (R.layout.fragment_membership_setting_school){

    override fun initView() {
        (requireActivity() as MainActivity).noShowNavigation()
        (requireActivity() as MainActivity).noShowTabLayout()

        settingTextColor()
    }

    override fun initClick() {
        binding.etUserSchool.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.etUserSchool.text.toString().isEmpty()){
                    binding.etLayoutUserSchool.error = "학교이름을 입력해주세요."
                    binding.btnNextSchoolInform.setBackgroundResource(R.drawable.background_btn_border_gray)

                } else {
                    binding.etLayoutUserSchool.error = null
                    binding.btnNextSchoolInform.setBackgroundResource(R.drawable.background_btn_red)

                    binding.btnNextSchoolInform.setOnClickListener {
                        findNavController().navigate(R.id.action_fragment_membership_setting_school_to_fragment_membership_setting_sch_inform)
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun settingTextColor(){
        val school = SpannableStringBuilder(binding.titleSettingSchool.text.toString())
        school.apply {
            setSpan(ForegroundColorSpan(resources.getColor(R.color.main_color)), 10, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        binding.titleSettingSchool.text = school

        val textAni : Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.textanim)
        binding.titleSettingSchool.startAnimation(textAni)
    }

}