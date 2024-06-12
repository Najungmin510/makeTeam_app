package com.example.maketeam_app.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.access.ViewModel
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentHomeMainBinding
import com.example.maketeam_app.view.adapter.BoardAdapter
import com.example.maketeam_app.view.adapter.ItemClick
import com.example.maketeam_app.view.board.fragment_board_detail
import com.example.maketeam_app.view.board.write.fragment_write_new_board_previewDirections

class home_fragment_school_main : BaseFragment<FragmentHomeMainBinding>(R.layout.fragment_home_main) {

    private val vm : ViewModel by activityViewModels()
    private val LOG = "schoolmain"
    override fun initView() {
        (requireActivity() as MainActivity).showNavigation()
        (requireActivity() as MainActivity).showTabLayout()
        (requireActivity() as MainActivity).showToolbar()
        settingData()

    }

    override fun initClick() {
        binding.btnWriteNewBoardSchool.setOnClickListener {
            findNavController().navigate(home_fragment_school_mainDirections
                .actionHomeFragmentSchoolMainToFragmentWriteNewBoard(
                category = 0
            ))
        }
    }

    private fun settingData(){
        val adapter = BoardAdapter(requireContext(), object : ItemClick {
            @SuppressLint("CommitTransaction")
            override fun clickBoard(id: Long) {
                Log.d(LOG, "게시글 레이아웃 클릭")

                findNavController().navigate(home_fragment_school_mainDirections
                    .actionHomeFragmentSchoolMainToFragmentBoardDetail(
                        clickId = id
                    ))

//                parentFragmentManager.beginTransaction().replace(
//                    R.id.container_fragment,
//                    fragment_board_detail().apply {
//                        arguments = Bundle().apply {
//                            putLong("clickId", id)
//                        }
//                    }
//                ).commit()

            } //clickboard

            override fun clickApplyEnd(id: Long, isEnd: Boolean) {

            }

        })

        binding.recyclerViewSchool.adapter = adapter
        binding.recyclerViewSchool.layoutManager = LinearLayoutManager(context)

        vm.getBoard(0).observe(this, Observer {
            adapter.setData(it)

            if(it.isEmpty()){
                binding.imageNoDataHome.visibility = View.VISIBLE
            } else {
                binding.imageNoDataHome.visibility = View.GONE
            }
        })

    }

}