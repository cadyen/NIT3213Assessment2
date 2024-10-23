plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    //Add required plugins
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")

}

android {
    namespace = "com.example.nit3213assessment2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nit3213assessment2"
        minSdk = 28
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

    buildFeatures {
        viewBinding  = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //Add required dependencies
    implementation(libs.material)
    implementation(libs.hilt.android)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    testImplementation(libs.junit.junit)
    kapt(libs.hilt.android.compiler)
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.logging.interceptor)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui)
    // Local unit test dependencies (run on the JVM)
    testImplementation(libs.mockk) // Core MockK library for local unit tests
    testImplementation(libs.mockk.android) // Android-specific MockK for local unit tests
    testImplementation(libs.mockk.agent) // MockK agent for advanced mocking (e.g., static methods)
    testImplementation(libs.junit) // JUnit for local unit tests
// Instrumented test dependencies (run on an Android device or emulator)
    androidTestImplementation(libs.mockk.android) // Android- specific MockK for instrumented tests
    androidTestImplementation(libs.mockk.agent) // MockK agent for advanced mocking in instrumented tests
    androidTestImplementation(libs.androidx.junit.v113) // AndroidX JUnit for instrumented tests
    //testImplementation(libs.kotlinx.coroutines.test)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kapt {
    correctErrorTypes = true
}
