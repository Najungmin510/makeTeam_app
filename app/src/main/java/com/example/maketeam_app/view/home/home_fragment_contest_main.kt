package com.example.maketeam_app.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maketeam_app.R
import com.example.maketeam_app.access.ViewModel
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentHomeContentMainBinding
import com.example.maketeam_app.view.adapter.BoardAdapter
import com.example.maketeam_app.view.adapter.ItemClick
import com.example.maketeam_app.view.board.fragment_board_detail

class home_fragment_contest_main : BaseFragment<FragmentHomeContentMainBinding>(R.layout.fragment_home_content_main) {

    private val vm : ViewModel by activityViewModels()
    private val LOG = "contestMain"
    override fun initView() {
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

                parentFragmentManager.beginTransaction().replace(
                    R.id.container_fragment,
                    fragment_board_detail().apply {
                        arguments = Bundle().apply {
                            putLong("clickId", id)
                        }
                    }
                ).commit()

            } //clickboard
        })

        binding.recyclerViewContent.adapter = adapter
        binding.recyclerViewContent.layoutManager = LinearLayoutManager(context)

        vm.getBoard(1).observe(this, Observer {
            adapter.setData(it)
        })
    }

}