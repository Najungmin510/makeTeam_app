package com.example.maketeam_app.view.board

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.access.ViewModel
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentBoardDetailBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class fragment_board_detail : BaseFragment<FragmentBoardDetailBinding>(R.layout.fragment_board_detail) {

    private val LOG = "boardDetail"
    private val vm : ViewModel by activityViewModels()
    private val args : fragment_board_detailArgs by navArgs()

    override fun initView() {
        settingTitle()
        settingDetail()
        (requireActivity() as MainActivity).noShowToolbar()
        initChartSetting(binding.groupBoardChart.chartApplyPeople)
        initChartDataSetting(binding.groupBoardChart.chartApplyPeople)
    }

    override fun initClick() {
        binding.btnApplyTeam.setOnClickListener {
            findNavController().navigate(fragment_board_detailDirections.actionFragmentBoardDetailToFragmentBoardApplyCheck(
                clickId = args.clickId
            ))
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
            val data = vm.getDetailBoard(args.clickId)

            withContext(Dispatchers.Main) {
                binding.groupBoardContent.teamLookTitleDetail.text = data.title
                binding.groupBoardContent.boardDetailContent.text = data.content

                if (!data.deadline.isNullOrEmpty()) binding.groupBoardContent.textTeamDateDetailDeadline.text = data.deadline

                if (!data.siteLink.isNullOrEmpty()) binding.groupBoardContent.textUri.text = data.siteLink
                else binding.groupBoardContent.textUri.autoLinkMask = 0

                binding.groupBoardContent.teamDateDetail.text = data.writeDay

                if(data.isEnd){
                    binding.groupBoardContent.teamSituationDetail.text = "모집완료"
                    binding.groupBoardContent.teamSituationDetail.setBackgroundResource(R.drawable.background_looking_for_team_close)
                    binding.groupBoardContent.teamSituationDetail.setTextColor(ContextCompat.getColor(requireContext(), R.color.main_color))

                    binding.btnApplyTeam.setBackgroundResource(R.drawable.background_btn_border_gray_upgrade)
                    binding.btnApplyTeam.text = "모집이 완료된 게시글이에요."
                    binding.btnApplyTeam.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_gray_blue))
                    binding.btnApplyTeam.isClickable = false

                } else {
                    binding.groupBoardContent.teamSituationDetail.text = "모집중"
                    binding.groupBoardContent.teamSituationDetail.setBackgroundResource(R.drawable.background_looking_for_team)
                    binding.groupBoardContent.teamSituationDetail.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_green))

                    binding.btnApplyTeam.setBackgroundResource(R.drawable.background_btn_red)
                    binding.btnApplyTeam.text = "지원하기"
                    binding.btnApplyTeam.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                    binding.btnApplyTeam.isClickable = true
                }

                val positions = data.position
                val inflater = LayoutInflater.from(requireContext())

                if (positions != null) {
                    val layoutPosition = binding.groupBoardPosition.layoutShowPosition
                    val guideNoPosition =
                        layoutPosition.findViewById<TextView>(R.id.text_position_no) //포지션 없을 때 안내 문구
                    guideNoPosition.visibility = View.GONE

                    for (p in positions) {
                        val addPosition = inflater.inflate(
                            R.layout.row_position_detail_show,
                            layoutPosition,
                            false
                        )
                        val needPositionName =
                            addPosition.findViewById<TextView>(R.id.text_team_position_name)
                        val needPositionDetail =
                            addPosition.findViewById<TextView>(R.id.text_team_position_detail)

                        needPositionName.text = "${p.positionName}(${p.positionPeople})"
                        needPositionDetail.text = p.positionDetail
                        layoutPosition.addView(addPosition)
                    }
                }
            }
        }
    }

    private fun initChartSetting(barChart: BarChart){

        barChart.setDrawGridBackground(false)//차트 격자구조 없음
        barChart.setDrawBarShadow(false)//차트 그림자 없음
        barChart.setDrawBorders(false)//차트 테두리 없음
        barChart.description.isEnabled = false
        barChart.isEnabled = false
        barChart.legend.isEnabled = false

        val xAxis : XAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)

        val leftAxis : YAxis = barChart.axisLeft
        leftAxis.setDrawAxisLine(false)
        leftAxis.isEnabled = false

        val rightAxis : YAxis = barChart.axisRight
        rightAxis.setDrawAxisLine(false)
        rightAxis.isEnabled = false

        barChart.animateY(1000)
        barChart.animateX(1000)

    }

    private fun initChartDataSetting(barChart: BarChart){
        barChart.setScaleEnabled(false) //확대축소 불가

        val applyList = ArrayList<BarEntry>()

        applyList.add(BarEntry(1.0f, 2.0f))
        applyList.add(BarEntry(2.0f, 3.0f))
        applyList.add(BarEntry(3.0f, 4.0f))

        val barDataSet = BarDataSet(applyList, "")
        barDataSet.setColors(Color.rgb(217, 74, 86),Color.rgb(217, 74, 86), Color.rgb(217, 74, 86) )
        barDataSet.setDrawValues(false)

        val data = BarData(barDataSet)
        barChart.data = data
        barChart.invalidate()
    }

}