package com.example.maketeam_app.view.notice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.maketeam_app.R
import com.example.maketeam_app.databinding.FragmentNoticeBinding


class NoticeFragment : Fragment() {

    private var _binding: FragmentNoticeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoticeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moveUntilNow()
    }

    private fun moveUntilNow()
    {
        binding.listTeamFind.listTeamFind.setOnClickListener()
        {
            findNavController().navigate(R.id.action_fragment_notice_to_fragment_until_now)
        }

        binding.listApplication.listApplication.setOnClickListener()
        {
            findNavController().navigate(R.id.action_fragment_notice_to_fragment_until_now)
        }

        binding.listJudment.listJudment.setOnClickListener()
        {
            findNavController().navigate(R.id.action_fragment_notice_to_fragment_until_now)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
