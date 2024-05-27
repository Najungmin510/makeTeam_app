plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
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
    implementation ("com.kakao.sdk:v2-user:$kakaoVersion")

    //mpchart
    implementation ("com.github.PhilJay:MPAndroidChart:$mpCharVersion")
}