package com.example.maketeam_app.view.notice

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.maketeam_app.R
import com.example.maketeam_app.databinding.FragmentEvaluationBinding

class EvaluationFragment : Fragment() {

    private var _binding: FragmentEvaluationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEvaluationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Change text color for specific parts
        setColoredText()

        // Set up button click listener
        moveNotice()
    }

    private fun setColoredText() {
        // 첫 번째 TextView: "해당 팀원의 실력은 어땠나요?"에서 "실력" 부분만 빨간색으로 변경
        val skillText = "해당 팀원의 실력은 어땠나요?"
        val skillSpannable = SpannableString(skillText)
        val skillStart = skillText.indexOf("실력")
        val skillEnd = skillStart + "실력".length
        skillSpannable.setSpan(ForegroundColorSpan(Color.RED), skillStart, skillEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.listApplication.scoreSkill.text = skillSpannable

        // 두 번째 TextView: "해당 팀원의 커뮤니케이션 능력은 어땠나요?"에서 "커뮤니케이션 능력" 부분만 빨간색으로 변경
        val communityText = "해당 팀원의 커뮤니케이션 능력은 어땠나요?"
        val communitySpannable = SpannableString(communityText)
        val communityStart = communityText.indexOf("커뮤니케이션 능력")
        val communityEnd = communityStart + "커뮤니케이션 능력".length
        communitySpannable.setSpan(ForegroundColorSpan(Color.RED), communityStart, communityEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.listApplication.scoreCommunity.text = communitySpannable

        // 세 번째 TextView: "해당 팀원의 일정 관리 능력은 어땠나요?"에서 "팀원의 일정 관리 능력" 부분만 빨간색으로 변경
        val scheduleText = "해당 팀원의 일정 관리 능력은 어땠나요?"
        val scheduleSpannable = SpannableString(scheduleText)
        val scheduleStart = scheduleText.indexOf("팀원의 일정 관리 능력")
        val scheduleEnd = scheduleStart + "팀원의 일정 관리 능력".length
        scheduleSpannable.setSpan(ForegroundColorSpan(Color.RED), scheduleStart, scheduleEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.listApplication.scoreSchedule.text = scheduleSpannable
    }

    private fun moveNotice() {
        binding.btnSuccess.setOnClickListener {
            Toast.makeText(requireContext(), "평가를 완료하였습니다.", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_fragment_evaluation_to_fragment_notice)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
