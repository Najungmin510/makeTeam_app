package com.example.maketeam_app.view.notice

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.maketeam_app.databinding.FragmentEvaluationBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EvaluationFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentEvaluationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEvaluationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTextWithColor(binding.listApplication.scoreSkill, "해당 팀원의 실력은 어땠나요?", "실력")
        setTextWithColor(binding.listApplication.scoreCommunity, "해당 팀원의 커뮤니케이션 능력은 어땠나요?", "커뮤니케이션 능력")
        setTextWithColor(binding.listApplication.scoreSchedule, "해당 팀원의 일정 관리 능력은 어땠나요?", "일정 관리 능력")
    }

    private fun setTextWithColor(textView: TextView, fullText: String, targetText: String) {
        val spannableString = SpannableString(fullText)
        val start = fullText.indexOf(targetText)
        val end = start + targetText.length
        spannableString.setSpan(ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannableString
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EvaluationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
