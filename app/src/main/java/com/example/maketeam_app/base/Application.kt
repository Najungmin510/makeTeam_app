package com.example.maketeam_app.base

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application : Application() {
    override fun onCreate(){
        super.onCreate()

        KakaoSdk.init(this, "87a433ac0f324bdc908ff8636bce1a72")
    }
}