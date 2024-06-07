package com.example.maketeam_app.view.user

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.findNavController
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentMembershipSettingSchInformBinding

class fragment_membership_setting_sch_inform : BaseFragment<FragmentMembershipSettingSchInformBinding> (R.layout.fragment_membership_setting_sch_inform) {

    private var number = false
    private var major = false
    override fun initView() {
        (requireActivity() as MainActivity).noShowNavigation()
        (requireActivity() as MainActivity).noShowTabLayout()

        val textAni : Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.textanim)
        binding.titleSettingSchInform.startAnimation(textAni)
    }

    override fun initClick() {
        userNumber()
        userMajor()

    }

    private fun userNumber(){

        binding.etUserNum.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                number = if(binding.etUserNum.text.toString().isEmpty()){
                    binding.etLayoutUserNum.error ="값을 입력해주세요."
                    false
                } else if(binding.etUserNum.text.toString().length < 8){
                    binding.etLayoutUserNum.error ="학번은 8자리로 입력해주세요."
                    false
                } else {
                    binding.etLayoutUserNum.error = null
                    true
                }
                update()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

    }

    private fun userMajor() {

        binding.etUserMajor.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                major = if(binding.etUserMajor.text.toString().isEmpty()){
                    binding.etLayoutUserMajor.error = "값을 입력해주세요."
                    false
                } else {
                    binding.etLayoutUserMajor.error = null
                    true
                }
                update()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }


    private fun update(){
        if (number && major) {
            binding.btnNextFinish.setBackgroundResource(R.drawable.background_btn_red)
            binding.btnNextFinish.setOnClickListener {
                findNavController().navigate(R.id.action_fragment_membership_setting_sch_inform_to_fragment_membership_success)
            }
        } else {
            binding.btnNextFinish.setBackgroundResource(R.drawable.background_btn_border_gray)
            binding.btnNextFinish.setOnClickListener {
                Toast.makeText(requireContext(), "내용을 모두 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}