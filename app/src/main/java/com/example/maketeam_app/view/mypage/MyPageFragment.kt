package com.example.maketeam_app.view.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.maketeam_app.R
import com.example.maketeam_app.databinding.FragmentMyPageBinding


class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moveMettingFragment()
        moveApplytoFragment()
        mozipFlag()
    }

    private fun moveMettingFragment() {
        binding.meetingLayout.setOnClickListener {
            findNavController().navigate(R.id.action_mypage_fragment_to_meeting_fragment)
        }
    }

    private fun moveApplytoFragment()
    {
        binding.post1.setOnClickListener {
            findNavController().navigate(R.id.action_mypage_fragment_to_applyto_fragment)
        }

        binding.post2.setOnClickListener {
            findNavController().navigate(R.id.action_mypage_fragment_to_applyto_fragment)
        }
    }

    private fun mozipFlag() {
//        // DB에서 isEnd 값을 가져와서 모집 상태를 설정
//        val isEnd = // 여기에 데이터베이스에서 isEnd 값을 가져오는 코드를 작성해야 합니다.
//            binding.mozip.text = if (isEnd) "모집완료" else "모집중"
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
