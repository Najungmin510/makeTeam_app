package com.example.maketeam_app.view.mypage

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.maketeam_app.R
import com.example.maketeam_app.databinding.FragmentMyPageBinding
import com.example.maketeam_app.openai.GPTURL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lockEditText()
        editChatBot()
        editTextSubmit()
    }

    private fun lockEditText()
    {
        binding.introText.isEnabled = false
    }

    private fun editChatBot() {
        binding.editChatbot.setOnClickListener {
            val progressDialog = ProgressDialog(requireContext()).apply {
                setMessage("챗 봇이 첨삭 중 입니다. 잠시만 기다려 주세요.")
                setCancelable(false)
            }

            lifecycleScope.launch {
                progressDialog.show()
                try {
                    val response = withContext(Dispatchers.IO) {
                        val promptQuery = binding.introText.text.toString()
                        val response = GPTURL.chatGPT(promptQuery)
                        Log.d("프롬프트 쿼리", promptQuery)
                        Log.d("프롬프트 답장", response)
                        response // 이 부분에서 response를 반환해야 합니다.
                    }
                    withContext(Dispatchers.Main) {
                        binding.introText.text = Editable.Factory.getInstance().newEditable(response)
                    }
                } finally {
                    progressDialog.dismiss()
                }
            }
        }
    }

    private fun editTextSubmit() {
        binding.submitButton.setOnClickListener {

            // EditText에서 텍스트 가져오기
            val newText = binding.introText.text.toString()
            // 가져온 텍스트를 저장하거나 필요한 작업 수행
            // 여기서는 단순히 로그에 출력하는 것으로 대체하겠습니다.s
            println("새로운 텍스트: $newText")

            // 텍스트 입력창 비활성화
            if (binding.introText.text.toString() == getString(R.string.intro_placeholder)) {
                val editable = Editable.Factory.getInstance().newEditable("\n\n\n\n\n\n")
                binding.introText.text = editable
            }
            binding.introText.isEnabled = !binding.introText.isEnabled
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
