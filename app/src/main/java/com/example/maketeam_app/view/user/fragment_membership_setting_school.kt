package com.example.maketeam_app.view.user

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.databinding.FragmentMembershipSettingSchoolBinding

class fragment_membership_setting_school : AppCompatActivity() {

    private lateinit var binding : FragmentMembershipSettingSchoolBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMembershipSettingSchoolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val school = SpannableStringBuilder(binding.titleSettingSchool.text.toString())
        school.apply {
            setSpan(ForegroundColorSpan(resources.getColor(R.color.main_color)), 10, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        binding.titleSettingSchool.text = school

    }
}