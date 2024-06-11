import com.android.tools.r8.internal.Sp

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.5.0"
    id("androidx.navigation.safeargs.kotlin")
    id ("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.maketeam_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.maketeam_app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    val glideVersion = "4.16.0"
    val fragmentVersion = "1.6.2"
    val CoroutineVersion = "1.3.9"
    val navVersion = "2.7.7"
    val kakaoVersion = "2.20.1"
    val mpCharVersion = "v3.1.0"
    val hiltVersion = "2.48"
    val roomVersion = "2.6.1"
    val moshiVersion ="1.15.1"
    val gsonVersion = "2.10.1"
    val lottieVersion = "6.4.0"
    val SpinKitVersion = "1.4.0"

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Glide
    implementation("com.github.bumptech.glide:glide:$glideVersion")

    //Fragment
    implementation ("androidx.fragment:fragment-ktx:$fragmentVersion")

    //Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$CoroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$CoroutineVersion")

    //Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation ("androidx.navigation:navigation-ui-ktx:$navVersion")

    //kakao login
    implementation ("com.kakao.sdk:v2-user:$kakaoVersion")

    //mpchart
    implementation ("com.github.PhilJay:MPAndroidChart:$mpCharVersion")

    //hilt
    implementation ("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    //roomDB
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    //annotationProcessor("androidx.room:room-compiler:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    //gson
    implementation("com.google.code.gson:gson:$gsonVersion")

    //Moshi
    //implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    //ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")

    //lottie (벡터기반 애니메이션)
    implementation("com.airbnb.android:lottie:$lottieVersion")

    //
    implementation("com.github.ybq:Android-SpinKit:$SpinKitVersion")
}