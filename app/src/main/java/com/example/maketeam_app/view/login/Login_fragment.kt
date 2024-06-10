package com.example.maketeam_app.view.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.findNavController
import com.example.maketeam_app.MainActivity
import com.example.maketeam_app.R
import com.example.maketeam_app.base.BaseFragment
import com.example.maketeam_app.databinding.FragmentLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class login_fragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override fun initView() {
        Log.d("login", "로그인화면 진입")
        (requireActivity() as MainActivity).noShowTabLayout()
        (requireActivity() as MainActivity).noShowNavigation()

    }

    override fun initClick() {
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_login_fragment_to_fragment_membership_setting_name)
//            Log.d("login","로그인 버튼 클릭")
//
//            val callback : (OAuthToken?, Throwable?) -> Unit = { token, error ->
//                if(error != null){
//                    Log.d("login","카카오 계정으로 로그인 실패")
//                    Log.d("로그인 에러", error.toString())
//                } else {
//                    Log.d("login", "카카오 계정으로 로그인 성공")
//                }
//            }
//
//            /**카카오톡으로 로그인*/
//            if(UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
//
//                UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
//                    if(error != null){
//                        Log.d("login", "카카오톡으로 로그인 실패")
//                        if(error is ClientError && error.reason == ClientErrorCause.Cancelled){
//                            return@loginWithKakaoTalk
//                        }
//
//                        UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
//                    } else if (token != null) {
//                        Toast.makeText(requireContext(), "카카오로 로그인 성공", Toast.LENGTH_SHORT).show()
//                        findNavController().navigate(R.id.action_login_fragment_to_fragment_membership_setting_name)
//                        Log.d("login","카카오톡으로 로그인 성공")
//                    }
//                }
//            } else {
//                UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
//            }

        } //로그인 버튼
    }

}