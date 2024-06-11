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


        initView()
        initClick()
    }

    private fun initView() {
        initAdapter()
        moveMyPageHome()
    }

    private fun initAdapter()
    {
        // Initialize ListView and set Adapter
        listView = binding.listView
        teamMembers = listOf(
            TeamMember("김성우 교수님의 HCI 팀원 찾습니다", "송태웅", "한림대학교", "20195189"),
            TeamMember("김성우 교수님의 HCI 팀원 찾습니다", "김김김", "한림대학교", "20240000"),
            TeamMember("김성우 교수님의 HCI 팀원 찾습니다", "김김김", "한림대학교", "20240000"),
            TeamMember("김성우 교수님의 HCI 팀원 찾습니다", "김김김", "한림대학교", "20240000")

        )
        val adapter = TeamAdapter(requireContext(), teamMembers)
        listView.adapter = adapter
    }

    private fun moveMyPageHome()
    {
        binding.listView.setOnClickListener{

        }
    }

    private fun initClick() {
//        binding.someButton.setOnClickListener {
//            Toast.makeText(requireContext(), "Button clicked", Toast.LENGTH_SHORT).show()
//        }
    }
}
