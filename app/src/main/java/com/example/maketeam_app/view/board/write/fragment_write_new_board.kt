package com.example.maketeam_app.view.board.write

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.access.ViewModel
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentWriteNewBoardBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * 텍스트 관찰하는게 더 좋아서 textwacher 사용
 * */
@AndroidEntryPoint
class fragment_write_new_board : BaseFragment<FragmentWriteNewBoardBinding>(R.layout.fragment_write_new_board) {

    private var title = false
    private var content = false

    private val vm : ViewModel by activityViewModels()
    override fun initView() {
        (requireActivity() as MainActivity).noShowNavigation()
        (requireActivity() as MainActivity).noShowTabLayout()

        isCheck()
    }

    override fun initClick() {
        binding.headerWriteNewBoard.btnWriteDetailSetting.setOnClickListener {
            if(title && content){
                goDetailScreen()
            } else {
                Toast.makeText(requireContext(), "제목, 내용을 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goDetailScreen(){
        findNavController().navigate(
            fragment_write_new_boardDirections.actionFragmentWriteNewBoardToFragmentWriteNewBoardDetail(
                title = binding.textTitle.text.toString(),
                content = binding.etContent.text.toString()
            )
        )
    }

    private fun isCheck(){
        binding.textTitle.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                title = binding.textTitle.text.toString().isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.etContent.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                content = binding.etContent.text.toString().isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

}