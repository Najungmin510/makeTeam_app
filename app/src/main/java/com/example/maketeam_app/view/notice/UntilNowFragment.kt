package com.example.maketeam_app.view.notice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.maketeam_app.R
import com.example.maketeam_app.databinding.FragmentUntilNowBinding


class UntilNowFragment : Fragment() {

    private var _binding: FragmentUntilNowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUntilNowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moveEvaluation()
    }

    private fun moveEvaluation()
    {
        binding.utilMeet1.listItemUtilNow.setOnClickListener()
        {
            findNavController().navigate(R.id.action_fragment_until_now_to_fragment_evaluation)
        }

        binding.utilMeet2.listItemUtilNow.setOnClickListener()
        {
            findNavController().navigate(R.id.action_fragment_until_now_to_fragment_evaluation)
        }

        binding.utilMeet3.listItemUtilNow.setOnClickListener()
        {
            findNavController().navigate(R.id.action_fragment_until_now_to_fragment_evaluation)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
