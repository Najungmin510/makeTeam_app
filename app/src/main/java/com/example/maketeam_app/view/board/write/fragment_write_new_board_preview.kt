package com.example.maketeam_app.view.board.write

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
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
    override fun initView() {
        binding.headerWriteNewBoardPreview.btnWriteDetailSetting.visibility = View.INVISIBLE
        binding.headerWriteNewBoardPreview.textHeaderTitle.text = "글쓰기"

        settingPreview()
    }

    override fun initClick() {
        binding.btnUploadNewBoard.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                vm.boardInsert(BoardContent(0,
                    args.title,
                    args.content,
                    getDate(),
                    args.deadline,
                    args.link,
                    position = listOf(
                        Position("테스트","테스트내용"),
                        Position("테스트2","테스트내용2"))
                ))
            }

            Toast.makeText(requireContext(), "게시물을 업로드했어요", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun settingPreview(){
        binding.groupBoardDetailContent.teamLookTitleDetail.text = args.title
        binding.groupBoardDetailContent.boardDetailContent.text = args.content
        binding.groupBoardDetailContent.teamDateDetail.text = getDate()

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

    /**오늘 날짜 및 시간구하기*/
    @SuppressLint("SimpleDateFormat")
    private fun getDate() : String{
        val date = Date(System.currentTimeMillis())
        val simpleDateFormat = SimpleDateFormat(dateFormat)

        return simpleDateFormat.format(date)
    }

}