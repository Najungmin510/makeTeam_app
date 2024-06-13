package com.example.maketeam_app.view.mypage

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
import com.example.maketeam_app.databinding.FragmentApplytoBinding
import com.example.maketeam_app.openai.GPTURL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ApplytoFragment : BaseFragment<FragmentApplytoBinding>(R.layout.fragment_applyto) {

    private val vm : ViewModel by activityViewModels()
    private var ischeck = false
    private lateinit var progressBar: ProgressBar
    override fun initView() {
        (requireActivity() as MainActivity).noShowNavigation()
        (requireActivity() as MainActivity).noShowTabLayout()
        (requireActivity() as MainActivity).noShowToolbar()
        //settingProgress()
    }

    override fun initClick() {
        //apply()
        chatBotUse()
        goMyPage()
    }

    /**
     * 포지션 체크박스 생성
     * */
//    @SuppressLint("SetTextI18n")
//    private fun settingApplyPosition(){
//        lifecycleScope.launch(Dispatchers.IO){
//            val data = vm.getDetailBoard(args.clickId)
//
//            withContext(Dispatchers.Main){
//                val positions = data.position
//                val inflater = LayoutInflater.from(requireContext())
//
//                if(positions != null){
//                    val layoutPosition = binding.groupSelectMyPosition.layoutSelectMyPosition
//                    val guideNoPosition = layoutPosition.findViewById<TextView>(R.id.text_position_no_apply)
//                    guideNoPosition.visibility = View.GONE
//
//                    for(p in positions){
//                        val addPosition = inflater.inflate(
//                            R.layout.row_btn_checkbox_apply,
//                            layoutPosition,
//                            false
//                        )
//
//                        val needPositionName = addPosition.findViewById<TextView>(R.id.team_apply_position_name)
//                        val needPositionDetail = addPosition.findViewById<TextView>(R.id.team_apply_position_detail)
//
//                        needPositionName.text = "${p.positionName}(${p.positionPeople})"
//                        needPositionDetail.text = p.positionDetail
//                        layoutPosition.addView(addPosition)
//                    }
//                }
//            }
//        }
//
//    }

//    private fun apply(){
//        binding.groupApplyButton.textPushAlarm.setOnClickListener {
//            ischeck = if(!ischeck){
//                binding.groupApplyButton.textPushAlarm
//                    .setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_checkbox_yes,0,0,0)
//                true
//            } else {
//                binding.groupApplyButton.textPushAlarm
//                    .setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_checkbox_no,0,0,0)
//                false
//            }
//        }
//    }

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

    private fun goMyPage()
    {
        binding.groupApplyButton.btnEdit.setOnClickListener()
        {

            Toast.makeText(requireContext(), "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_applyto_fragment_to_mypage_fragment)
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