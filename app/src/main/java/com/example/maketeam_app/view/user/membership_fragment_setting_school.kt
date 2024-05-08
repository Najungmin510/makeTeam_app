package com.example.maketeam_app.view.user

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.maketeam_app.R
import com.example.maketeam_app.databinding.FragmentMembershipSettingSchoolBinding

class membership_fragment_setting_school : AppCompatActivity() {

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