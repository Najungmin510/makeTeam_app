package com.example.maketeam_app.view.board.apply

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Build
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.access.ViewModel
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.base.BaseProgressDialog
import com.example.maketeam_app.databinding.FragmentBoardApplyToGroupBinding
import com.example.maketeam_app.openai.GPTURL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class fragment_board_apply_to_group : BaseFragment<FragmentBoardApplyToGroupBinding>(R.layout.fragment_board_apply_to_group) {

    private val args : fragment_board_apply_to_groupArgs by navArgs()
    private val vm : ViewModel by activityViewModels()
    private var ischeck = false

    override fun initView() {
        (requireActivity() as MainActivity).noShowNavigation()
        (requireActivity() as MainActivity).noShowTabLayout()
        (requireActivity() as MainActivity).noShowToolbar()
        settingData()
    }

    override fun initClick() {
        apply()
        chatBotUse()

        binding.groupApplyButton.btnSendApply.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_board_apply_to_group_to_home_fragment_school_main)
            Toast.makeText(requireContext(), "지원을 완료했어요.", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 포지션 체크박스 생성
     * */
    @SuppressLint("SetTextI18n")
    private fun settingData(){
        lifecycleScope.launch(Dispatchers.IO){
            val data = vm.getDetailBoard(args.clickId)

            withContext(Dispatchers.Main){
                val positions = data.position
                val inflater = LayoutInflater.from(requireContext())

                if(positions != null){
                    val layoutPosition = binding.groupSelectMyPosition.layoutSelectMyPosition

                    val guideNoPosition = layoutPosition.findViewById<TextView>(R.id.text_position_no_apply)
                    guideNoPosition.visibility = View.GONE

                    for(p in positions){
                        val addPosition = inflater.inflate(
                            R.layout.row_btn_checkbox_apply,
                            layoutPosition,
                            false
                        )

                        val needPositionName = addPosition.findViewById<TextView>(R.id.team_apply_position_name)
                        val needPositionDetail = addPosition.findViewById<TextView>(R.id.team_apply_position_detail)

                        needPositionName.text = "${p.positionName}(${p.positionPeople})"
                        needPositionDetail.text = p.positionDetail
                        layoutPosition.addView(addPosition)
                    }
                }

                binding.groupMyInfo.applyDetailTitle.text = data.title
                binding.headerApplyToGroup.btnBackX.visibility = View.INVISIBLE
                binding.headerApplyToGroup.textHeaderTitle.text = "지원하기"
                binding.headerApplyToGroup.btnWriteDetailSetting.visibility = View.INVISIBLE
            }
        }

    }

    private fun apply(){
        binding.groupApplyButton.textPushAlarm.setOnClickListener {
            ischeck = if(!ischeck){
                binding.groupApplyButton.textPushAlarm
                    .setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_checkbox_yes,0,0,0)
                true
            } else {
                binding.groupApplyButton.textPushAlarm
                    .setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_checkbox_no,0,0,0)
                false
            }
        }
    }

    private fun chatBotUse(){
        binding.groupWriteMyMessage.btnChatGptSend.setOnClickListener {
            var dialog = BaseProgressDialog.progressDialog(requireContext())

            lifecycleScope.launch {
                withContext(Dispatchers.Main){
                   dialog.show()
                }

                try {
                    val response = withContext(Dispatchers.IO) {
                        val promptQuery = binding.groupWriteMyMessage.etMyMessage.text.toString()
                        val response = GPTURL.chatGPT(promptQuery)
                        Log.d("프롬프트 쿼리", promptQuery)
                        Log.d("프롬프트 답장", response)
                        response // 이 부분에서 response를 반환해야 합니다.
                    }

                    withContext(Dispatchers.Main) {
                        binding.groupWriteMyMessage.etMyMessage.text = Editable.Factory.getInstance().newEditable(response)
                    }

                } finally {
                    dialog.dismiss()
                }
            }
        }
    }

    /*
    @SuppressLint("MissingInflatedId")
    private fun settingProgress(){
        val inflater = LayoutInflater.from(requireContext())
        val layoutPosition = binding.layoutApplyMain
        val addProgress = inflater.inflate(R.layout.dialog_progress_loading, layoutPosition, false)
        progressBar = addProgress.findViewById(R.id.spinkit_progress)

        val doubleBounce = DoubleBounce()
        progressBar.indeterminateDrawable = doubleBounce

        layoutPosition.addView(addProgress)
        progressBar.visibility = View.GONE
    }

    private fun showProgress(){
        progressBar.visibility = View.VISIBLE
    }

    private fun noShowProgress(){
        progressBar.visibility = View.GONE
    }
*/
}