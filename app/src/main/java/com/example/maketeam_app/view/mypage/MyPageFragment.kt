package com.example.maketeam_app.view.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.access.ViewModel
import com.example.maketeam_app.databinding.FragmentMyPageBinding
import com.example.maketeam_app.view.adapter.BoardAdapter
import com.example.maketeam_app.view.adapter.ItemClick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!
    private val vm : ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).noShowTabLayout()
        (requireActivity() as MainActivity).noShowToolbar()
        binding.headerMyPage.btnBackX.visibility = View.INVISIBLE
        binding.headerMyPage.textHeaderTitle.text = "프로필"
        binding.headerMyPage.btnWriteDetailSetting.visibility = View.INVISIBLE

        moveMettingFragment()
        moveApplytoFragment()
        settingAdapter()
    }

    private fun moveMettingFragment() {
        binding.meetingLayout.setOnClickListener {
            findNavController().navigate(R.id.action_mypage_fragment_to_meeting_fragment)
        }
    }

    private fun moveApplytoFragment()
    {
        /*
        binding.post1.setOnClickListener {
            findNavController().navigate(R.id.action_mypage_fragment_to_applyto_fragment)
        }

        binding.post2.setOnClickListener {
            findNavController().navigate(R.id.action_mypage_fragment_to_applyto_fragment)
        }*/
    }

    private fun settingAdapter(){

        val adapter = BoardAdapter(requireContext(), object : ItemClick{
            override fun clickBoard(id: Long) { //클릭하면 글 자세히 보기로 이동

            }

            override fun clickApplyEnd(id: Long, isEnd: Boolean) {
                vm.updateBoardApply(id, !isEnd)

                if(isEnd){
                    Toast.makeText(requireContext(), "팀원 모집을 다시 시작했어요.",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "팀원 모집을 마감했어요.",Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.recyclerViewMyBoardList.adapter = adapter
        binding.recyclerViewMyBoardList.layoutManager = LinearLayoutManager(context)

        vm.getAllBoard().observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
