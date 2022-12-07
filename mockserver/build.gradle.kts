plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Libs.Android.MATERIAL)

    implementation(Libs.AndroidX.CORE_KTX)
    implementation(Libs.AndroidX.APPCOMPAT)

    implementation(Libs.Gson.GSON)

    implementation(Libs.Hilt.ANDROID)
    kapt(Libs.Hilt.COMPILER)

    implementation(Libs.OkHttp.OKHTTP)

    testImplementation(Libs.JUnit.JUNIT)
    androidTestImplementation(Libs.AndroidX.Test.ESPRESSO)
    androidTestImplementation(Libs.AndroidX.Test.JUNIT_EXT)

    // hilt test
    testImplementation(Libs.Hilt.TESTING)
    // For instrumented tests.
    androidTestImplementation(Libs.Hilt.TESTING)
    // ...with Kotlin.
    kaptAndroidTest(Libs.Hilt.COMPILER)
    // ...with Java.
    androidTestAnnotationProcessor(Libs.Hilt.COMPILER)
}