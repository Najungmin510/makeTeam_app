package com.example.maketeam_app.view.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.access.ViewModel
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentHomeContestMainBinding
import com.example.maketeam_app.view.adapter.BoardAdapter
import com.example.maketeam_app.view.adapter.ItemClick

class home_fragment_contest_main : BaseFragment<FragmentHomeContestMainBinding>(R.layout.fragment_home_contest_main) {

    private val vm : ViewModel by activityViewModels()
    private val LOG = "contestMain"
    override fun initView() {
        (requireActivity() as MainActivity).showNavigation()
        (requireActivity() as MainActivity).showTabLayout()
        (requireActivity() as MainActivity).showToolbar()
        settingData()
    }

    override fun initClick() {
        binding.btnWriteNewBoardContest.setOnClickListener {
            findNavController().navigate(home_fragment_contest_mainDirections
                .actionHomeFragmentContestMainToFragmentWriteNewBoard(
                category = 1
            ))
        }
    }

    private fun settingData(){
        val adapter = BoardAdapter(requireContext(), object : ItemClick {
            @SuppressLint("CommitTransaction")
            override fun clickBoard(id: Long) {
                Log.d(LOG, "게시글 레이아웃 클릭")

                findNavController().navigate(home_fragment_contest_mainDirections
                    .actionHomeFragmentContestMainToFragmentBoardDetail(
                        clickId = id
                    ))

            } //clickboard

            override fun clickApplyEnd(id: Long, isEnd: Boolean) {

            }
        })

        binding.recyclerViewContent.adapter = adapter
        binding.recyclerViewContent.layoutManager = LinearLayoutManager(context)

        vm.getBoard(1).observe(this, Observer {
            adapter.setData(it)

            if(it.isEmpty()){
                binding.imageNoDataContest.visibility = View.VISIBLE
            } else {
                binding.imageNoDataContest.visibility = View.GONE
            }
        })
    }

}