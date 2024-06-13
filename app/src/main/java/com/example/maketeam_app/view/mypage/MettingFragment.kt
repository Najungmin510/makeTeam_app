package com.example.maketeam_app.view.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.view.adapter.TeamAdapter
import com.example.maketeam_app.view.adapter.TeamMember
import com.example.maketeam_app.databinding.FragmentMettingBinding

class MettingFragment : Fragment() {

    private lateinit var binding: FragmentMettingBinding
    private lateinit var listView: ListView
    private lateinit var teamMembers: List<TeamMember>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).noShowNavigation()
        (requireActivity() as MainActivity).noShowTabLayout()
        (requireActivity() as MainActivity).noShowToolbar()

        initView()
        initClick()
    }

    private fun initView() {
        initAdapter()

        binding.headerMetting.textHeaderTitle.text = "지금까지 만난 팀원"
        binding.headerMetting.btnWriteDetailSetting.visibility = View.INVISIBLE
    }

    private fun initAdapter()
    {
        // Initialize ListView and set Adapter
        listView = binding.listView
        teamMembers = listOf(
            TeamMember("김성우 교수님의 HCI 팀원 찾습니다", "송태웅", "한림대학교", "20195189"),
            TeamMember("김성우 교수님의 HCI 팀원 찾습니다", "김민주", "한림대학교", "20244862"),
            TeamMember("김성우 교수님의 HCI 팀원 찾습니다", "박혜선", "한림대학교", "20231112"),
            TeamMember("김성우 교수님의 HCI 팀원 찾습니다", "김훈", "한림대학교", "20205568")

        )
        val adapter = TeamAdapter(requireContext(), teamMembers)
        listView.adapter = adapter
    }


    private fun initClick() {
//        binding.someButton.setOnClickListener {
//            Toast.makeText(requireContext(), "Button clicked", Toast.LENGTH_SHORT).show()
//        }
    }
}
