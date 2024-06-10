package com.example.maketeam_app.view.board

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.access.ViewModel
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentBoardDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class fragment_board_detail : BaseFragment<FragmentBoardDetailBinding>(R.layout.fragment_board_detail) {

    private val LOG = "boardDetail"
    private var id: Long = 0
    private val vm : ViewModel by activityViewModels()
    override fun initView() {

        id = arguments?.getLong("clickId")!!

        settingTitle()
        settingDetail()
        settingChart()
    }

    override fun initClick() {
        binding.btnApplyTeam.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_board_detail_to_fragment_board_apply_to_group)
        }
    }

    private fun settingTitle(){
        (requireActivity() as MainActivity).noShowTabLayout()
        (requireActivity() as MainActivity).noShowNavigation()

        binding.headerBoardDetail.textHeaderTitle.text = "한림대학교"
        binding.headerBoardDetail.btnWriteDetailSetting.visibility = View.INVISIBLE
        binding.headerBoardDetail.btnBackX.setBackgroundResource(R.drawable.btn_back_left)
    }

    @SuppressLint("SetTextI18n")
    private fun settingDetail(){
        lifecycleScope.launch(Dispatchers.IO){
            val data = vm.getDetailBoard(id)

            binding.groupBoardContent.teamLookTitleDetail.text = data.title
            binding.groupBoardContent.boardDetailContent.text = data.content

            if(!data.deadline.isNullOrEmpty()) binding.groupBoardContent.textTeamDateDetailDeadline.text = data.deadline

            if(!data.siteLink.isNullOrEmpty()) binding.groupBoardContent.textUri.text = data.siteLink
            else binding.groupBoardContent.textUri.autoLinkMask = 0

            binding.groupBoardContent.teamDateDetail.text = data.writeDay

            val positions = data.position
            val inflater = LayoutInflater.from(requireContext())

            if(positions != null){
                val layoutPosition = binding.groupBoardPosition.layoutShowPosition
                val guideNoPosition = layoutPosition.findViewById<TextView>(R.id.text_position_no) //포지션 없을 때 안내 문구
                guideNoPosition.visibility = View.GONE

                for(p in positions){
                    val addPosition = inflater.inflate(R.layout.row_position_detail_show, layoutPosition, false)
                    val needPositionName = addPosition.findViewById<TextView>(R.id.text_team_position_name)
                    val needPositionDetail = addPosition.findViewById<TextView>(R.id.text_team_position_detail)

                    needPositionName.text = "${p.positionName}(${p.positionPeople})"
                    needPositionDetail.text = p.positionDetail
                    layoutPosition.addView(addPosition)
                }
            }
        }
    }

    private fun settingChart(){

    }

}