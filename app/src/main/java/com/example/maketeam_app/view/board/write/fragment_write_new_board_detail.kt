package com.example.maketeam_app.view.board.write

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentWriteNewBoardDetailBinding
import com.example.maketeam_app.model.Position
import com.github.mikephil.charting.charts.BarChart
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class fragment_write_new_board_detail : BaseFragment<FragmentWriteNewBoardDetailBinding>(R.layout.fragment_write_new_board_detail) {
    private val args :  fragment_write_new_board_detailArgs by navArgs()
    private val LOG = "wboardDetail"
    private val positionList = mutableListOf<LinearLayout>()

    override fun initView() {
        if(args.category == 0){
            binding.headerWriteNewBoardDetail.textHeaderTitle.text = "교내 글쓰기"
        } else {
            binding.headerWriteNewBoardDetail.textHeaderTitle.text = "공모전 글쓰기"
        }
        binding.headerWriteNewBoardDetail.btnWriteDetailSetting.visibility = View.INVISIBLE
        (requireActivity() as MainActivity).noShowToolbar()
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

        setDate()

    }

    /**새 포지션 추가 부분 */
    private fun addView(){
        val inflater = LayoutInflater.from(requireContext())
        val layoutPosition = binding.groupWritePosition.layoutPositionGroup

        val addPositionView = inflater.inflate(R.layout.row_member_positon_layout, layoutPosition, false)
        layoutPosition.addView(addPositionView)

        val remove = addPositionView.findViewById<ImageButton>(R.id.btn_delete_position)

        remove.setOnClickListener {
            layoutPosition.removeView(addPositionView)
        }

        positionList.add(addPositionView as LinearLayout) //동적으로 추가한 레이아웃을 리니어 레이아웃 형태로 담아줌

    }

    /**사용자가 입력한 포지션 전부 가져오기*/
    private fun getNeedPosition() : List<Position> {
        val position = mutableListOf<Position>()

        for(layout in positionList){ //동적으로 추가했던 레이아웃들이 있으니, 이 레이아웃에서 각 edittext 의 값을 뽑아오기
            val pName = layout.findViewById<EditText>(R.id.et_position_name).text.toString()
            val pSkill = layout.findViewById<EditText>(R.id.et_position_skill).text.toString()
            val pPeople = layout.findViewById<EditText>(R.id.et_position_people).text.toString()

            if(pName.isNotEmpty() && pSkill.isNotEmpty() && pPeople.isNotEmpty()){ //모든 값이 다 입력되어 있을경우에만
                Log.d(LOG, "포지션 명: $pName")
                Log.d(LOG, "포지션 스킬: $pSkill")
                Log.d(LOG, "인원수: $pPeople")
                val data = Position(pName, pSkill, pPeople)
                position.add(data) //데이터 추가
            }
        }
        return position
    }

    @SuppressLint("SetTextI18n")
    private fun setDate(){
        binding.groupWriteDeadline.etDate.setOnClickListener {
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)
            context?.let { it1 ->
                DatePickerDialog(it1, { _, year, month, day ->
                    run {
                        binding.groupWriteDeadline.etDate.setText(year.toString() + "." + (month + 1).toString() + "." + day.toString())
                    }
                }, year, month, day)
            }?.show()
        }
    }


    /**작성글 미리보기로 넘어가는 코드*/
    private fun previewPage(){
        val date = binding.groupWriteDeadline.etDate.text.toString().ifEmpty { null }
        val site = binding.groupWriteSiteLink.etWebsiteLink.text.toString().ifEmpty { null }

        val getPosition = getNeedPosition().ifEmpty { null }

        findNavController().navigate(fragment_write_new_board_detailDirections
            .actionFragmentWriteNewBoardDetailToFragmentWriteNewBoardPreview(
                category = args.category,
                title = args.title,
                content = args.content,
                deadline = date,
                link = site,
                position = getPosition?.toTypedArray()
            ))
    }

}