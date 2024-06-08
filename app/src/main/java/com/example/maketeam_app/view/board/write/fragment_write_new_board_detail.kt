package com.example.maketeam_app.view.board.write

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.maketeam_app.R
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentWriteNewBoardDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class fragment_write_new_board_detail : BaseFragment<FragmentWriteNewBoardDetailBinding>(R.layout.fragment_write_new_board_detail) {
    private val args :  fragment_write_new_board_detailArgs by navArgs()
    private val LOG = "wboardDetail"

    override fun initView() {
        Log.d(LOG, args.content)
        Log.d(LOG, args.title)

        binding.headerWriteNewBoardDetail.textHeaderTitle.text = "글쓰기"
        binding.headerWriteNewBoardDetail.btnWriteDetailSetting.visibility = View.INVISIBLE
    }

    override fun initClick() {
        //val inflater = LayoutInflater.from(requireContext())
        //val layoutPosition = binding.groupWritePosition.layoutPositionGroup

        binding.groupWritePosition.btnAddPosition.setOnClickListener {
            addView()
        }

        binding.btnGoFinalCheckPage.setOnClickListener {
            previewPage()
        }

    }

    private fun addView(){
        val inflater = LayoutInflater.from(requireContext())
        val layoutPosition = binding.groupWritePosition.layoutPositionGroup

        val addPositionView = inflater.inflate(R.layout.row_member_positon_layout, layoutPosition, false)
        layoutPosition.addView(addPositionView)

        val remove = addPositionView.findViewById<ImageButton>(R.id.btn_delete_position)
        remove.setOnClickListener {
            layoutPosition.removeView(addPositionView)
        }
    }

    private fun previewPage(){
        val date = binding.groupWriteDeadline.etDate.text.toString().ifEmpty { null }
        val site = binding.groupWriteSiteLink.etWebsiteLink.text.toString().ifEmpty { null }

        findNavController().navigate(fragment_write_new_board_detailDirections
            .actionFragmentWriteNewBoardDetailToFragmentWriteNewBoardPreview(
                title = args.title,
                content = args.content,
                deadline = date,
                link = site
            ))
    }

}