package com.example.maketeam_app.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.maketeam_app.R
import com.example.maketeam_app.databinding.FragmentLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class login_fragment : AppCompatActivity() {

    private lateinit var binding : FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {

            Log.d("login","로그인 버튼 클릭")
            
            val callback : (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if(error != null){
                    Log.d("login","카카오 계정으로 로그인 실패")
                } else {
                    Log.d("login", "카카오 계정으로 로그인 성공")
                }
            }
            
            /**카카오톡으로 로그인*/
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {

                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if(error != null){
                        Log.d("login", "카카오톡으로 로그인 실패")
                        if(error is ClientError && error.reason == ClientErrorCause.Cancelled){
                            return@loginWithKakaoTalk
                        }

                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    } else if (token != null) {
                        Log.d("login","카카오톡으로 로그인 성공")
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }

        } //로그인 버튼


    }
}