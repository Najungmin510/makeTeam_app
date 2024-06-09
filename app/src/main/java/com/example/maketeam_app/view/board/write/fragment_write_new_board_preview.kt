package com.example.maketeam_app.view.board.write

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.maketeam_app.R
import com.example.maketeam_app.access.ViewModel
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentWriteNewBoardPreviewBinding
import com.example.maketeam_app.model.BoardContent
import com.example.maketeam_app.model.Position
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.Date


/**
 * 업로드 하기 전 미리보기
 * */
@AndroidEntryPoint
class fragment_write_new_board_preview : BaseFragment<FragmentWriteNewBoardPreviewBinding>(R.layout.fragment_write_new_board_preview) {

    private val args : fragment_write_new_board_previewArgs by navArgs()
    private val vm : ViewModel by activityViewModels()
    private val dateFormat ="yyyy.MM.dd HH:mm"
    private val LOG = "preview"
    override fun initView() {
        binding.headerWriteNewBoardPreview.btnWriteDetailSetting.visibility = View.INVISIBLE
        binding.headerWriteNewBoardPreview.textHeaderTitle.text = "글쓰기"

        settingPreview()
    }

    override fun initClick() {
        binding.btnUploadNewBoard.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                vm.boardInsert(BoardContent(
                    args.category,
                    args.title,
                    args.content,
                    getDate(),
                    args.deadline,
                    args.link,
                    position = args.position?.toList()
                ))
            }

            Toast.makeText(requireContext(), "게시물을 업로드했어요", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.home_fragment_school_main)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun settingPreview(){
        binding.groupBoardDetailContent.teamLookTitleDetail.text = args.title
        binding.groupBoardDetailContent.boardDetailContent.text = args.content
        binding.groupBoardDetailContent.teamDateDetail.text = getDate()

        positionView()

        if(!args.deadline.isNullOrEmpty()){
            binding.groupBoardDetailContent.textTeamDateDetailDeadline.text = args.deadline
        } else {
            binding.groupBoardDetailContent.textTeamDateDetailDeadline.text = "입력된 프로젝트 마감 일자가 없어요."
        }

        if(!args.link.isNullOrEmpty()){
            binding.groupBoardDetailContent.textUri.text = args.link
        } else {
            binding.groupBoardDetailContent.textUri.text = "입력된 사이트 링크가 없어요."
            binding.groupBoardDetailContent.textUri.autoLinkMask = 0
        }
    }

    /**포지션 부분 동적 추가*/
    @SuppressLint("SetTextI18n")
    private fun positionView() {
        val position: Array<Position>? = args.position
        val inflater = LayoutInflater.from(requireContext())

        if (position != null) {
            val layoutPosition = binding.groupBoardDetailPosition.layoutShowPosition
            val guideNoPosition = layoutPosition.findViewById<TextView>(R.id.text_position_no) //포지션 없을 때 안내 문구
            guideNoPosition.visibility = View.GONE

            for(p in position){ //포지션 수 만큼 레이아웃 생성
                val addPosition = inflater.inflate(R.layout.row_position_detail_show, layoutPosition, false)
                val previewP = addPosition.findViewById<TextView>(R.id.text_team_position_name) //포지션 명
                val previewS = addPosition.findViewById<TextView>(R.id.text_team_position_detail) //포지션 요구 스킬

                previewP.text = "${p.positionName}(${p.positionPeople})"
                previewS.text = p.positionDetail
                layoutPosition.addView(addPosition)
            }
        }
    }

    /**오늘 날짜 및 시간구하기*/
    @SuppressLint("SimpleDateFormat")
    private fun getDate() : String{
        val date = Date(System.currentTimeMillis())
        val simpleDateFormat = SimpleDateFormat(dateFormat)

        return simpleDateFormat.format(date)
    }

}